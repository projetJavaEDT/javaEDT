package com.example.demo.controleur;

import com.example.demo.Appli;
import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.*;
import com.example.demo.modele.ressources.Salle;
import com.example.demo.modele.user.Administrateur;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Help h = new Help();


    @Override
    public void actuSeance(MouseEvent mouseEvent) {
        super.actuSeance(mouseEvent);
        modifseance.setDisable(true);
        saveseance.setDisable(true);
        cancelseance.setDisable(true);
    }


    public void modifSeance(MouseEvent mouseEvent) throws SQLException {
        codemod.setEditable(true);
        libm.setEditable(true);
        codesalle.setDisable(false);
        types.setDisable(false);
        codeens.setEditable(true);
        dateseance.setDisable(false);
        debuts.setEditable(true);
        fins.setEditable(true);

        saveseance.setDisable(false);
        cancelseance.setDisable(false);

        codesalle.getItems().remove(1,codesalle.getItems().size());
        salleAvailable(dateseance, codesalle);
    }

    public void salleAvailable(DatePicker date, ChoiceBox choice) throws SQLException {
        SalleDAO s = SalleDAO.create();
        if(date.getValue() != null){
            List<Salle> salles = s.findbyDate(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            for(Salle sa : salles){
                choice.getItems().add(sa.getCodeSalle());
            }
        }
    }


    @Override
    public void clickEDT(MouseEvent mouseEvent) {
        super.clickEDT(mouseEvent);
        modifseance.setDisable(false);
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
