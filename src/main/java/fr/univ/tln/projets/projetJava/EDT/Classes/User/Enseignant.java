package fr.univ.tln.projets.projetJava.EDT.Classes.User;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionAge;

public class Enseignant extends Utilisateur {
    private String grade ;
    private Enseignant( String nom, String prenom, String email, int tel, String mdp,int age, String adresse, String grade)throws ExceptionAge
    {
        super( nom, prenom, email, tel, mdp, age, adresse);
        this.grade = grade ;
    }
    public static Enseignant of( String nom, String prenom, String email, int tel, String mdp,int age, String adresse, String grade)throws ExceptionAge{
        return new Enseignant( nom, prenom, email, tel, mdp,age, adresse, grade);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

