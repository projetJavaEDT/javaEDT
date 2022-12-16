package fr.univ.tln.projets.projetJava.EDT.DAO;

import fr.univ.tln.projets.projetJava.EDT.Classes.Security;
import fr.univ.tln.projets.projetJava.EDT.Classes.User.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EtudiantDAO extends JdbcDAO implements AutoCloseable{
    private static Logger log = Logger.getLogger(EtudiantDAO.class.getName());
    public EtudiantDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM ETUDIANT");
        findbyId = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE EMAIL=? AND MDP = ?");
    }
    public static EtudiantDAO  create() throws SQLException {
        return new EtudiantDAO();
    }
    public List<Etudiant> findAll() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            etudiants.add(Etudiant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getInt("AGE"),rs.getString("ADRESSE"),rs.getString("PROMO")));
        }
        return etudiants;
    }
    public Etudiant findById(String email) throws SQLException {
        Etudiant etud = null;
        findbyId.setString(1, email);
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            etud = Etudiant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getInt("AGE"),rs.getString("ADRESSE"),rs.getString("PROMO"));
        }
        return etud;
    }
    public boolean exist(Etudiant etud) throws SQLException {
        return exist(etud.getEmail());
    }
    public boolean exist(String email) throws SQLException {
        findbyId.setString(1, email);
        ResultSet rs = findbyId.executeQuery();
        return rs.next();
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