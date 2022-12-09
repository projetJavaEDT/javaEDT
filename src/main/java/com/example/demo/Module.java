package com.example.demo;

public class Module {
    private String codeMod ;
    private String libelleMod ;
    private int volumeHoraire ;

    private Module(String codeMod, String libelleMod, int volumeHoraire){
        this.codeMod = codeMod ;
        this.libelleMod = libelleMod ;
        this.volumeHoraire = volumeHoraire ;
    }
    public static Module of(String codeMod, String libelleMod, int volumeHoraire){
            return new Module(codeMod, libelleMod, volumeHoraire);
    }

    public String getCodeMod() {
        return codeMod;
    }

    public void setCodeMod(String codeMod) {
        this.codeMod = codeMod;
    }

    public String getLibelleMod() {
        return libelleMod;
    }

    public void setLibelleMod(String libelleMod) {
        this.libelleMod = libelleMod;
    }

    public int getVolumeHoraire() {
        return volumeHoraire;
    }

    public void setVolumeHoraire(int volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }

    @Override
    public String toString() {
        return "Module{" +
                "codeMod='" + codeMod + '\'' +
                ", libelleMod='" + libelleMod + '\'' +
                ", volumeHoraire=" + volumeHoraire +
                '}';
    }
}
