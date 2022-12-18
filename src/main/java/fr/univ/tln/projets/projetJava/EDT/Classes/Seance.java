package fr.univ.tln.projets.projetJava.EDT.Classes;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.*;
import java.util.*;

import java.util.Date;

public class Seance {
    public enum Type {
        TP, TD, CM;
    }
    private String codeMod ;
    private String codeSalle;
    private Type typeSeance;
    private Date date;
    private int heureD;
    private int heureF;

    public static class SeanceBuilder  {
        private String codeMod ;
        private String codeSalle;
        private Type typeSeance;
        private Date date;
        private int heureD;
        private int heureF;


        public SeanceBuilder codeMod(String codeMod) {
            this.codeMod = codeMod;
            return this;
        }
        public SeanceBuilder codeSalle(String codeSalle) {
            this.codeSalle = codeSalle;
            return this;
        }
        public SeanceBuilder typeSeance(Type typeSeance) {
            this.typeSeance = typeSeance;
            return this;
        }
        public SeanceBuilder date(Date date) {
            this.date = date;
            return this;
        }
        public SeanceBuilder heureD(int heureD) {
            this.heureD = heureD;
            return this;
        }
        public SeanceBuilder heureF(int heureF) {
            this.heureF = heureF;
            return this;
        }


        public Seance build() throws ExceptionHeure {
            Seance seance = new Seance();
            seance.codeMod = codeMod;
            seance.codeSalle = codeSalle;
            if (heureD > heureF)
                throw new ExceptionHeure();
            seance.heureD = heureD;
            seance.heureF = heureF;
            seance.date = date;
            return seance;
        }
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
                ", date=" + date +
                ", heureD='" + heureD + '\'' +
                ", heureF='" + heureF + '\'' +
                '}';
    }


}