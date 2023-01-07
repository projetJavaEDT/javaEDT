package com.example.demo.controleur;

import com.example.demo.modele.ressources.Seance;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Help {

    public static String validateEmailRegex(String email){
        String regexEt = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][e][t][u][d][.][f][r]$";
        String regexEn = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][e][n][s][.][f][r]$";
        String regexAd = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][a][d][m][i][n][.][f][r]$";

        Pattern pEt = Pattern.compile(regexEt);
        Pattern pEn = Pattern.compile(regexEn);
        Pattern pAd = Pattern.compile(regexAd);

        Matcher matchEt = pEt.matcher(email);
        Matcher matchEn = pEn.matcher(email);
        Matcher matchAd = pAd.matcher(email);
        if(matchEt.matches())
            return "etud";
        else if(matchEn.matches())
            return "ens";
        else if (matchAd.matches())
            return "admin";
        else
            return "not exist";
    }


    public String hacherMdp(String mdp){
        String s = "" ;
        MessageDigest msg;
        try {
            msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(mdp.getBytes(StandardCharsets.UTF_8));
            for(byte b : hash) {
                s += (Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return s;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void typeSeance(Seance s, FlowPane f){
        switch(s.getTypeSeance()){
            case CM:
                f.setBackground(Background.fill(Color.web("#23b0b8")));
                break;
            case TP:
                f.setBackground(Background.fill(Color.web("fec5d9")));
                break;
            case TD:
                f.setBackground(Background.fill(Color.web("b0c4de")));
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


    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.show();
    }
}
