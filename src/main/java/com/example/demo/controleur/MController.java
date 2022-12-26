package com.example.demo.controleur;


import com.example.demo.modele.ressources.Module;
import com.example.demo.modele.ressources.Seance;
import com.example.demo.modele.DAO.ModuleDAO;
import com.example.demo.modele.DAO.SeanceDAO;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MController {
    @FXML
    private ColumnConstraints lundicolonne;
    @FXML
    private GridPane edt;
    @FXML
    private Label code;
    @FXML
    private Label code1;
    @FXML
    private TextField codem;
    @FXML
    private TextField libmod;
    @FXML
    private TextField typesc;
    @FXML
    private TextField datesc;
    @FXML
    private TextField dureesc;
    private FlowPane f;
    private Label courslabel;


    private void typeSeance(Seance s){
        switch(s.getTypeSeance()){
            case CM:
                f.setBackground(Background.fill(Color.LIGHTPINK));
                break;
            case TP:
                f.setBackground(Background.fill(Color.LIGHTBLUE));
                break;
            case TD:
                f.setBackground(Background.fill(Color.LIGHTGREEN));
                break;
        }
    }

    private int horaireF(Seance s){
        int heured = 0;
        switch(s.getHeureF()){
            case 8:
                heured = 1;
                break;
            case 9:
                heured = 2;
                break;
            case 10:
                heured = 3;
                break;
            case 11:
                heured = 4;
                break;
            case 12:
                heured = 5;
                break;
            case 13:
                heured = 6;
                break;
            case 14:
                heured = 7;
                break;
            case 15:
                heured = 8;
                break;
            case 16:
                heured = 9;
                break;
            case 17:
                heured = 10;
                break;
        }
        return heured;
    }


    private int horaireD(Seance s){
        int heured = 0;
        switch(s.getHeureD()){
            case 8:
                heured = 1;
                break;
            case 9:
                heured = 2;
                break;
            case 10:
                heured = 3;
                break;
            case 11:
                heured = 4;
                break;
            case 12:
                heured = 5;
                break;
            case 13:
                heured = 6;
                break;
            case 14:
                heured = 7;
                break;
            case 15:
                heured = 8;
                break;
            case 16:
                heured = 9;
                break;
            case 17:
                heured = 10;
                break;
        }
        return heured;
    }

    private int jourSemaine(Seance s){
        SimpleDateFormat date = new SimpleDateFormat("EEEE");
        int ijour = 0;
        switch (date.format(s.getDate())){
            case "lundi":
                ijour = 1;
                break;
            case "mardi":
                ijour = 2;
                break;
            case "mercredi":
                ijour = 3;
                break;
            case "jeudi":
                ijour = 4;
                break;
            case "vendredi":
                ijour = 5;
                break;
        }
        return ijour;
    }

    @FXML
    protected void displaySeance() {
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            List<Seance> seances = seanceDAO.findAll();
            for(Seance s : seances){
                f = new FlowPane();
                String cours = String.valueOf(ModuleDAO.create().findById(s.getCodeMod()).getLibelleMod());
                courslabel = new Label(cours);
                courslabel.setAlignment(Pos.CENTER);
                f.getChildren().add(courslabel);
                courslabel.setOnMouseClicked(this::dispalyRecap);
                edt.add(f,jourSemaine(s),horaireD(s),1,horaireF(s)-1);
                typeSeance(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void dispalyRecap(MouseEvent mouseEvent) {
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            Module m = ModuleDAO.create().findByMod(courslabel.getText());
            Seance s = seanceDAO.findById(m.getCodeMod());
            codem.setText(m.getCodeMod());
            libmod.setText(m.getLibelleMod());
            typesc.setText(s.getTypeSeance().toString());
            datesc.setText(s.getDate().toString());
            dureesc.setText(String.valueOf(m.getVolumeHoraire()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}