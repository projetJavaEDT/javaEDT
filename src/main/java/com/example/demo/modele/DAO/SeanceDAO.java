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

    //private static Logger log = Logger.getLogger(SeanceDAO.class.getName());
    private PreparedStatement weekseance;
    public PreparedStatement delete;


    private SeanceDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM SEANCE");
        findbyID = connection.prepareStatement("SELECT * FROM SEANCE WHERE CODEM=? AND DATE=? AND HEURED=?");
        weekseance = connection.prepareStatement("SELECT * FROM SEANCE where DATE BETWEEN ? AND ?");
        persist = connection.prepareStatement("INSERT INTO SEANCE(CODEM, CODES, CODEENS, TYPESEANCE, DATE, HEURED, HEUREF) VALUES (?,?,?,?,?,?,?)");
        delete = connection.prepareStatement("DELETE FROM SEANCE WHERE CODEM=? AND DATE=? AND HEURED=?");
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

    public Seance recapSeance(String module, String date, int hdebut) throws SQLException {
        Seance s = null;
        findbyID.setString(1,module);
        findbyID.setString(2,date);
        findbyID.setInt(3,hdebut);
        ResultSet rs = findbyID.executeQuery();
        while (rs.next()) {
            s = Seance.of(rs.getString("CODEM"), rs.getString("CODES"), rs.getString("CODEENS"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getInt("HEURED"), rs.getInt("HEUREF"));
        }
        return s;
    }

    public List<Seance> findbyDate(String dated, String datef) throws SQLException {
        List<Seance> s = new ArrayList<>();
        weekseance.setString(1,dated);
        weekseance.setString(2,datef);
        ResultSet rs = weekseance.executeQuery();
        while (rs.next()) {
            s.add(Seance.of(rs.getString("CODEM"), rs.getString("CODES"), rs.getString("CODEENS"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getInt("HEURED"), rs.getInt("HEUREF")));
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


    public void delete(String codeMod, String date, int heured) throws SQLException {
        delete.setString(1, codeMod);
        delete.setString(2, date);
        delete.setInt(3, heured);
        delete.executeUpdate();
    }


    public void update(Seance s) throws SQLException {
        update(s.getCodeMod(), s.getCodeSalle(), s.getCodeEns(), String.valueOf(s.getTypeSeance()), s.getDate(), s.getHeureD(), s.getHeureF());
    }

    public void update(String module, String salle, String ens, String type, Date date, int hd, int hf) throws SQLException {
        update = connection.prepareStatement("UPDATE SEANCE SET CODEM='"+module+"', CODES='"+salle+"', CODEENS='"+ens+"', TYPESEANCE='"+type+"', DATE='"+date+"', HEURED='"+hd+"', HEUREF='"+hf+"' WHERE CODEM='"+module+"' AND DATE='"+date+"' AND HEURED='"+hd+"'");
        update.executeUpdate();
    }


    @Override
    public void close() throws SQLException {
        connection.close();
    }
}