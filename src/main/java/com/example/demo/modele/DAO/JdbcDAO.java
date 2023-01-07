package com.example.demo.modele.DAO;

import java.sql.*;
import java.util.logging.Logger;


public abstract class JdbcDAO {
    private static Logger log = Logger.getLogger(JdbcDAO.class.getName());
    public static String DB_URL = "jdbc:h2:tcp://localhost/~/EDTbdd";
    public static String USER = "sa";
    public static String PASS = "";
    public Connection connection;
    public static PreparedStatement findby; //pour verif de connexion
    public PreparedStatement findAll;
    public PreparedStatement findbyID;
    public PreparedStatement update;


    public JdbcDAO() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }


}