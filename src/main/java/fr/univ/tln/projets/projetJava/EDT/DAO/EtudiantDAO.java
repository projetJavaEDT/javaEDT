package fr.univ.tln.projets.projetJava.EDT.DAO;

import fr.univ.tln.projets.projetJava.EDT.Classes.User.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EtudiantDAO extends JdbcDAO implements AutoCloseable{

    private static Logger log = Logger.getLogger(ModuleDAO.class.getName());
    private EtudiantDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM ETUDIANT");
        findbyId = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE EMAIL=?");
    }

    public static ModuleDAO create() throws SQLException {
        return new ModuleDAO();
    }

    public List<Etudiant> findAll() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            etudiants.add(Etudiant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getString("PROMO")));
        }
        return etudiants;
    }


    public Etudiant findById(String email) throws SQLException {
        Etudiant etud = null;
        findbyId.setString(1, email);
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            etud = Etudiant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getString("PROMO"));
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


    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}