package com.example.demo.modele.DAO;

import com.example.demo.modele.ressources.Salle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SalleDAO extends JdbcDAO implements AutoCloseable{
    private static Logger log = Logger.getLogger(SeanceDAO.class.getName());

    private SalleDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM SALLE");
        findbyID = connection.prepareStatement("SELECT * FROM SEANCE WHERE CODES=?");
    }

    public static SalleDAO create() throws SQLException {
        return new SalleDAO();
    }

    public List<Salle> findAll() throws SQLException {
        List<Salle> salles = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            salles.add(Salle.of(rs.getString("CODES"), rs.getString("DEPART"), rs.getInt("NBRPLACE")));
        }
        return salles;
    }

    public Salle findById(String module) throws SQLException {
        Salle s = null;
        findbyID.setString(1, module);
        ResultSet rs = findbyID.executeQuery();
        while (rs.next()) {
            s = Salle.of(rs.getString("CODES"), rs.getString("DEPART"), rs.getInt("NBRPLACE"));
        }
        return s;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}
