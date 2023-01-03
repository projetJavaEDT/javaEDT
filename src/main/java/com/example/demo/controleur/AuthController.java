package com.example.demo.controleur;


import com.example.demo.Appli;
import com.example.demo.exception.*;
import com.example.demo.modele.DAO.*;
import com.example.demo.modele.user.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthController{
    @FXML
    private TextField emailIdField;
    @FXML
    private Label codeerror;
    @FXML
    private PasswordField passwordField;
    private EtudController etudControl;
    private EnsController ensControl;
    private AdminController adminControl;
    public static String mail_pers;

    public void clean() {
        codeerror.setText("");
    }


    //ESPACE
    public void espaceDedie(ActionEvent event, Utilisateur user){
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = null;
            Scene scene = null;
            switch (user.getClass().getSimpleName()){
                case "Etudiant":
                    fxmlLoader = new FXMLLoader(Appli.class.getResource("etud-view.fxml"));
                    scene = new Scene(fxmlLoader.load());
                    etudControl = fxmlLoader.getController();
                    etudControl.labnom.setText(user.getNom());
                    etudControl.labprenom.setText(user.getPrenom());
                    etudControl.initialise();
                    break;
                case "Enseignant":
                    fxmlLoader = new FXMLLoader(Appli.class.getResource("ens-view.fxml"));
                    scene = new Scene(fxmlLoader.load());
                    ensControl = fxmlLoader.getController();
                    ensControl.labnom.setText(user.getNom());
                    ensControl.labprenom.setText(user.getPrenom());
                    ensControl.initialise();
                    break;
                case "Administrateur":
                    fxmlLoader = new FXMLLoader(Appli.class.getResource("admin-view.fxml"));
                    scene = new Scene(fxmlLoader.load());
                    adminControl = fxmlLoader.getController();
                    adminControl.labnom.setText(user.getPrenom());
                    adminControl.initialise();
                    break;
            }
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    //ETABLIR CONNEXION AVEC LESPACE APPROPRIE
    public void login(ActionEvent event) throws SQLException, ExceptionEmail, ExceptionAge {
        if (emailIdField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            codeerror.setText("Entrer un email ou un mot de passe!");
            return;
        }
        String emailId = emailIdField.getText();
        String password = passwordField.getText();
        password = hacherMdp(password);
        String type = validateEmailRegex(emailId);

        VerificationMailMdp verif = null;
        switch(type){
            case "ens":
                verif = EnseignantDAO.create();
                break;
            case "etud":
                verif = EtudiantDAO.create();
                break;
            case "admin":
                verif = AdministrateurDAO.create();
                break;
            default:
                codeerror.setText("Entrez une adresse mail valide!");
        }
        if(verif != null) {
            boolean flag = verif.validate(emailId, password);
            if (!flag)
                codeerror.setText("Email ou mot de passe incorrecte!");
            else {
                switch (type) {
                    case "ens":
                        Enseignant enseignant = ((EnseignantDAO) verif).findByID(emailId);
                        mail_pers = enseignant.getEmail();
                        espaceDedie(event,enseignant);
                        break;
                    case "etud":
                        Etudiant etudiant = ((EtudiantDAO) verif).findByID(emailId);
                        mail_pers = etudiant.getEmail();
                        espaceDedie(event,etudiant);
                        break;
                    case "admin":
                        Administrateur admin = ((AdministrateurDAO) verif).findByID(emailId);
                        mail_pers = admin.getEmail();
                        espaceDedie(event,admin);
                        break;
                }
            }
        }
    }



    //UTILITAIRES
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


    public String hacherMdp(String mdp){
        String s = "" ;
        MessageDigest msg;

        try {
            msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(mdp.getBytes(StandardCharsets.UTF_8));
            for(byte b : hash) {
                s += (Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return s;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
