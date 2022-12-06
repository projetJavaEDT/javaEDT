package fr.univ.tln.projets.projetJava.EDT.Classes;

import org.graalvm.compiler.replacements.StandardGraphBuilderPlugins;

public class Seance {
    private int id ;
    private String libelleSalle ;
    private int nbPlace;
    private ClasseType classeType ;

    private Seance(int id, String libelleSalle, int nbPlace, ClasseType classeType){
        this.classeType = classeType;
        this.id = id ;
        this.nbPlace = nbPlace ;
        this.libelleSalle = libelleSalle ;
    }
    public static Seance of(int id, String libelleSalle, int nbPlace, ClasseType classeType) {
        return new Seance(id, libelleSalle, nbPlace, classeType);
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
