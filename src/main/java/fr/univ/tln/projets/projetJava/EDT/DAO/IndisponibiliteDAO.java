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
        findbyId = connection.prepareStatement("SELECT * FROM INDISPONIBILITE WHERE EMAIL=? AND JOUR=?");
    }


    public List<Indisponibilite> findAll() throws SQLException {
        List<Indisponibilite> indispos = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            indispos.add(Indisponibilite.of(rs.getDate("JOUR"), rs.getString("HEURE"), rs.getString("REMARQUE"), rs.getString("MAIL")));
        }
        return indispos;
    }

    public Indisponibilite findbyId(String mail, Date jour) throws SQLException {
        Indisponibilite indispo = null;
        findbyId.setString(1, mail);
        findbyId.setString(2, String.valueOf(jour));
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            indispo = Indisponibilite.of(rs.getDate("JOUR"), rs.getString("HEURE"), rs.getString("REMARQUE"), rs.getString("MAIL"));
        }
        return indispo;
    }

    public void insert(Indisponibilite indispo) throws SQLException {
        //String id = indispo.getId();
        insert((Date) indispo.getJour(), indispo.getHeure(), indispo.getRemarque(), indispo.getMail());
    }

    public void insert(Date jour, String heure, String remarque, String mail) throws SQLException {
        insert = connection.prepareStatement("INSERT INTO INDISPONIBILITE(JOUR, HEURE, REMARQUE, MAIL) VALUES(?, ?, ?, ?)");
        findbyId.setString(1, String.valueOf(jour));
        findbyId.setString(2, heure);
        findbyId.setString(3, remarque);
        findbyId.setString(4, mail);
        insert.executeUpdate();
    }

    @Override
    public void close() throws SQLException {
        connection.close();
        //log.info("DB connection closed");
    }
}