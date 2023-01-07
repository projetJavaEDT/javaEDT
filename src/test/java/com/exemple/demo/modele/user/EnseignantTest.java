package com.exemple.demo.modele.user;

import com.example.demo.exception.*;


import com.example.demo.modele.user.Enseignant;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class EnseignantTest {
    @Test
    void nouvelEnseignant()throws ExceptionAge, ExceptionEmail{
        Date d = new Date(1997, 11, 19);
        Enseignant e = Enseignant.of("prof","prof",d,"28 boulevard alata","25486978","prof.du@ens.fr","jean","C1");
        System.out.println(e);
    }
}
