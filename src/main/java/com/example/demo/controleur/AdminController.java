package com.example.demo.controleur;

import com.example.demo.Appli;
import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.*;
import com.example.demo.modele.ressources.Salle;
import com.example.demo.modele.user.Administrateur;
import com.example.demo.modele.user.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
    private TextField mdps;
    @FXML
    private Button infossavebtn;
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
    private Button suppbtn;
    @FXML
    private Button saveseance;
    @FXML
    private Button addseance;
    private Utilities h = new Utilities();
    private Date olddate;
    private int oldheure;


    @Override
    public void initialise(){
        super.initialise();
        addseance.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/insert.png"))));
        saveseance.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/save.png"))));
        modifseance.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/edit.png"))));
        suppbtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/delete.png"))));
    }

    @Override
    public void clickEDT(MouseEvent mouseEvent) {
        super.clickEDT(mouseEvent);
        modifseance.setDisable(false);
    }


    @Override
    public void refreshEDT(MouseEvent mouseEvent) {
        super.refreshEDT(mouseEvent);
        modifseance.setDisable(true);
        saveseance.setDisable(true);
        suppbtn.setDisable(true);
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
        suppbtn.setDisable(false);

        codesalle.getItems().remove(1,codesalle.getItems().size());
        salleAvailable(dateseance, codesalle);
        olddate = Date.valueOf(dateseance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        oldheure = Integer.parseInt(debuts.getText());
    }


    public void saveModifsSeance(MouseEvent mouseEvent) {
        String val1 = codemod.getText();
        String val2 = String.valueOf(codesalle.getValue());
        String val3 = codeens.getText();
        String val4 = String.valueOf(types.getValue());
        String val5 = dateseance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int val6 = Integer.parseInt(debuts.getText());
        int val7 = Integer.parseInt(fins.getText());
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            seanceDAO.update(olddate,oldheure,val1,val2,val3,val4,Date.valueOf(val5),val6,val7);
            h.infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            h.infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }

    public void deleteSeance(MouseEvent mouseEvent) {
        String val1 = codemod.getText();
        String val2 = String.valueOf(dateseance.getValue());
        int val3 = Integer.parseInt(debuts.getText());
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            seanceDAO.delete(val1,val2,val3);
            h.infoBox("La séance a bien été supprimée!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            h.infoBox("Séance non supprimée!", "Echec");
        }
    }


    public void addSeance(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("addSeance-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900,700);
            SeanceController control = fxmlLoader.getController();
            control.initCompenents();
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
            mdps.setText("aaaaaaaaaaa");

            nom.setDisable(true);
            prenom.setDisable(true);
            datenaiss.setDisable(true);
            email.setDisable(true);
            tel.setDisable(true);
            mdps.setDisable(true);
            adresse.setDisable(true);
            infossavebtn.setDisable(true);
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
        mdps.setDisable(false);
        adresse.setDisable(false);
        infossavebtn.setDisable(false);
    }

    public void saveModifInfos(MouseEvent mouseEvent) {
        String val1 = nom.getText();
        String val2 = prenom.getText();
        String val3 = datenaiss.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String val4 = adresse.getText();
        String val5 = tel.getText();
        String val6 = email.getText();
        String val7 = h.hacherMdp(mdps.getText());
        try(AdministrateurDAO adminDAO = AdministrateurDAO.create()) {
            adminDAO.update(AuthController.mail_pers,val1,val2, java.sql.Date.valueOf(val3),val4,val5,val6,val7);
            h.infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            h.infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }
}
