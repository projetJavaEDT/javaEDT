package com.example.demo.modele.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.example.demo.modele.ressources.Module;

public class ModuleDAO extends JdbcDAO implements AutoCloseable{

    //private static Logger log = Logger.getLogger(ModuleDAO.class.getName());
    private PreparedStatement findbyLib;
    private PreparedStatement findbyCode;
    private PreparedStatement find;

    public static ModuleDAO create() throws SQLException {
        return new ModuleDAO();
    }


    private ModuleDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT SEANCE.CODEM, LIBELLEMOD, CODEENS, min(DATE) AS DEBUT, max(DATE) AS FIN, VOLUMEHORAIRE FROM SEANCE,MODULE WHERE SEANCE.CODEM = MODULE.CODEM GROUP BY SEANCE.CODEM,CODEENS");
        findbyCode = connection.prepareStatement("SELECT SEANCE.CODEM, LIBELLEMOD, CODEENS, min(DATE) AS DEBUT, max(DATE) AS FIN, VOLUMEHORAIRE FROM SEANCE,MODULE WHERE SEANCE.CODEM = MODULE.CODEM AND SEANCE.CODEM=? GROUP BY SEANCE.CODEM,CODEENS");
        findbyLib = connection.prepareStatement("SELECT SEANCE.CODEM, LIBELLEMOD, CODEENS, min(DATE) AS DEBUT, max(DATE) AS FIN, VOLUMEHORAIRE FROM SEANCE,MODULE WHERE SEANCE.CODEM = MODULE.CODEM AND LIBELLEMOD=? GROUP BY SEANCE.CODEM,CODEENS");
    }

    public List<Module> findAll_() throws SQLException {
        List<Module> modules = new ArrayList<>();
        findAll = connection.prepareStatement("SELECT * FROM MODULE");
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            modules.add(Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE")));
        }
        return modules;
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

    public List<Module> recapByCode(String module) throws SQLException {
        List<Module> modules = new ArrayList<>();
        findbyCode.setString(1, module);
        ResultSet rs = findbyCode.executeQuery();
        while (rs.next()) {
            modules.add(Module.off(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getString("CODEENS"), rs.getDate("DEBUT"), rs.getDate("FIN"), rs.getInt("VOLUMEHORAIRE")));
        }
        return modules;
    }


    public List<Module> recapByLib(String module) throws SQLException {
        List<Module> modules = new ArrayList<>();
        findbyLib.setString(1, module);
        ResultSet rs = findbyLib.executeQuery();
        while (rs.next()) {
            modules.add(Module.off(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getString("CODEENS"), rs.getDate("DEBUT"), rs.getDate("FIN"), rs.getInt("VOLUMEHORAIRE")));
        }
        return modules;
    }


    public Module findbyId(String module) throws SQLException {
        Module mod= null;
        find = connection.prepareStatement("SELECT * FROM MODULE WHERE CODEM=?");
        find.setString(1, module);
        ResultSet rs = find.executeQuery();
        while (rs.next()) {
            mod = Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }

    public Module findbyLib(String module) throws SQLException {
        Module mod= null;
        find = connection.prepareStatement("SELECT * FROM MODULE WHERE LIBELLEMOD=?");
        find.setString(1, module);
        ResultSet rs = find.executeQuery();
        while (rs.next()) {
            mod = Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }


    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
