package com.example.demo.controleur;

import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.EtudiantDAO;
import com.example.demo.modele.user.Etudiant;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EtudController extends Controller{
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
    private TextField promo;
    @FXML
    private Button infossavebtn;
    private Utilities h = new Utilities();


    public void displayInfos(){
        try(EtudiantDAO etudDAO = EtudiantDAO.create()) {
            Etudiant etud = etudDAO.findByID(AuthController.mail_pers);
            nom.setText(etud.getNom());
            prenom.setText(etud.getPrenom());

            LocalDate ldate = LocalDate.parse(etud.getDatenaissance().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            datenaiss.setValue(ldate);
            email.setText(etud.getEmail());
            tel.setText(String.valueOf(etud.getTel()));
            adresse.setText(etud.getAdresse());
            promo.setText(etud.getPromo());
            mdps.setText("aaaaaaaaaaa");

            nom.setDisable(true);
            prenom.setDisable(true);
            datenaiss.setDisable(true);
            email.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
            mdps.setDisable(true);
            promo.setDisable(true);
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
        promo.setDisable(false);
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
        String val8 = promo.getText();
        try(EtudiantDAO etudDAO = EtudiantDAO.create()) {
            etudDAO.update(new AuthController().mail_pers,val1,val2, java.sql.Date.valueOf(val3),val4,val5,val6,val7,val8);
            h.infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            h.infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }
}
