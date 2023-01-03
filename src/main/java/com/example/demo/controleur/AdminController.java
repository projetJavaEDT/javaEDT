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

public class AdminController {

    @FXML
    public Label labnom;
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
    private TableColumn nbrpl;
    @FXML
    private TableColumn depart;
    @FXML
    private TableColumn codem;
    @FXML
    private TableColumn libmod;
    @FXML
    private TableColumn ens;
    @FXML
    private TableColumn debut;
    @FXML
    private TableColumn fin;
    @FXML
    private TableColumn volumeh;
    @FXML
    private TextField searchm;
    @FXML
    private ChoiceBox choicem;
    @FXML
    private TextField searchs;
    @FXML
    private ChoiceBox choices;
    @FXML
    private ChoiceBox months;
    @FXML
    private ChoiceBox weeks;
    @FXML
    private DatePicker dates;
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
    private ObservableList data;
    private FlowPane f;
    private Label courslabel;
    private Help h;


    public void initialise(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),new EventHandler(){
            @Override
            public void handle(Event event) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                currdate.setText(s.format(cal.getTime()));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //ajout des items du choicebox
        choicem.getItems().add("Code");
        choicem.getItems().add("Libellé");
        //ajout des items du choicebox
        choices.getItems().add("Code");
        choices.getItems().add("Date");
        //ajout des items du choicebox
        months.getItems().add("Janvier");
        months.getItems().add("Février");
        months.getItems().add("Mars");
        months.getItems().add("Avril");
        months.getItems().add("Mai");
        months.getItems().add("Juin");
        months.getItems().add("Juillet");
        months.getItems().add("Aout");
        months.getItems().add("Septembre");
        months.getItems().add("Octobre");
        months.getItems().add("Novembre");
        months.getItems().add("Décembre");
        //ajout des items du choicebox
        types.getItems().add("CM");
        types.getItems().add("TD");
        types.getItems().add("TP");

        months.getSelectionModel().selectFirst();
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

    //PARTIE EDT
    public void displayEDT(Event event) {
        h = new Help();
        h.deleteOldWeeks(edt);
        EDTperDate("2022-12-05", "2022-12-11");
    }


    private void EDTperDate(String dated, String datef){
        h = new Help();
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            List<Seance> ss = seanceDAO.findMinDate(dated, datef);
            for(Seance s : ss) {
                f = new FlowPane();
                String cours = s.getCodeMod();
                courslabel = new Label(cours);
                courslabel.setAlignment(Pos.CENTER);
                f.getChildren().add(courslabel);
                courslabel.setOnMouseClicked(this::dispalyRecap);
                edt.add(f, h.jourSemaine(s), h.horaireD(s), 1, h.horaireF(s));
                h.typeSeance(s,f);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void dispalyRecap(MouseEvent mouseEvent) {
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            Module m = ModuleDAO.create().findMod(courslabel.getText());
            Seance s = seanceDAO.findById(m.getCodeMod());
            codemod.setText(m.getCodeMod());
            libm.setText(m.getLibelleMod());
            switch (s.getTypeSeance().toString()){
                case "CM":
                    types.getSelectionModel().select(0);
                    break;
                case "TD":
                    types.getSelectionModel().select(1);
                    break;
                case "TP":
                    types.getSelectionModel().select(2);
                    break;
            }
            codeens.setText(s.getCodeEns());
            dateseance.setValue(LocalDate.parse(s.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            debuts.setText(String.valueOf(s.getHeureD()));
            fins.setText(String.valueOf(s.getHeureF()));
            modifseance.setDisable(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void nextWeek(MouseEvent mouseEvent) {
        h = new Help();
        h.deleteOldWeeks(edt);
        EDTperDate("2022-12-12","2022-12-18");
    }

    public void prevWeek(MouseEvent mouseEvent) {
        h = new Help();
        h.deleteOldWeeks(edt);
        EDTperDate("2022-12-05", "2022-12-11");
    }


    //PARTIE INFORMATIONS PERSONNELLES
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

    public void saveModifInfos(MouseEvent mouseEvent) {
        String val1 = nom.getText();
        String val2 = prenom.getText();
        String val3 = datenaiss.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String val4 = adresse.getText();
        String val5 = tel.getText();
        String val6 = email.getText();
        try(AdministrateurDAO adminDAO = AdministrateurDAO.create()) {
            adminDAO.update(new AuthController().mail_pers,val1,val2, java.sql.Date.valueOf(val3),val4,val5,val6);
            infoBox("Modification(s) sauvegardée(s)!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            infoBox("Modification(s) non sauvegardée(s)!", "Echec");
        }
    }

    public void cancelModifInfos(MouseEvent mouseEvent) {
        displayInfos();
    }


    //PARTIE MATIERES
    public void displayMatieres(Event event) {
        try(ModuleDAO modDAO = ModuleDAO.create()) {
            data = FXCollections.observableArrayList();
            List<Module> modules = modDAO.findAll();
            for(Module m : modules){
                data.add(m);
            }
            codem.setCellValueFactory(new PropertyValueFactory<Salle, String>("codeMod"));
            libmod.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("libelleMod"));
            ens.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("enseignant"));
            debut.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("dateDebut"));
            fin.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("dateFin"));
            volumeh.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("volumeHoraire"));
            tablemat.setItems(data);
            choicem.getSelectionModel().select("Code");
            searchm.setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchModule(MouseEvent mouseEvent) {
        try(ModuleDAO moduleDAO = ModuleDAO.create()) {
            Module module = null;
            data = FXCollections.observableArrayList();
            if (choicem.getValue() == "Code"){
                module = moduleDAO.findByID(searchm.getText());
            } else{
                module = moduleDAO.findByMod(searchm.getText());
            }
            data.add(module);
            codem.setCellValueFactory(new PropertyValueFactory<Salle, String>("codeMod"));
            libmod.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("libelleMod"));
            ens.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("enseignant"));
            debut.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("dateDebut"));
            fin.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("dateFin"));
            volumeh.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("volumeHoraire"));
            tablemat.setItems(data);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //PARTIE SALLES
    public void searchChoice(ActionEvent actionEvent) {
        if (choices.getSelectionModel().getSelectedIndex() == 0){
            dates.setVisible(false);
            searchs.setVisible(true);
        } else {
            dates.setVisible(true);
            searchs.setVisible(false);
        }
    }

    public void displaySalles(Event event) {
        try(SalleDAO salleDAO = SalleDAO.create()) {
            List<Salle> salles = salleDAO.findAll();;
            data = FXCollections.observableArrayList();
            for(Salle s : salles){
                data.add(s);
            }
            codes.setCellValueFactory(new PropertyValueFactory<Salle, String>("codeSalle"));
            depart.setCellValueFactory(new PropertyValueFactory<Salle, String>("depart"));
            nbrpl.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("nbPlace"));
            tablesal.setItems(data);
            choices.getSelectionModel().select("Code");
            searchs.setText("");
            dates.setVisible(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchSalle(MouseEvent mouseEvent) {
        try(SalleDAO salleDAO = SalleDAO.create()) {
            List<Salle> salles = null;
            Salle salle = null;
            data = FXCollections.observableArrayList();
            if (choices.getValue() == "Code"){
                salle = salleDAO.findById(searchs.getText());
                data.add(salle);
            } else{
                salles = salleDAO.findbyDate(dates.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                for(Salle s : salles){
                    data.add(s);
                }
            }
            codes.setCellValueFactory(new PropertyValueFactory<Salle, String>("codeSalle"));
            depart.setCellValueFactory(new PropertyValueFactory<Salle, String>("depart"));
            nbrpl.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("nbPlace"));
            tablesal.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.show();
    }



}
