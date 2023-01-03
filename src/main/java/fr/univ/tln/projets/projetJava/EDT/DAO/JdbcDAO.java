package fr.univ.tln.projets.projetJava.EDT.DAO;

import java.sql.*;
import java.util.logging.Logger;

public abstract class JdbcDAO {

    private static Logger log = Logger.getLogger(JdbcDAO.class.getName());
    public static String DB_URL = "jdbc:h2:tcp://localhost/~/EDT";
    public static String USER = "sa";
    public static String PASS = "";
    public Connection connection;
    public PreparedStatement insert;
    public PreparedStatement findAll;
    public static PreparedStatement findbyId;


    public JdbcDAO() throws SQLException {
        log.info("DB connection opened");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }
}