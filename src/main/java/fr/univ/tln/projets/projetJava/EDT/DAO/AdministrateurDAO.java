package fr.univ.tln.projets.projetJava.EDT.DAO;

import fr.univ.tln.projets.projetJava.EDT.Classes.Security;
import fr.univ.tln.projets.projetJava.EDT.Classes.User.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdministrateurDAO extends JdbcDAO implements AutoCloseable{
    private static Logger log = Logger.getLogger(EtudiantDAO.class.getName());
    public AdministrateurDAO() throws SQLException {
        findbyId = connection.prepareStatement("SELECT * FROM ADMINISTRATEUR WHERE EMAIL=? AND MDP = ?");
    }
    public static AdministrateurDAO  create() throws SQLException {
        return new AdministrateurDAO();
    }

    public Administrateur findById(String email) throws SQLException {
        Administrateur adm = null;
        findbyId.setString(1, email);
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            adm = Administrateur.of(rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"),rs.getInt("TEL"), rs.getString("MDP"),rs.getInt("AGE"),rs.getString("ADRESSE"));
        }
        return adm;
    }
    public boolean exist(Administrateur adm) throws SQLException {
        return exist(adm.getEmail());
    }
    public boolean exist(String email) throws SQLException {
        findbyId.setString(1, email);
        ResultSet rs = findbyId.executeQuery();
        return rs.next();
    }

    public static boolean validate(String email, String password) throws SQLException {

        Security sec = new Security(password);
        String mdp = sec.hacherMdp(password);
        findbyId.setString(1, email);
        findbyId.setString(2, mdp);
        ResultSet resultSet = findbyId.executeQuery();
        return resultSet.next() ;
    }
    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}