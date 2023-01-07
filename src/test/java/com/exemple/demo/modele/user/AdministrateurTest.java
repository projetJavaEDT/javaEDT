package com.exemple.demo.modele.user;

import com.example.demo.exception.*;
import com.example.demo.modele.user.Administrateur;
import org.junit.jupiter.api.Test;

import java.util.Date;


public class AdministrateurTest {

    @Test
    void nouvelAdministrateur()  throws ExceptionAge, ExceptionEmail {
        Date d = new Date(1997, 11, 19);
        Administrateur a = Administrateur.of("admin","admin",d,"15 pont de bois","24089751","admin.admin@admin.fr","29 avenue colonel picot");
        System.out.println(a);
    }
}
