package fr.univ.tln.projets.projetJava.EDT.Classes.User;

public class Enseignant extends Utilisateur {
    private String grade ;
    private Enseignant( String nom, String prenom, String email, int tel, String mdp, String grade)
    {
        super( nom, prenom, email, tel,  mdp);
        this.grade = grade ;
    }
    public static Enseignant of( String nom, String prenom, String email, int tel, String mdp, String grade){
        return new Enseignant( nom, prenom, email, tel, mdp, grade);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

