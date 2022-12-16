package com.example.demo.modele.DAO;

import com.example.demo.Module;
import com.example.demo.Seance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SeanceDAO extends JdbcDAO implements AutoCloseable{

    private static Logger log = Logger.getLogger(SeanceDAO.class.getName());

    private SeanceDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM SEANCE");
    }

    public static SeanceDAO create() throws SQLException {
        return new SeanceDAO();
    }

    public List<Seance> findAll() throws SQLException {
        List<Seance> seances = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            seances.add(Seance.of(rs.getString("CODEM"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getString("HEURED"), rs.getString("HEUREF")));
        }
        return seances;
    }

    public List<Seance> displayM_1() throws SQLException {
        List<Seance> seances = new ArrayList<>();
        findAll = connection.prepareStatement("SELECT CODEM,DATE,TYPESEANCE,HEURED,HEUREF FROM SEANCE");
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            seances.add(Seance.of(rs.getString("CODEM"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getString("HEURED"), rs.getString("HEUREF")));
        }
        return seances;
    }

    public Seance findById(int id) throws SQLException {
        Seance s = null;
        findbyId.setInt(1, id);
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            s = Seance.of(rs.getString("CODEM"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getString("HEURED"), rs.getString("HEUREF"));
        }
        return s;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}