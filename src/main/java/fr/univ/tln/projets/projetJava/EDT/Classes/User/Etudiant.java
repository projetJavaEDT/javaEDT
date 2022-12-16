package fr.univ.tln.projets.projetJava.EDT.Classes.User;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionAge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Etudiant extends Utilisateur {
    private String promo ;
    private Etudiant(String nom, String prenom, String email, int tel,String mdp,int age, String adresse, String promo)throws ExceptionAge
    {
        super( nom, prenom, email, tel, mdp, age, adresse);
        this.promo = promo ;
    }
    public static Etudiant of( String nom, String prenom, String email, int tel, String mdp,int age, String adresse, String promo)throws ExceptionAge{
        return new Etudiant( nom, prenom, email, tel, mdp, age, adresse, promo);
    }


    public String getPromo() {
        return this.promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
}
