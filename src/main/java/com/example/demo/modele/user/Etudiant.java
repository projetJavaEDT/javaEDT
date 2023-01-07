package com.example.demo.modele.user;

import com.example.demo.exception.ExceptionEmail;

import java.util.Date;

public class Etudiant extends Utilisateur {
    private String promo ;
    private Etudiant(String nom, String prenom, Date datenaissance, String adresse, String tel, String email, String mdp, String promo)throws ExceptionEmail{
        super(nom, prenom, datenaissance, adresse, tel, email, mdp);
        this.promo = promo ;
    }
    public static Etudiant of(String nom, String prenom, Date datenaissance, String adresse, String tel, String email, String mdp, String promo)throws ExceptionEmail{
        return new Etudiant(nom, prenom, datenaissance, adresse, tel, email, mdp, promo);
    }


    public String getPromo() {
        return this.promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                super.toString()+
                "promo='" + promo + '\'' +
                '}';
    }
}