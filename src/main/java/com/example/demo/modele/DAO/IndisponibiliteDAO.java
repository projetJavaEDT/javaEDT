package com.example.demo.modele.DAO;

import com.example.demo.modele.ressources.Indisponibilite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class IndisponibiliteDAO extends JdbcDAO implements AutoCloseable {
    //private static Logger log = Logger.getLogger(EtudiantDAO.class.getName());

    public static IndisponibiliteDAO create() throws SQLException {
        return new IndisponibiliteDAO();
    }


    private IndisponibiliteDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM INDISPONIBILITE");
        findbyID = connection.prepareStatement("SELECT * FROM INDISPONIBILITE WHERE EMAIL=? AND JOUR=?");
        persist = connection.prepareStatement("INSERT INTO INDISPONIBILITE(JOUR, REMARQUE, EMAIL) VALUES(?, ?, ?)");
    }


    public List<Indisponibilite> findAll() throws SQLException {
        List<Indisponibilite> indispos = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            indispos.add(Indisponibilite.of(rs.getDate("JOUR"), rs.getString("REMARQUE"), rs.getString("MAIL")));
        }
        return indispos;
    }

    public Indisponibilite findbyId(String mail, Date jour) throws SQLException {
        Indisponibilite indispo = null;
        findbyID.setString(1, mail);
        findbyID.setString(2, String.valueOf(jour));
        ResultSet rs = findbyID.executeQuery();
        while (rs.next()) {
            indispo = Indisponibilite.of(rs.getDate("JOUR"), rs.getString("REMARQUE"), rs.getString("MAIL"));
        }
        return indispo;
    }


    public void persist(Indisponibilite indispo) throws SQLException {
        persist(indispo.getJour(), indispo.getRemarque(), indispo.getMail());
    }

    public void persist(Date jour,String remarque, String mail) throws SQLException {
        persist.setString(1, String.valueOf(jour));
        persist.setString(2, remarque);
        persist.setString(3, mail);
        persist.executeUpdate();
    }

    @Override
    public void close() throws SQLException {
        connection.close();
        //log.info("DB connection closed");
    }
}