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
import java.sql.SQLException;


public class AuthController{
    @FXML
    private TextField emailIdField;
    @FXML
    private Label codeerror;
    @FXML
    private PasswordField passwordField;
    private Controller control;
    public static String mail_pers;
    Help help = new Help();
    public void clean() {
        codeerror.setText("");
    }


    //ESPACE
    public void espaceDedie(ActionEvent event, Utilisateur user){
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = null;
            if(user.getClass().getSimpleName().equals("Etudiant")){
                fxmlLoader = new FXMLLoader(Appli.class.getResource("etud-view.fxml"));
            } else if (user.getClass().getSimpleName().equals("Enseignant")) {
                fxmlLoader = new FXMLLoader(Appli.class.getResource("ens-view.fxml"));
            } else{ //admin
                fxmlLoader = new FXMLLoader(Appli.class.getResource("admin-view.fxml"));
            }
            Scene scene = new Scene(fxmlLoader.load());
            control = fxmlLoader.getController();
            control.labnom.setText(user.getNom());
            control.labprenom.setText(user.getPrenom());
            control.initialise();
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
        password = help.hacherMdp(password);
        String type = help.validateEmailRegex(emailId);

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
}
