package com.example.demo.modele.ressources;


public class Salle {
    private String codeSalle ;
    private String depart ;
    private int nbPlace;

    private Salle(String codeSalle, String depart, int nbPlace){
        this.codeSalle = codeSalle ;
        this.depart = depart;
        this.nbPlace = nbPlace ;
    }
    public static Salle of(String codeSalle, String depart , int nbPlace) {
        return new Salle(codeSalle, depart, nbPlace);
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "codeSalle='" + codeSalle + '\'' +
                ", depart='" + depart + '\'' +
                ", nbPlace=" + nbPlace +
                '}';
    }
}
