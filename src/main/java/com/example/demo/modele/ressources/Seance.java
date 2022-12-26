package com.example.demo.modele.ressources;


import java.util.Date;

public class Seance {
    public enum Type {
        TP, TD, CM;
    }
    private String codeMod ;
    private String codeSalle;
    private String codeEns;
    private Type typeSeance;
    private Date date;
    private int heureD;
    private int heureF;

    private Seance(String codeMod, String codeSalle, String codeEns, Type typeSeance, Date date, int heureD, int heureF){
        this.codeMod = codeMod ;
        this.codeSalle = codeSalle;
        this.codeEns = codeEns;
        this.typeSeance = typeSeance;
        this.date = date;
        this.heureD = heureD;
        this.heureF = heureF;
    }


    public static Seance of(String codeMod, String codeSalle, String codeEns, Type typeSeance, Date date, int heureD, int heureF){
        return new Seance(codeMod, codeSalle, codeEns, typeSeance, date, heureD, heureF);
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

    public String getCodeEns() {
        return codeEns;
    }

    public void setCodeEns(String codeEns) {
        this.codeEns = codeEns;
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

    public int getHeureD() {
        return heureD;
    }

    public void setHeureD(int heureD) {
        this.heureD = heureD;
    }

    public int getHeureF() {
        return heureF;
    }

    public void setHeureF(int heureF) {
        this.heureF = heureF;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "codeMod='" + codeMod + '\'' +
                ", codeSalle='" + codeSalle + '\'' +
                ", codeEns='" + codeEns + '\'' +
                ", typeSeance=" + typeSeance +
                ", date=" + date +
                ", heureD=" + heureD +
                ", heureF=" + heureF +
                '}';
    }
}
