package com.example.demo.modele.ressources;




import javafx.scene.control.DatePicker;

        import java.util.*;

public class Indisponibilite {
    private String id;
    private Date jour;
    private String remarque ;
    private String heure;
    private String mail;


    private Indisponibilite(Date jour,String heure,String remarque, String mail) {
        this.mail = mail;
        this.heure = heure;
        this.remarque = remarque;
        this.jour = jour;
        this.id = this.mail+this.jour;
    }
    public static Indisponibilite of(Date jour,String heure,String remarque, String mail){
        return new Indisponibilite(jour, heure, remarque, mail);
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = this.mail + this.jour;

    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}