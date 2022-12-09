package fr.univ.tln.projets.projetJava.EDT.Classes.User;

public class Administrateur extends Utilisateur {
    private Administrateur( String nom, String prenom, String email, int tel, String mdp){
        super( nom, prenom, email, tel,  mdp);
    }
    public static Administrateur of( String nom, String prenom, String email, int tel, String mdp){
        return new Administrateur( nom, prenom, email, tel,  mdp);
    }
}
