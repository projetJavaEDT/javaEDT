package fr.univ.tln.projets.projetJava.EDT;

import fr.univ.tln.projets.projetJava.EDT.DAO.AdministrateurDAO;
import fr.univ.tln.projets.projetJava.EDT.DAO.EnseignantDAO;
import fr.univ.tln.projets.projetJava.EDT.DAO.EtudiantDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthController {

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML

    public static String validateEmailRegex(String email){
        String regexEt = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][e][t][u][d][.][f][r]$";
        String regexEn = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][e][n][s][.][f][r]$";
        String regexAd = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][a][d][m][i][n][.][f][r]$";

        Pattern pEt = Pattern.compile(regexEt);
        Pattern pEn = Pattern.compile(regexEn);
        Pattern pAd = Pattern.compile(regexAd);

        Matcher matchEt = pEt.matcher(email);
        Matcher matchEn = pEn.matcher(email);
        Matcher matchAd = pAd.matcher(email);
        if(matchEt.matches())
            return "etud";
        else if(matchEn.matches())
            return "ens";
        else if (matchAd.matches())
            return "admin";
        else
            return "not exist";
    }
    public void login(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        String emailId = emailIdField.getText();
        String password = passwordField.getText();
        String type = validateEmailRegex(emailId);
        if (type == "ens") {
            EnseignantDAO jdbcDao = new EnseignantDAO();
            boolean flag =  EnseignantDAO.validate(emailId, password) ;
            if (!flag) {
                infoBox("Entrez un mot de passe et login correcte", null, "Echec");
            } else {
                infoBox("Authentification enseignant reussite", null, "Succes");
            }
        }
        else if(type == "etud") {
            EtudiantDAO jdbcDao1 = new EtudiantDAO();
            boolean flag =  EtudiantDAO.validate(emailId, password) ;
            if (!flag) {
                infoBox("Entrez un mot de passe et login correcte", null, "Echec");
            } else {
                infoBox("Authentification etudiant reussite", null, "Succes");
            }
        }
        else if (type == "admin") {
            AdministrateurDAO jdbcDao2 = new AdministrateurDAO();
            boolean flag = AdministrateurDAO.validate(emailId, password);
            if (!flag) {
                infoBox("Entrez un mot de passe et login correcte", null, "Echec");
            } else {
                infoBox("Authentification administrateur reussite", null, "Succes");
            }
        }
        else
            infoBox("Entrez une adresse mail valide", null, "Echec");


    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

