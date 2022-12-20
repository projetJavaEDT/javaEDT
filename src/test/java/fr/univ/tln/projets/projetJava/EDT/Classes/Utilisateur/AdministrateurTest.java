package fr.univ.tln.projets.projetJava.EDT.Classes.Utilisateur;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionAge;
import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionEmail;
import org.junit.jupiter.api.Test;


public class AdministrateurTest {

    @Test
    void nouvelAdministrateur()  throws ExceptionAge, ExceptionEmail {
        Administrateur a = Administrateur.of("admin","admin","admin@admin.fr",20202020,"lkdj",30,"29 avenue colonel picot");
        System.out.println(a);
    }
}
