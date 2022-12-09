package fr.univ.tln.projets.projetJava.EDT.Classes;

import java.util.*;

public class Indisponibilite {
    private int id;
    private boolean indisponibilite;
    private Date jour;


    public Indisponibilite(int id, boolean indisponibilite, Date jour) {
        this.id = id;
        this.indisponibilite = indisponibilite;
        this.jour = jour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIndisponibilite() {
        return indisponibilite;
    }

    public void setIndisponibilite(boolean indisponibilite) {
        this.indisponibilite = indisponibilite;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indisponibilite that = (Indisponibilite) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Indisponibilite{" +
                "id=" + id +
                ", indisponibilite=" + indisponibilite +
                ", jour=" + jour +
                '}';
    }
}
