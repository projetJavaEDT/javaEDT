package com.example.demo.modele.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.example.demo.modele.ressources.Module;

public class ModuleDAO extends JdbcDAO implements AutoCloseable{

    private static Logger log = Logger.getLogger(ModuleDAO.class.getName());

    public static ModuleDAO create() throws SQLException {
        return new ModuleDAO();
    }


    private ModuleDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT SEANCE.CODEM, LIBELLEMOD, CODEENS, min(DATE) AS DEBUT, max(DATE) AS FIN, VOLUMEHORAIRE FROM SEANCE,MODULE WHERE SEANCE.CODEM = MODULE.CODEM GROUP BY SEANCE.CODEM");
        findby = connection.prepareStatement("SELECT * FROM MODULE WHERE CODEM=?");
        findbyID = connection.prepareStatement("SELECT SEANCE.CODEM, LIBELLEMOD, CODEENS, min(DATE) AS DEBUT, max(DATE) AS FIN, VOLUMEHORAIRE FROM SEANCE,MODULE WHERE SEANCE.CODEM = MODULE.CODEM AND SEANCE.CODEM=? GROUP BY SEANCE.CODEM");
        findbyMod = connection.prepareStatement("SELECT SEANCE.CODEM, LIBELLEMOD, CODEENS, min(DATE) AS DEBUT, max(DATE) AS FIN, VOLUMEHORAIRE FROM SEANCE,MODULE WHERE SEANCE.CODEM = MODULE.CODEM AND LIBELLEMOD=? GROUP BY SEANCE.CODEM");
    }


    public List<Module> findAll() throws SQLException {
        List<Module> modules = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            modules.add(Module.off(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getString("CODEENS"), rs.getDate("DEBUT"), rs.getDate("FIN"), rs.getInt("VOLUMEHORAIRE")));
        }
        return modules;
    }

    public Module findMod(String module) throws SQLException {
        Module mod= null;
        findby.setString(1, module);
        ResultSet rs = findby.executeQuery();
        while (rs.next()) {
            mod = Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }


    public Module findByID(String module) throws SQLException {
        Module mod= null;
        findbyID.setString(1, module);
        ResultSet rs = findbyID.executeQuery();
        while (rs.next()) {
            mod = Module.off(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getString("CODEENS"), rs.getDate("DEBUT"), rs.getDate("FIN"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }


    public Module findByMod(String module) throws SQLException {
        Module mod= null;
        findbyMod.setString(1, module);
        ResultSet rs = findbyMod.executeQuery();
        while (rs.next()) {
            mod = Module.off(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getString("CODEENS"), rs.getDate("DEBUT"), rs.getDate("FIN"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }


    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}
