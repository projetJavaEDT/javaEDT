package fr.univ.tln.projets.projetJava.EDT.Classes;

public class Etudiant extends Utilisateur{
    private String promo ;
    private Etudiant(int id, String nom, String prenom, String email, int tel, String login, String mdp, String promo)
    {
        super(id, nom, prenom, email, tel, login, mdp);
        this.promo = promo ;
    }
    public static Etudiant of(int id, String nom, String prenom, String email, int tel, String login, String mdp, String promo){
        return new Etudiant(id, nom, prenom, email, tel, login, mdp, promo);
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
