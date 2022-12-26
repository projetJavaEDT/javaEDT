package com.example.demo.modele.DAO;


import com.example.demo.modele.user.Administrateur;
import com.example.demo.exception.*;
import com.example.demo.modele.user.Enseignant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AdministrateurDAO extends JdbcDAO implements AutoCloseable, VerificationMailMdp {
    private static Logger log = Logger.getLogger(EtudiantDAO.class.getName());

    public static AdministrateurDAO  create() throws SQLException {
        return new AdministrateurDAO();
    }


    private AdministrateurDAO() throws SQLException {
        findby = connection.prepareStatement("SELECT * FROM ADMINISTRATEUR WHERE EMAIL=? AND MDP = ?");
    }


    public Administrateur findById(String email) throws SQLException, ExceptionAge,ExceptionEmail {
        Administrateur adm = null;
        findby.setString(1, email);
        ResultSet rs = findby.executeQuery();
        while (rs.next()) {
            adm = Administrateur.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE"), rs.getString("ADRESSE"), rs.getString("TEL"), rs.getString("EMAIL"), rs.getString("MDP"));
        }
        return adm;
    }


    @Override
    public boolean validate(String email, String password) throws SQLException {
        findby.setString(1, email);
        findby.setString(2, password);
        ResultSet resultSet = findby.executeQuery();
        return resultSet.next();
    }


    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}