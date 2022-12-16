package fr.univ.tln.projets.projetJava.EDT.Classes.User;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.*;

public abstract class Utilisateur {

    private String nom ;
    private String prenom ;
    private String email ;
    private int tel ;
    private String mdp;
    private String adresse ;
    private int age ;

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getAge() {
        return age;
    }

    public String getAdresse() {
        return adresse;
    }

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
    protected Utilisateur (String nom, String prenom, String email, int tel, String mdp, int age, String adresse )throws ExceptionAge
    {
        if (age < 0)
            throw new ExceptionAge();
        this.email = email ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.mdp = mdp ;
        this.adresse = adresse;
        this.age= age ;
    }




}
