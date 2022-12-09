package com.example.demo.Modele.User;

public class Administrateur extends Utilisateur {
    private Administrateur(int id, String nom, String prenom, String email, int tel, String login, String mdp){
        super(id, nom, prenom, email, tel, login, mdp);
    }
    public static Administrateur of(int id, String nom, String prenom, String email, int tel, String login, String mdp){
        return new Administrateur(id, nom, prenom, email, tel, login, mdp);
    }
}
