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
    private EtudController control;

    public static String mail_pers;

    public void clean() {
        codeerror.setText("");
    }

    public void fenetreEtud(ActionEvent event){
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("etud-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            control = fxmlLoader.getController();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


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
                        break;
                    case "etud":
                        Etudiant etudiant = ((EtudiantDAO) verif).findByID(emailId);
                        mail_pers = etudiant.getEmail();
                        fenetreEtud(event);
                        control.labnom.setText(etudiant.getNom());
                        control.labprenom.setText(etudiant.getPrenom());
                        control.initDate();
                        break;
                    case "admin":
                        break;
                }
            }
        }
    }


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
