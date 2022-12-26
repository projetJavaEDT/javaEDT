package com.example.demo.modele.DAO;


import com.example.demo.exception.*;
import com.example.demo.modele.user.Administrateur;
import com.example.demo.modele.user.Enseignant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnseignantDAO extends JdbcDAO implements AutoCloseable, VerificationMailMdp {
    private static Logger log = Logger.getLogger(EnseignantDAO.class.getName());

    public static EnseignantDAO  create() throws SQLException {
        return new EnseignantDAO();
    }


    private EnseignantDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM ENSEIGNANT");
        findby = connection.prepareStatement("SELECT * FROM ENSEIGNANT WHERE EMAIL=? AND MDP = ?");
    }


    //String nom, String prenom, String email, int tel, String mdp, String grade
    public List<Enseignant> findAll() throws SQLException, ExceptionAge, ExceptionEmail  {
        List<Enseignant> enseignants = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            enseignants.add(Enseignant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE"), rs.getString("ADRESSE"), rs.getString("TEL"), rs.getString("EMAIL"), rs.getString("MDP"), rs.getString("GRADE")));
        }
        return enseignants;
    }


    public Enseignant findById(String email) throws SQLException, ExceptionAge, ExceptionEmail {
        Enseignant ens = null;
        findby.setString(1, email);
        ResultSet rs = findby.executeQuery();
        while (rs.next()) {
            ens = Enseignant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE"), rs.getString("ADRESSE"), rs.getString("TEL"), rs.getString("EMAIL"), rs.getString("MDP"), rs.getString("GRADE"));
        }
        return ens;
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