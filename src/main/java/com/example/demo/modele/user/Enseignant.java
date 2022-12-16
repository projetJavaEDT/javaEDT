package com.example.demo.modele.user;

public class Enseignant extends Utilisateur {
    private String grade ;
    private Enseignant(int id, String nom, String prenom, String email, int tel, String login, String mdp, String grade)
    {
        super(id, nom, prenom, email, tel, login, mdp);
        this.grade = grade ;
    }
    public static Enseignant of(int id, String nom, String prenom, String email, int tel, String login, String mdp, String grade){
        return new Enseignant(id, nom, prenom, email, tel, login, mdp, grade);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

