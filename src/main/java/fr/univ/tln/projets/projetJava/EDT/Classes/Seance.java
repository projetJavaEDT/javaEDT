package fr.univ.tln.projets.projetJava.EDT.Classes;


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
    private String heureD;
    private String heureF;



    public static class SeanceBuilder{
        private String codeMod ;
        private String codeSalle;
        private Type typeSeance;
        private Date date;
        private String heureD;
        private String heureF;


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
        public SeanceBuilder heureD(String heureD) {
            this.heureD = heureD;
            return this;
        }
        public SeanceBuilder heureF(String heureF) {
            this.heureF = heureF;
            return this;
        }


        public Seance build() {
            Seance seance = new Seance();
            seance.codeMod = codeSalle;
            seance.codeSalle = codeSalle;
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