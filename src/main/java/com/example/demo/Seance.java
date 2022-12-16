package com.example.demo;


import java.util.Date;

public class Seance {
    public enum Type {
        TP, TD, CM;
    }
    private String codeMod ;
    private String codeSalle;
    private Type typeSeance;
    private Date date;
    private String heureD;
    private String heureF;

    private Seance(String codeMod, Type typeSeance, Date date, String heureD, String heureF){
        this.codeMod = codeMod ;
        this.typeSeance = typeSeance;
        this.date = date;
        this.heureD = heureD;
        this.heureF = heureF;
    }

    /*private Seance(String codeMod, Type typeSeance, Date date, String heureD, String heureF){
        new Seance(codeMod, null, typeSeance, date, heureD, heureF);
    }*/

    public static Seance of(String codeMod, Type typeSeance, Date date, String heureD, String heureF){
        return new Seance(codeMod, typeSeance, date, heureD, heureF);
    }

    public String getCodeMod() {
        return codeMod;
    }

    public void setCodeMod(String codeMod) {
        this.codeMod = codeMod;
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public Type getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(Type typeSeance) {
        this.typeSeance = typeSeance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeureD() {
        return heureD;
    }

    public void setHeureD(String heureD) {
        this.heureD = heureD;
    }

    public String getHeureF() {
        return heureF;
    }

    public void setHeureF(String heureF) {
        this.heureF = heureF;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "codeMod='" + codeMod + '\'' +
                ", date=" + date +
                ", heureD='" + heureD + '\'' +
                ", heureF='" + heureF + '\'' +
                '}';
    }
}
