package fr.univ.tln.projets.projetJava.EDT.Classes.User;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionAge;

public class Administrateur extends Utilisateur {
    private Administrateur( String nom, String prenom, String email, int tel, String mdp, int age, String adresse) throws ExceptionAge {
        super( nom, prenom, email, tel,  mdp, age, adresse);
    }
    public static Administrateur of( String nom, String prenom, String email, int tel, String mdp, int age, String adresse)throws ExceptionAge{
        return new Administrateur( nom, prenom, email, tel,  mdp, age, adresse);
    }
}
