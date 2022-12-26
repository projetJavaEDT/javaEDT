package com.example.demo.controleur;

import com.example.demo.Appli;
import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.EtudiantDAO;
import com.example.demo.modele.DAO.ModuleDAO;
import com.example.demo.modele.DAO.SalleDAO;
import com.example.demo.modele.ressources.Module;
import com.example.demo.modele.ressources.Salle;
import com.example.demo.modele.user.Etudiant;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

public class EtudController {

    @FXML
    public Label labnom;
    @FXML
    public Label labprenom;
    @FXML
    private Label currdate;
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
    private TextField promo;
    @FXML
    private Button savebtn;
    @FXML
    private Button cancelbtn;
    @FXML
    private TableView tablesal;
    @FXML
    private TableView tablemat;
    @FXML
    private TableColumn codes;
    @FXML
    private TableColumn nbrplace;
    @FXML
    private TableColumn codem;
    @FXML
    private TableColumn libmod;
    @FXML
    private TableColumn volumeh;
    private ObservableList data;


    public void initDate(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        currdate.setText(s.format(cal.getTime()));
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void fenetreAuth(ActionEvent event){
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("auth-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900,500);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("EDT-authentification");
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void displayInfos(){
        try(EtudiantDAO etudDAO = EtudiantDAO.create()) {
            Etudiant etud = etudDAO.findByMail(AuthController.mail_pers);
            nom.setText(etud.getNom());
            prenom.setText(etud.getPrenom());
            LocalDate ldate = LocalDate.parse(etud.getDatenaissance().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            datenaiss.setValue(ldate);
            email.setText(etud.getEmail());
            tel.setText(String.valueOf(etud.getTel()));
            adresse.setText(etud.getAdresse());
            promo.setText(etud.getPromo());

            nom.setEditable(false);
            prenom.setEditable(false);
            datenaiss.setEditable(false);
            email.setEditable(false);
            tel.setEditable(false);
            adresse.setEditable(false);
            promo.setEditable(false);

            nom.setDisable(true);
            prenom.setDisable(true);
            datenaiss.setDisable(true);
            email.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
            promo.setDisable(true);
            savebtn.setDisable(true);
            cancelbtn.setDisable(true);
        } catch (SQLException | ExceptionEmail e) {
            throw new RuntimeException(e);
        }
    }

    public void displayMatieres(Event event) {
        try(ModuleDAO modDAO = ModuleDAO.create()) {
            data = FXCollections.observableArrayList();
            List<Module> modules = modDAO.findAll();
            for(Module m : modules){
                data.add(m);
            }
            codem.setCellValueFactory(new PropertyValueFactory<Salle, String>("codeMod"));
            libmod.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("libelleMod"));
            volumeh.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("volumeHoraire"));
            tablemat.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displaySalles(Event event) {
        try(SalleDAO salleDAO = SalleDAO.create()) {
            data = FXCollections.observableArrayList();
            List<Salle> salles = salleDAO.findAll();
            for(Salle s : salles){
                data.add(s);
            }
            codes.setCellValueFactory(new PropertyValueFactory<Salle, String>("codeSalle"));
            nbrplace.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("nbPlace"));
            tablesal.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void modifInfos(MouseEvent mouseEvent) {
        nom.setEditable(true);
        prenom.setEditable(true);
        datenaiss.setEditable(true);
        email.setEditable(true);
        tel.setEditable(true);
        adresse.setEditable(true);
        promo.setEditable(true);

        nom.setDisable(false);
        prenom.setDisable(false);
        datenaiss.setDisable(false);
        email.setDisable(false);
        tel.setDisable(false);
        adresse.setDisable(false);
        promo.setDisable(false);
        savebtn.setDisable(false);
        cancelbtn.setDisable(false);
    }

    public void saveModif(MouseEvent mouseEvent) {
        String val1 = nom.getText();
        String val2 = prenom.getText();
        String val3 = datenaiss.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String val4 = adresse.getText();
        String val5 = tel.getText();
        String val6 = email.getText();
        String val7 = promo.getText();
        try(EtudiantDAO etudDAO = EtudiantDAO.create()) {
            etudDAO.update(new AuthController().mail_pers,val1,val2, java.sql.Date.valueOf(val3),val4,val5,val6,val7);
            infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }

    public void cancelModif(MouseEvent mouseEvent) {
        displayInfos();
    }


    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.show();
    }

}
