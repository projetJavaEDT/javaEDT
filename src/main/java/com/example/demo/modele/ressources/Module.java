package com.example.demo.modele.ressources;

import java.util.Date;

public class Module {
    private String codeMod ;
    private String libelleMod ;
    private  String enseignant;
    private Date dateDebut;
    private Date dateFin;
    private int volumeHoraire ;

    private Module(String codeMod, String libelleMod, int volumeHoraire){
        this.codeMod = codeMod ;
        this.libelleMod = libelleMod ;
        this.volumeHoraire = volumeHoraire ;
    }


    private Module(String codeMod, String libelleMod, String enseignant, Date dateDebut, Date dateFin, int volumeHoraire){
        this.codeMod = codeMod ;
        this.libelleMod = libelleMod ;
        this.enseignant = enseignant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.volumeHoraire = volumeHoraire ;
    }


    public static Module of(String codeMod, String libelleMod, int volumeHoraire){
        return new Module(codeMod, libelleMod, volumeHoraire);
    }

    public static Module off(String codeMod, String libelleMod, String enseignant, Date dateDebut, Date dateFin, int volumeHoraire){
        return new Module(codeMod, libelleMod, enseignant, dateDebut, dateFin, volumeHoraire);
    }

    public String getCodeMod() {
        return codeMod;
    }

    public void setCodeMod(String codeMod) {
        this.codeMod = codeMod;
    }

    public String getLibelleMod() {
        return libelleMod;
    }

    public void setLibelleMod(String libelleMod) {
        this.libelleMod = libelleMod;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getVolumeHoraire() {
        return volumeHoraire;
    }

    public void setVolumeHoraire(int volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }

    @Override
    public String toString() {
        return "Module{" +
                "codeMod='" + codeMod + '\'' +
                ", libelleMod='" + libelleMod + '\'' +
                ", enseignant='" + enseignant + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", volumeHoraire=" + volumeHoraire +
                '}';
    }
}
