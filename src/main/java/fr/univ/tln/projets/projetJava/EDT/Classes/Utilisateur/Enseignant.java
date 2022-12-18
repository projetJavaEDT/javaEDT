package fr.univ.tln.projets.projetJava.EDT.Classes.Utilisateur;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.*;

public class Enseignant extends Utilisateur {
    private String grade ;
    private Enseignant( String nom, String prenom, String email, int tel, String mdp,int age, String adresse, String grade)throws ExceptionAge, ExceptionEmail
    {
        super( nom, prenom, email, tel, mdp, age, adresse);
        this.grade = grade ;
    }
    public static Enseignant of( String nom, String prenom, String email, int tel, String mdp,int age, String adresse, String grade)throws ExceptionAge, ExceptionEmail{
        return new Enseignant( nom, prenom, email, tel, mdp,age, adresse, grade);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

