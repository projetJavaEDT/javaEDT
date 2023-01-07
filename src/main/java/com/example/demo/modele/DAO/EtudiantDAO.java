package com.example.demo.modele.DAO;

import com.example.demo.exception.*;
import com.example.demo.modele.user.Etudiant;

import java.sql.*;
import java.util.logging.Logger;

public class EtudiantDAO extends JdbcDAO implements AutoCloseable, ComportementUser {
    //private static Logger log = Logger.getLogger(EtudiantDAO.class.getName());

    public static EtudiantDAO create() throws SQLException {
        return new EtudiantDAO();
    }


    private EtudiantDAO() throws SQLException {
        findby = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE EMAIL=? AND MDP=?");
        findbyID = connection.prepareStatement("SELECT * FROM ETUDIANT WHERE EMAIL=?");
    }


    @Override
    public boolean validate(String email, String password) throws SQLException {
        findby.setString(1, email);
        findby.setString(2, password);
        ResultSet resultSet = findby.executeQuery();
        return resultSet.next();
    }



    public Etudiant findByID(String email) throws SQLException, ExceptionEmail {
        Etudiant etud = null;
        findbyID.setString(1, email);
        ResultSet rs = findbyID.executeQuery();
        while (rs.next()) {
            etud = Etudiant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE"), rs.getString("ADRESSE"), rs.getString("TEL"), rs.getString("EMAIL"), rs.getString("MDP"), rs.getString("PROMO"));
        }
        return etud;
    }


    public void update(String id, Etudiant etud) throws SQLException {
        update(id, etud.getNom(), etud.getPrenom(), (Date) etud.getDatenaissance(), etud.getAdresse(), etud.getTel(), etud.getEmail(), etud.getMdp(), etud.getPromo());
    }

    public void update(String id, String nom, String prenom, Date datenaissance, String adresse, String tel, String mail, String mdp, String promo) throws SQLException {
        update = connection.prepareStatement("UPDATE ETUDIANT SET NOM='"+nom+"', PRENOM='"+prenom+"', DATENAISSANCE='"+datenaissance+"', ADRESSE='"+adresse+"', TEL='"+tel+"', EMAIL='"+mail+"', MDP='"+mdp+"', PROMO='"+promo+"' WHERE EMAIL='"+id+"' ");
        update.executeUpdate();
    }


    @Override
    public void close() throws SQLException {
        connection.close();
    }
}