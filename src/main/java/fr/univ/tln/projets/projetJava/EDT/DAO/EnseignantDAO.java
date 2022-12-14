package fr.univ.tln.projets.projetJava.EDT.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univ.tln.projets.projetJava.EDT.Classes.User.*;
import fr.univ.tln.projets.projetJava.EDT.Classes.Security ;
public class EnseignantDAO extends JdbcDAO implements AutoCloseable{
    private static Logger log = Logger.getLogger(EnseignantDAO.class.getName());
    public EnseignantDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM ENSEIGNANT");
        findbyId = connection.prepareStatement("SELECT * FROM ENSEIGNANT WHERE EMAIL=? AND MDP = ?");
    }
    public static EnseignantDAO  create() throws SQLException {
        return new EnseignantDAO();
    }

    //String nom, String prenom, String email, int tel, String mdp, String grade
    public List<Enseignant> findAll() throws SQLException {
        List<Enseignant> enseignants = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            enseignants.add(Enseignant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getInt("AGE"),rs.getString("ADRESSE"),rs.getString("GRADE")));
        }
        return enseignants;
    }
    public Enseignant findById(String email) throws SQLException {
        Enseignant ens = null;
        findbyId.setString(1, email);
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            ens = Enseignant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getInt("AGE"),rs.getString("ADRESSE"),rs.getString("GRADE"));
        }
        return ens;
    }
    public boolean exist(Enseignant ens) throws SQLException {
        return exist(ens.getEmail());
    }
    public boolean exist(String email) throws SQLException {
        findbyId.setString(1, email);
        ResultSet rs = findbyId.executeQuery();
        return rs.next();
    }
    public static boolean validateEmailRegex(String email){
        String regex = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][e][n][s]+[.][f][r]$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(email);
        if(!match.matches())
            return false;
        return true ;
    }
    public static boolean validate(String email, String password) throws SQLException {

        // Step 2:Create a statement using connection object
        Security sec = new Security(password);
        String mdp = sec.hacherMdp(password);
        findbyId.setString(1, email);
        findbyId.setString(2, mdp);
        //System.out.println(preparedStatement);

        ResultSet resultSet = findbyId.executeQuery();
        return resultSet.next() ;
    }
    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}