package com.exemple.demo.modele.ressource;

import com.example.demo.exception.ExceptionNbMaxPlacesSalle;
import com.example.demo.modele.ressources.Indisponibilite;
import com.example.demo.modele.ressources.Salle;
import org.junit.jupiter.api.Test;

import java.util.Date;


public class IndisponibiliteTest {
    @Test
    void nouvelleIndisponibilite() {
        Date dIndispo = new Date(2020, 11, 19);
        Indisponibilite indispo = Indisponibilite.of(dIndispo,"maladie", "dupont.prof@ens.fr");
        System.out.println(indispo);
    }
}


