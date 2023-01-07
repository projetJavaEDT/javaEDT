package com.exemple.demo.modele.ressource;


import com.example.demo.exception.*;
import com.example.demo.modele.ressources.Salle;
import org.junit.jupiter.api.Test;

public class SalleTest {
    @Test
    void nouvelleSalle() throws ExceptionNbMaxPlacesSalle {
        Salle s = Salle.of("U001","10", 25);
        System.out.println(s);
    }
}
