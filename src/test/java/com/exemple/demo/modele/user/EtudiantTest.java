package com.exemple.demo.modele.user;

import com.example.demo.exception.* ;

import com.example.demo.modele.user.Etudiant;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class EtudiantTest {
    @Test
    void nouvelEtudiant()throws  ExceptionAge, ExceptionEmail{
        Date d = new Date(1997, 11, 19);
        Etudiant et = Etudiant.of("ben ", "rawia",d ,"28 boulevard alata","23054690","rawia.ben@etud.fr","hgtu","M1");
        System.out.println(et);

    }
}
