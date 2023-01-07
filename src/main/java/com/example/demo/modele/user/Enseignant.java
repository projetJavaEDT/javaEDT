package com.example.demo.modele.user;

import com.example.demo.exception.ExceptionAge;
import com.example.demo.exception.ExceptionEmail;

import java.util.Date;

public class Enseignant extends Utilisateur {
    private String grade ;
    private Enseignant(String nom, String prenom, Date datenaissance, String adresse, String tel, String email, String mdp, String grade)throws ExceptionEmail{
        super(nom, prenom, datenaissance, adresse, tel, email, mdp);
        this.grade = grade ;
    }
    public static Enseignant of(String nom, String prenom, Date datenaissance, String adresse, String tel, String email, String mdp, String grade)throws ExceptionEmail{
        return new Enseignant(nom, prenom, datenaissance, adresse, tel, email, mdp, grade);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                super.toString()+
                "grade='" + grade + '\'' +
                '}';
    }
}
