package com.example.demo.controleur;

import com.example.demo.Appli;
import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.*;
import com.example.demo.modele.ressources.Module;
import com.example.demo.modele.ressources.Salle;
import com.example.demo.modele.ressources.Seance;
import com.example.demo.modele.user.Administrateur;
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
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class AdminController extends Controller{
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
    private Button savebtn;
    @FXML
    private Button cancelbtn;
    @FXML
    private TextField codemod;
    @FXML
    private TextField libm;
    @FXML
    private ChoiceBox codesalle;
    @FXML
    private TextField codeens;
    @FXML
    private ChoiceBox types;
    @FXML
    private DatePicker dateseance;
    @FXML
    private TextField debuts;
    @FXML
    private TextField fins;
    @FXML
    private Button modifseance;
    @FXML
    private Button saveseance;
    @FXML
    private Button cancelseance;
    @FXML
    private GridPane edt;
    private Help h = new Help();



    public void init(){
        Label jour = new Label();
        jour.setAlignment(Pos.CENTER);
        jour.setContentDisplay(ContentDisplay.CENTER);
        jour.setFont(new Font("System Bold Italic",14));

        edt.add(jour,1,0);
        edt.setHalignment(jour, HPos.CENTER);
    }



    public void modifSeance(MouseEvent mouseEvent) {
        codemod.setEditable(true);
        libm.setEditable(true);
        codeens.setEditable(true);
        dateseance.setEditable(true);
        debuts.setEditable(true);
        fins.setEditable(true);

        saveseance.setDisable(false);
        cancelseance.setDisable(false);
    }

    public void addSeance(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("addSeance-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900,700);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Insertion séance");
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    //********************************************PARTIE INFOS PERSOS****************************************************
    public void displayInfos(){
        try(AdministrateurDAO adminDAO = AdministrateurDAO.create()) {
            Administrateur admin = adminDAO.findByID(AuthController.mail_pers);
            nom.setText(admin.getNom());
            prenom.setText(admin.getPrenom());

            LocalDate ldate = LocalDate.parse(admin.getDatenaissance().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            datenaiss.setValue(ldate);
            email.setText(admin.getEmail());
            tel.setText(String.valueOf(admin.getTel()));
            adresse.setText(admin.getAdresse());

            nom.setDisable(true);
            prenom.setDisable(true);
            datenaiss.setDisable(true);
            email.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
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
        savebtn.setDisable(false);
        cancelbtn.setDisable(false);
    }

    public void cancelModifInfos(MouseEvent mouseEvent) {
        displayInfos();
    }

    public void saveModifInfos(MouseEvent mouseEvent) {
        String val1 = nom.getText();
        String val2 = prenom.getText();
        String val3 = datenaiss.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String val4 = adresse.getText();
        String val5 = tel.getText();
        String val6 = email.getText();
        try(AdministrateurDAO adminDAO = AdministrateurDAO.create()) {
            adminDAO.update(new AuthController().mail_pers,val1,val2, java.sql.Date.valueOf(val3),val4,val5,val6);
            h.infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            h.infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }


}
