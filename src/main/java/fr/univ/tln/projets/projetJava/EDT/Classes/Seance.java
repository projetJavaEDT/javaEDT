package fr.univ.tln.projets.projetJava.EDT.Classes;


import java.util.*;

public class Seance {

    private int id;
    private String type;
    private Date jour;
    private int heureDebut;
    private int heureFin;

    public Seance(int id, String type, Date jour, int heureDebut, int heureFin) {
        this.id = id;
        this.type = type;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return id == seance.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", jour=" + jour +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                '}';
    }
}
