package fr.univ.tln.projets.projetJava.EDT.Classes.Utilisateur;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionAge;
import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionEmail;
import org.junit.jupiter.api.Test;

public class EnseignantTest {
    @Test
    void nouvelEnseignant()throws ExceptionAge, ExceptionEmail{
        Enseignant e = Enseignant.of("prof","prof","prof@ens.fr",202020,"kfjk",20,"28 boulevard saint jean","C1");
        System.out.println(e);
    }
}
