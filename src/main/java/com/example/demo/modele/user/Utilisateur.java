package com.example.demo.modele.user;

import com.example.demo.exception.ExceptionEmail;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utilisateur {

    private String nom ;
    private String prenom ;
    private Date datenaissance ;
    private String adresse ;
    private String tel ;
    private String email ;
    private String mdp;


    protected Utilisateur (String nom, String prenom, Date datenaissance, String adresse, String tel, String email, String mdp) throws ExceptionEmail{
        String regex = "^[a-zA-Z0-9]{0,30}[_.-]{0,10}[a-zA-Z0-9]{0,30}[@][a-z]{3,5}[.][f][r]$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(email);
        if(!match.matches())
            throw new ExceptionEmail();

        this.nom = nom ;
        this.prenom = prenom ;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email ;
        this.mdp = mdp ;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", datenaissance=" + datenaissance +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}