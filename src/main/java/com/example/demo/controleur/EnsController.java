package com.example.demo.controleur;

import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.*;
import com.example.demo.modele.user.Enseignant;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private TextField mdps;
    @FXML
    private TextField grade;
    @FXML
    private Button infossavebtn;
    @FXML
    private TextArea remarque;
    @FXML
    private DatePicker dateindispo;
    @FXML
    private Button saveindispo;
    @FXML
    private Button cancelindispo;
    private Utilities h = new Utilities();

    @Override
    public void initialise(){
        super.initialise();
        cancelindispo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/act1.png"))));
        saveindispo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/save.png"))));
    }


    //********************************************PARTIE INFOS PERSOS****************************************************
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
            mdps.setText("aaaaaaaaaaa");

            nom.setDisable(true);
            prenom.setDisable(true);
            datenaiss.setDisable(true);
            email.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
            mdps.setDisable(true);
            grade.setDisable(true);
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
        adresse.setDisable(false);
        mdps.setDisable(false);
        grade.setDisable(false);
        infossavebtn.setDisable(false);
    }


    public void saveModif(MouseEvent mouseEvent) {
        String val1 = nom.getText();
        String val2 = prenom.getText();
        String val3 = datenaiss.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String val4 = adresse.getText();
        String val5 = tel.getText();
        String val6 = email.getText();
        String val7 = h.hacherMdp(mdps.getText());
        String val8 = grade.getText();
        try(EnseignantDAO ensDAO = EnseignantDAO.create()) {
            ensDAO.update(new AuthController().mail_pers,val1,val2, java.sql.Date.valueOf(val3),val4,val5,val6,val7,val8);
            h.infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            h.infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }

    //********************************************PARTIE INDISPO****************************************************
    public void saveIdispo(MouseEvent mouseEvent) {
        String val1 = dateindispo.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String val2 = remarque.getText();
        String val3 = AuthController.mail_pers;
        try(IndisponibiliteDAO indispoDAO = IndisponibiliteDAO.create()) {
            indispoDAO.persist(Date.valueOf(val1),val2,val3);
            h.infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //h.infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }

    public void cancelIndispo(MouseEvent mouseEvent) {
        dateindispo.setValue(null);
        remarque.setText("");
    }
}
