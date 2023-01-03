package com.example.demo.controleur;

import com.example.demo.modele.ressources.Seance;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.text.SimpleDateFormat;

public class Help {

    public void typeSeance(Seance s, FlowPane f){
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


    public int horaireD(Seance s){
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
                heured = 7;
                break;
            case 14:
                heured = 8;
                break;
            case 15:
                heured = 9;
                break;
            case 16:
                heured = 10;
                break;
            case 17:
                heured = 11;
                break;
        }
        return heured;
    }


    public int horaireF(Seance s){
        int duree = s.getHeureF() - s.getHeureD();
        return duree+1;
    }

    public int jourSemaine(Seance s){
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


    public void deleteOldWeeks(GridPane grid){
        ObservableList<Node> c = grid.getChildren();
        int i = c.size();
        while(i-->0){ //je peux mettre 17 psk les composantes FlowPane commencent à partir de l'indice 18
            if(c.get(i) instanceof FlowPane){
                grid.getChildren().remove(c.get(i));
            }
        }
    }
}
