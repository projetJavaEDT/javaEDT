package fr.univ.tln.projets.projetJava.EDT.DAO;
import fr.univ.tln.projets.projetJava.EDT.Classes.Indisponibilite.Indisponibilite;
import fr.univ.tln.projets.projetJava.EDT.Classes.Utilisateur.Enseignant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



public class IndisponibiliteDAO extends JdbcDAO implements AutoCloseable {
    private static Logger log = Logger.getLogger(EtudiantDAO.class.getName());

    public static EtudiantDAO create() throws SQLException {
        return new EtudiantDAO();
    }


    private IndisponibiliteDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM INDISPONIBILITE");
        findbyId = connection.prepareStatement("SELECT * FROM INDISPONIBILITE WHERE ID=?");
    }


    public List<Indisponibilite> findAll() throws SQLException {
        List<Indisponibilite> etudiants = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {

            etudiants.add(Indisponibilite.of(rs.getDate("JOUR"), rs.getString("HEURE"), rs.getString("REMARQUE"), rs.getString("MAIL"));
        }
        return etudiants;
    }


    public Indisponibilite findbyId(String id) throws SQLException {
        Indisponibilite indispo = null;
        findbyId.setString(1, id);
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            indispo = Indisponibilite.of(rs.getDate("JOUR"), rs.getString("HEURE"), rs.getString("REMARQUE"), rs.getString("MAIL"));
            indispo.setId();
        }
        return indispo;
    }



    public void insert(Indisponibilite indispo) throws SQLException {
        String id = indispo.getId();
        insert(id, (Date) indispo.getJour(), indispo.getHeure(), indispo.getRemarque());
    }

    public void insert(String id, Date jour, String heure, String remarque) throws SQLException {

        insert = connection.prepareStatement("INSERT INTO INDISPONIBILITE(ID, JOUR, HEURE, REMARQUE, MAIL) VALUES(?, ?, ?, ?,?)");
        insert.executeUpdate();
    }
    


    @Override
    public void close() throws SQLException {
        connection.close();
        //log.info("DB connection closed");
    }
}