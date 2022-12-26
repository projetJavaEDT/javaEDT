package com.example.demo.modele.user;

import com.example.demo.exception.ExceptionAge;
import com.example.demo.exception.ExceptionEmail;

import java.util.Date;

public class Administrateur extends Utilisateur {
    private Administrateur(String nom, String prenom, Date datenaissance, String adresse, String tel, String email, String mdp) throws ExceptionEmail {
        super(nom, prenom, datenaissance, adresse, tel, email, mdp);
    }
    public static Administrateur of(String nom, String prenom, Date datenaissance, String adresse, String tel, String email, String mdp)throws ExceptionEmail{
        return new Administrateur(nom, prenom, datenaissance, adresse, tel, email, mdp);
    }
}