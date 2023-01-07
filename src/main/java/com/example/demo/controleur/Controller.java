package com.example.demo.controleur;

import com.example.demo.Appli;
import com.example.demo.modele.DAO.ModuleDAO;
import com.example.demo.modele.DAO.SalleDAO;
import com.example.demo.modele.DAO.SeanceDAO;
import com.example.demo.modele.ressources.Module;
import com.example.demo.modele.ressources.Salle;
import com.example.demo.modele.ressources.Seance;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public  abstract  class Controller {
    @FXML
    private Label currdate;
    @FXML
    private ChoiceBox choicem;
    @FXML
    private ChoiceBox choices;
    @FXML
    public Label labnom;
    @FXML
    public Label labprenom;
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
    private TextField searchs;
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
    private GridPane edt;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Button actedt;
    @FXML
    private Button salsearchtbn;
    @FXML
    private Button salactbtn;
    @FXML
    private Button matactbtn;
    @FXML
    private Button matsearchbtn;
    @FXML
    private Button infosmodifbtn;
    @FXML
    private Button infossavebtn;
    private ObservableList data;
    private FlowPane f;
    private Label courslabel;
    private Label datecours;
    private Label typesec;
    private Label salle;
    private Label heured;
    private LocalDate today;
    private Utilities h = new Utilities();


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

        initComponents();
    }

    private void initComponents(){
        //ajout des items du choicebox
        choicem.getItems().add("Code");
        choicem.getItems().add("Libellé");

        choices.getItems().add("Code");
        choices.getItems().add("Date");

        types.getItems().add("CM");
        types.getItems().add("TD");
        types.getItems().add("TP");

        next.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/next.png"))));
        prev.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/prev.png"))));
        actedt.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/act1.png"))));
        salsearchtbn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/searchh.png"))));;
        salactbtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/act1.png"))));;
        matactbtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/act1.png"))));;
        matsearchbtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/searchh.png"))));;
        infosmodifbtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/edit.png"))));;
        infossavebtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imgs/save.png"))));;
    }

    public void fenetreAuth(ActionEvent event){
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("auth-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("EDT-authentification");
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    //********************************************PARTIE EDT****************************************************
    public void displayEDT(Event event) {
        deleteOldWeeks();
        today = LocalDate.now();
        while(today.getDayOfMonth() != 2){
            today = today.minusDays(1);
        }
        edtPerWeek(today.toString(), today.plusDays(6).toString());

        codemod.setText("");
        libm.setText("");
        codesalle.setValue(null);
        types.setValue(null);
        codeens.setText("");
        dateseance.setValue(null);
        debuts.setText("");
        fins.setText("");

        codemod.setEditable(false);
        libm.setEditable(false);
        codesalle.setDisable(true);
        types.setDisable(true);
        codeens.setEditable(false);
        dateseance.setDisable(true);
        debuts.setEditable(false);
        fins.setEditable(false);
    }


    public void refreshEDT(MouseEvent mouseEvent) {
        displayEDT(mouseEvent);
    }


    public void nextWeek(MouseEvent mouseEvent) { //affichage de la semaine prochaine
        deleteOldWeeks();
        today = today.plusDays(7);
        edtPerWeek(today.toString(), today.plusDays(6).toString());
    }

    public void prevWeek(MouseEvent mouseEvent) { //affichage de la semaine précédente
        deleteOldWeeks();
        today = today.minusDays(7);
        edtPerWeek(today.toString(), today.plusDays(6).toString());
    }


    private void edtPerWeek(String dated, String datef){ //affichage de l'emploi du temps par semaine par defaut on se met sur la semaine courante
        h = new Utilities();
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            List<Seance> ss = seanceDAO.findbyDate(dated, datef);
            for(Seance s : ss) {
                f = new FlowPane();
                String cours = ModuleDAO.create().findbyId(s.getCodeMod()).getLibelleMod();
                courslabel = new Label(cours);
                courslabel.setFont(new Font("System Italic",14));

                datecours = new Label(s.getDate().toString());
                datecours.setFont(new Font("System Italic",14));

                salle = new Label(s.getCodeSalle());
                salle.setFont(new Font("System Italic",13));

                typesec = new Label(s.getTypeSeance().toString());
                typesec.setFont(new Font("System Italic",14));

                heured = new Label(String.valueOf(s.getHeureD()));
                heured.setFont(new Font("System Italic",14));

                courslabel.setMouseTransparent(true);
                datecours.setMouseTransparent(true);
                typesec.setMouseTransparent(true);
                salle.setMouseTransparent(true);
                heured.setMouseTransparent(true);

                f.getChildren().addAll(courslabel,datecours,salle,typesec,heured);
                f.setHgap(300);
                f.setAlignment(Pos.CENTER);
                edt.setOnMouseClicked(this::clickEDT);
                edt.add(f, h.jourSemaine(s), h.horaireD(s), 1, h.horaireF(s));
                h.typeSeance(s,f);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void clickEDT(MouseEvent mouseEvent) { //récuperer la séance (flowpane) clicker
        String cours = null;
        String date = null;
        int hdebut = 0;
        int row = 0;
        int col = 0;
        //Node clickednode = mouseEvent.getPickResult().getIntersectedNode();
        Node clickednode = (Node) mouseEvent.getTarget();
        if(clickednode instanceof FlowPane){
            row = GridPane.getRowIndex(clickednode);
            col = GridPane.getColumnIndex(clickednode);
        }
        for(Node node : edt.getChildren()){
            if(node instanceof FlowPane){
                if(edt.getRowIndex(node)==row && edt.getColumnIndex(node)==col){
                    cours = ((Label) (((FlowPane) node).getChildren().get(0))).getText();
                    date = ((Label) (((FlowPane) node).getChildren().get(1))).getText();
                    hdebut = Integer.parseInt(((Label) (((FlowPane) node).getChildren().get(4))).getText());
                }
            }
        }
        if (cours!=null){
            dispalyRecapSeance(cours,date,hdebut);
        }
    }



    private void dispalyRecapSeance(String cours, String date, int hdebut) { //affichage d'un résumé d'une séance
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            Module m = ModuleDAO.create().findbyLib(cours);
            Seance s = seanceDAO.recapSeance(m.getCodeMod(),date,hdebut);
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

            codesalle.setValue(s.getCodeSalle());
            codeens.setText(s.getCodeEns());
            dateseance.setValue(LocalDate.parse(s.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            debuts.setText(String.valueOf(s.getHeureD()));
            fins.setText(String.valueOf(s.getHeureF()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //********************************************PARTIE MATIERE****************************************************
    public void displayModule(Event event) { //affichage des modules
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

    public void searchModule(MouseEvent mouseEvent) { //recherche d'un module soit par code ou par libellé
        try(ModuleDAO moduleDAO = ModuleDAO.create()) {
            List<Module> modules = null;
            data = FXCollections.observableArrayList();
            if (choicem.getValue() == "Code"){
                modules = moduleDAO.recapByCode(searchm.getText());
            } else{
                modules = moduleDAO.recapByLib(searchm.getText());
            }
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //********************************************PARTIE SALLE****************************************************
    public void displaySalles(Event event) { //affichage des salles
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

    public void searchChoice(ActionEvent actionEvent) {
        if (choices.getSelectionModel().getSelectedIndex() == 0){
            dates.setVisible(false);
            searchs.setVisible(true);
        } else {
            dates.setVisible(true);
            searchs.setVisible(false);
        }
    }

    public void searchSalle(MouseEvent mouseEvent) { //rechercher une salle
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


    
    public void deleteOldWeeks(){ //supprimer les flowpane contenu dans le gridpane (edt) pour afficher une autre semaine
        ObservableList<Node> c = edt.getChildren();
        int i = c.size();
        while(i-->0){ 
            if(c.get(i) instanceof FlowPane){
                edt.getChildren().remove(c.get(i));
            }
        }
    }

}
