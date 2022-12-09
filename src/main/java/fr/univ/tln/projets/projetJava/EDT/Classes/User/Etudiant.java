package fr.univ.tln.projets.projetJava.EDT.Classes.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Etudiant extends Utilisateur {
    private String promo ;
    private Etudiant(String nom, String prenom, String email, int tel,String mdp, String promo)
    {
        super( nom, prenom, email, tel, mdp);
        this.promo = promo ;
    }
    public static Etudiant of( String nom, String prenom, String email, int tel, String mdp, String promo){
        return new Etudiant( nom, prenom, email, tel, mdp, promo);
    }

    @Override
    public String getPrenom() {
        return super.getPrenom();
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom);
    }
}
