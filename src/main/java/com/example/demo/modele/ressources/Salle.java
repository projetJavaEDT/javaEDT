package com.example.demo.modele.ressources;


public class Salle {
    private String codeSalle ;
    private int nbPlace;

    private Salle(String codeSalle, int nbPlace){
        this.codeSalle = codeSalle ;
        this.nbPlace = nbPlace ;
    }
    public static Salle of(String codeSalle, int nbPlace) {
        return new Salle(codeSalle, nbPlace);
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
}
