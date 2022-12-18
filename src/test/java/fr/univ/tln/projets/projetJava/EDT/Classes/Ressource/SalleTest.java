package fr.univ.tln.projets.projetJava.EDT.Classes.Ressource;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.ExceptionNbMaxPlacesSalle;
import org.junit.jupiter.api.Test;

public class SalleTest {
    @Test
    void nouvelleSalle() throws ExceptionNbMaxPlacesSalle {
        Salle s = Salle.of(10,"U10", 25, Salle.ClasseType.AMPHI);
        System.out.println(s);
    }
}