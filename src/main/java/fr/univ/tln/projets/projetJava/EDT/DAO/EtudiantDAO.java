package fr.univ.tln.projets.projetJava.EDT.DAO;

import fr.univ.tln.projets.projetJava.EDT.Classes.Utilisateur.*;
import fr.univ.tln.projets.projetJava.EDT.Exceptions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EtudiantDAO extends JdbcDAO implements AutoCloseable{
    private static Logger log = Logger.getLogger(EtudiantDAO.class.getName());
    public EtudiantDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM ETUDIANT");
        findbyId = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE EMAIL=? AND MDP = ?");
    }
    public static EtudiantDAO  create() throws SQLException {
        return new EtudiantDAO();
    }
    public List<Etudiant> findAll() throws SQLException, ExceptionAge, ExceptionEmail {
        List<Etudiant> etudiants = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {

            etudiants.add(Etudiant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getInt("AGE"),rs.getString("ADRESSE"),rs.getString("PROMO")));
        }
        return etudiants;
    }
    public Etudiant findById(String email) throws SQLException, ExceptionAge, ExceptionEmail {
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
        findbyId.setString(1, email);
        findbyId.setString(2, password);
        ResultSet resultSet = findbyId.executeQuery();
        return resultSet.next() ;
    }
    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}