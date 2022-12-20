package fr.univ.tln.projets.projetJava.EDT.Classes.Utilisateur;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionAge;
import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionEmail;
import org.junit.jupiter.api.Test;

public class EtudiantTest {
    @Test
    void nouvelEtudiant()throws  ExceptionAge, ExceptionEmail{
        Etudiant et = Etudiant.of("ben ", "rawia","rawia.ben@etud.fr",250789456,"rawi",25,"avenue du 15 eme corps","M1");
        System.out.println(et);
    }
}
