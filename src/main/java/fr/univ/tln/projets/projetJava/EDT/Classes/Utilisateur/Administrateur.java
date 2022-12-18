package fr.univ.tln.projets.projetJava.EDT.Classes.Utilisateur;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.*;

public class Administrateur extends Utilisateur {
    private Administrateur( String nom, String prenom, String email, int tel, String mdp, int age, String adresse) throws ExceptionAge, ExceptionEmail {
        super( nom, prenom, email, tel,  mdp, age, adresse);
    }
    public static Administrateur of( String nom, String prenom, String email, int tel, String mdp, int age, String adresse)throws ExceptionAge, ExceptionEmail{
        return new Administrateur( nom, prenom, email, tel,  mdp, age, adresse);
    }
}
