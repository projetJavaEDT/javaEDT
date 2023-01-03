package com.example.demo.controleur;

import com.example.demo.Appli;
import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.*;
import com.example.demo.modele.ressources.Module;
import com.example.demo.modele.ressources.Salle;
import com.example.demo.modele.ressources.Seance;
import com.example.demo.modele.user.Enseignant;
import com.example.demo.modele.user.Etudiant;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class EnsController extends Controller{
    @FXML
    private TextArea adresse;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private DatePicker datenaiss;
    @FXML
    private TextField grade;
    @FXML
    private Button savebtn;
    @FXML
    private Button cancelbtn;
    private Help h = new Help();



    public void displayInfos(){
        try(EnseignantDAO ensDAO = EnseignantDAO.create()) {
            Enseignant ens = ensDAO.findByID(AuthController.mail_pers);
            nom.setText(ens.getNom());
            prenom.setText(ens.getPrenom());

            LocalDate ldate = LocalDate.parse(ens.getDatenaissance().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            datenaiss.setValue(ldate);
            email.setText(ens.getEmail());
            tel.setText(String.valueOf(ens.getTel()));
            adresse.setText(ens.getAdresse());
            grade.setText(ens.getGrade());

            nom.setDisable(true);
            prenom.setDisable(true);
            datenaiss.setDisable(true);
            email.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
            grade.setDisable(true);
            savebtn.setDisable(true);
            cancelbtn.setDisable(true);
        } catch (SQLException | ExceptionEmail e) {
            throw new RuntimeException(e);
        }
    }

    public void modifInfos(MouseEvent mouseEvent) {
        nom.setDisable(false);
        prenom.setDisable(false);
        datenaiss.setDisable(false);
        email.setDisable(false);
        tel.setDisable(false);
        adresse.setDisable(false);
        grade.setDisable(false);
        savebtn.setDisable(false);
        cancelbtn.setDisable(false);
    }

    public void cancelModif(MouseEvent mouseEvent) {
        displayInfos();
    }

    public void saveModif(MouseEvent mouseEvent) {
        String val1 = nom.getText();
        String val2 = prenom.getText();
        String val3 = datenaiss.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String val4 = adresse.getText();
        String val5 = tel.getText();
        String val6 = email.getText();
        String val7 = grade.getText();
        try(EnseignantDAO ensDAO = EnseignantDAO.create()) {
            ensDAO.update(new AuthController().mail_pers,val1,val2, java.sql.Date.valueOf(val3),val4,val5,val6,val7);
            h.infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            h.infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }

}
