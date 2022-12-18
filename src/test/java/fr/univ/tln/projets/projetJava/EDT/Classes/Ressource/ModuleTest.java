package fr.univ.tln.projets.projetJava.EDT.Classes.Ressource;

import org.junit.jupiter.api.Test;


public class ModuleTest {
    @Test
    void nouveauModule(){
        Module mod = Module.of("I141","Projet Collaboratif",50);
        System.out.println("code "+mod.getCodeMod()+" libelle "+mod.getLibelleMod()+" volume horaire "+mod.getVolumeHoraire());
    }
}
