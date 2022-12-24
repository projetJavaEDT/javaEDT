package fr.univ.tln.projets.projetJava.EDT.Classes.Ressource;

import fr.univ.tln.projets.projetJava.EDT.Exceptions.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class SeanceTest {
    @Test
    void nouvelleSeance()throws ExceptionHeure{
        Date dateT = new Date(2020,05,22);
        Seance s = new Seance.SeanceBuilder().typeSeance(Seance.Type.TD).codeMod("I111").codeSalle("U001").heureD(14).heureF(15).date(dateT).build();
        System.out.println(s);
    }

}

