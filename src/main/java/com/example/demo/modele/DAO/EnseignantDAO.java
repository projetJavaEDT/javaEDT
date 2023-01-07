package com.example.demo.modele.DAO;


import com.example.demo.exception.*;
import com.example.demo.modele.user.Enseignant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnseignantDAO extends JdbcDAO implements AutoCloseable, ComportementUser {
    //private static Logger log = Logger.getLogger(EnseignantDAO.class.getName());
    PreparedStatement findAllDispo;

    public static EnseignantDAO  create() throws SQLException {
        return new EnseignantDAO();
    }


    private EnseignantDAO() throws SQLException {
        findby = connection.prepareStatement("SELECT * FROM ENSEIGNANT WHERE EMAIL=? AND MDP = ?");
        findAll = connection.prepareStatement("SELECT * FROM ENSEIGNANT");
        findbyID = connection.prepareStatement("SELECT * FROM ENSEIGNANT WHERE EMAIL=?");
        findAllDispo = connection.prepareStatement("SELECT * FROM ENSEIGNANT WHERE EMAIL NOT IN(SELECT EMAIL FROM INDISPONIBILITE WHERE JOUR=?)");
    }


    @Override
    public boolean validate(String email, String password) throws SQLException {
        findby.setString(1, email);
        findby.setString(2, password);
        ResultSet resultSet = findby.executeQuery();
        return resultSet.next();
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

    public List<Enseignant> findAllDispo(String date) throws SQLException, ExceptionEmail  {
        List<Enseignant> enseignants = new ArrayList<>();
        findAllDispo.setString(1, date);
        ResultSet rs = findAllDispo.executeQuery();
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
        update(id, ens.getNom(), ens.getPrenom(), (Date) ens.getDatenaissance(), ens.getAdresse(), ens.getTel(), ens.getEmail(), ens.getMdp(), ens.getGrade());
    }

    public void update(String id, String nom, String prenom, Date datenaissance, String adresse, String tel, String mail, String mdp, String grade) throws SQLException {
        update = connection.prepareStatement("UPDATE ENSEIGNANT SET NOM='"+nom+"', PRENOM='"+prenom+"', DATENAISSANCE='"+datenaissance+"', ADRESSE='"+adresse+"', TEL='"+tel+"', EMAIL='"+mail+"', MDP='"+mdp+"', GRADE='"+grade+"' WHERE EMAIL='"+id+"' ");
        update.executeUpdate();
    }


    @Override
    public void close() throws SQLException {
        connection.close();
    }

}