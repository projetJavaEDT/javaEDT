package fr.univ.tln.projets.projetJava.EDT.Classes.Ressource;

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

    public void setCodeMod(String codeMod) {
        this.codeMod = codeMod;
    }

    public void setLibelleMod(String libelleMod) {
        this.libelleMod = libelleMod;
    }

    public void setVolumeHoraire(int volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }

    public int getVolumeHoraire() {
        return volumeHoraire;
    }

    public String getCodeMod() {
        return codeMod;
    }

    public String getLibelleMod() {
        return libelleMod;
    }
}
