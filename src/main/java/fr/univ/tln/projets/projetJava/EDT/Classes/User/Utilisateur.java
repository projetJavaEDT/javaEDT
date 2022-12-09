package fr.univ.tln.projets.projetJava.EDT.Classes.User;

public abstract class Utilisateur {

    private String nom ;
    private String prenom ;
    private String email ;
    private int tel ;
    private String mdp;
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public int getTel() {
        return tel;
    }
    public String getMdp() {
        return mdp;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMpd(String mdp) {
        this.mdp = mdp;
    }
    public void setTel(int tel) {
        this.tel = tel;
    }
    protected Utilisateur ( String nom, String prenom, String email, int tel, String mdp)
    {
        this.email = email ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.mdp = mdp ;

    }




}
