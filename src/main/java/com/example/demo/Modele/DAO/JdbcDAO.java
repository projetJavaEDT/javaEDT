package com.example.demo.Modele.DAO;

import java.sql.*;
import java.util.logging.Logger;


public abstract class JdbcDAO {

    private static Logger log = Logger.getLogger(JdbcDAO.class.getName());
    public static String DB_URL = "jdbc:h2:tcp://localhost/~/EDTbdd";
    public static String USER = "sa";
    public static String PASS = "";
    public Connection connection;
    public PreparedStatement findAll;
    public PreparedStatement findbyId;


    public JdbcDAO() throws SQLException {
        log.info("DB connection opened");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }
}