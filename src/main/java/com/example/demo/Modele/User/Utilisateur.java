package com.example.demo.Modele.User;

public abstract class Utilisateur {
    private int id ;
    private String nom ;
    private String prenom ;
    private String email ;
    private int tel ;
    private String login ;
    private String mdp;

    public int getId() {
        return id;
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

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMpd(String mdp) {
        this.mdp = mdp;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    protected Utilisateur (int id, String nom, String prenom, String email, int tel, String login, String mdp)
    {
        this.email = email ;
        this.nom = nom ;
        this.id = id ;
        this.prenom = prenom ;
        this.login = login ;
        this.mdp = mdp ;

    }




}
