package com.exemple.demo.modele.ressource;

import com.example.demo.modele.ressources.Module;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ModuleTest {
    @Test
    void nouveauModule1(){
        Module mod = Module.of("I111", "JAVA",29);
        System.out.println("code "+mod.getCodeMod()+" libelle "+mod.getLibelleMod()+" volume horaire "+mod.getVolumeHoraire());
    }
    void nouveauModule2(){
        Date dDeb = new Date(2020, 11, 19);
        Date dFin = new Date(2021, 05,  19);
        Module mod = Module.off("I111", "JAVA","dupont.prof@ens.fr",dDeb, dFin, 57 );
        System.out.println("code "+mod.getCodeMod()+" libelle "+mod.getLibelleMod()+" volume horaire "+mod.getVolumeHoraire());
    }
}
