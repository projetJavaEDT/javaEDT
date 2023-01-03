package com.example.demo.modele.DAO;


import com.example.demo.exception.*;
import com.example.demo.modele.user.Administrateur;
import com.example.demo.modele.user.Enseignant;
import com.example.demo.modele.user.Etudiant;

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
        findbyID = connection.prepareStatement("SELECT * FROM ENSEIGNANT WHERE EMAIL=?");
    }


    public List<Enseignant> findAll() throws SQLException, ExceptionEmail  {
        List<Enseignant> enseignants = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            enseignants.add(Enseignant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE"), rs.getString("ADRESSE"), rs.getString("TEL"), rs.getString("EMAIL"), rs.getString("MDP"), rs.getString("GRADE")));
        }
        return enseignants;
    }


    public Enseignant findByID(String email) throws SQLException, ExceptionEmail {
        Enseignant ens = null;
        findbyID.setString(1, email);
        ResultSet rs = findbyID.executeQuery();
        while (rs.next()) {
            ens = Enseignant.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE"), rs.getString("ADRESSE"), rs.getString("TEL"), rs.getString("EMAIL"), rs.getString("MDP"), rs.getString("GRADE"));
        }
        return ens;
    }


    public void update(String id, Enseignant ens) throws SQLException {
        update(id, ens.getNom(), ens.getPrenom(), (Date) ens.getDatenaissance(), ens.getAdresse(), ens.getTel(), ens.getEmail(), ens.getGrade());
    }

    public void update(String id, String nom, String prenom, Date datenaissance, String adresse, String tel, String mail, String grade) throws SQLException {
        update = connection.prepareStatement("UPDATE ENSEIGNANT SET NOM='"+nom+"', PRENOM='"+prenom+"', DATENAISSANCE='"+datenaissance+"', ADRESSE='"+adresse+"', TEL='"+tel+"', EMAIL='"+mail+"', GRADE='"+grade+"' WHERE EMAIL='"+id+"' ");
        update.executeUpdate();
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