package fr.univ.tln.projets.projetJava.EDT.Classes.Ressource;

import fr.univ.tln.projets.projetJava.EDT.Classes.ClasseType;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.*;

public class Salle {
    private int id ;
    private String libelleSalle ;
    private int nbPlace;
    private ClasseType classeType ;

    private Salle(int id, String libelleSalle, int nbPlace, ClasseType classeType) throws ExceptionNbMaxPlacesSalle{
        this.classeType = classeType;
        this.id = id ;
        this.nbPlace = nbPlace ;
        if (nbPlace > 30 )
                throw new ExceptionNbMaxPlacesSalle();
        this.libelleSalle = libelleSalle ;
    }
    public static Salle of(int id, String libelleSalle, int nbPlace, ClasseType classeType) throws ExceptionNbMaxPlacesSalle {
        return new Salle(id, libelleSalle, nbPlace, classeType);
    }

    public int getId() {
        return id;
    }

    public ClasseType getClasseType() {
        return classeType;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public String getLibelleSalle() {
        return libelleSalle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClasseType(ClasseType classeType) {
        this.classeType = classeType;
    }

    public void setLibelleSalle(String libelleSalle) {
        this.libelleSalle = libelleSalle;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
}
