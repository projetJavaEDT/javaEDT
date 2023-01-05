package com.example.demo.modele.DAO;

import com.example.demo.modele.ressources.Seance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class SeanceDAO extends JdbcDAO implements AutoCloseable{

    private static Logger log = Logger.getLogger(SeanceDAO.class.getName());

    private SeanceDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM SEANCE");
        findbyID = connection.prepareStatement("SELECT * FROM SEANCE WHERE CODEM=?");
        persist = connection.prepareStatement("INSERT INTO SEANCE (CODEM, CODES, CODEENS, TYPESEANCE, DATE, HEURED, HEUREF) VALUES (?,?,?,?,?,?,?)");
    }

    public static SeanceDAO create() throws SQLException {
        return new SeanceDAO();
    }

    public List<Seance> findAll() throws SQLException {
        List<Seance> seances = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            seances.add(Seance.of(rs.getString("CODEM"), rs.getString("CODES"), rs.getString("CODEENS"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getInt("HEURED"), rs.getInt("HEUREF")));
        }
        return seances;
    }

    public Seance findById(String module) throws SQLException {
        Seance s = null;
        findbyID.setString(1, module);
        ResultSet rs = findbyID.executeQuery();
        while (rs.next()) {
            s = Seance.of(rs.getString("CODEM"), rs.getString("CODES"), rs.getString("CODEENS"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getInt("HEURED"), rs.getInt("HEUREF"));
        }
        return s;
    }

    public List<Seance> findMinDate(String dated, String datef) throws SQLException {
        List<Seance> s = new ArrayList<>();
        PreparedStatement minDate = connection.prepareStatement("SELECT * FROM SEANCE where DATE BETWEEN ? AND ?");
        minDate.setString(1,dated);
        minDate.setString(2,datef);
        ResultSet rs = minDate.executeQuery();
        while (rs.next()) {
            s.add(Seance.of(rs.getString("CODEM"), rs.getString("CODES"), rs.getString("CODEENS"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getInt("HEURED"), rs.getInt("HEUREF")));
        }
        return s;
    }

    public Seance recapSeance(String module, String date) throws SQLException {
        Seance s = null;
        PreparedStatement find = connection.prepareStatement("SELECT * FROM SEANCE WHERE CODEM=? AND DATE=?");
        find.setString(1,module);
        find.setString(2,date);
        ResultSet rs = find.executeQuery();
        while (rs.next()) {
            s = Seance.of(rs.getString("CODEM"), rs.getString("CODES"), rs.getString("CODEENS"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getInt("HEURED"), rs.getInt("HEUREF"));
        }
        return s;
    }


    public void persist(String codeMod, String codeSalle, String codeEns, Seance.Type typeSeance, Date date, int heureD, int heureF) throws SQLException {
        persist.setString(1, codeMod);
        persist.setString(2, codeSalle);
        persist.setString(3, codeEns);
        persist.setString(4, String.valueOf(typeSeance));
        persist.setString(5, String.valueOf(date));
        persist.setInt(6, heureD);
        persist.setInt(7, heureF);
        persist.executeUpdate();
    }

    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}