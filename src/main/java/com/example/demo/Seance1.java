package com.example.demo;


public class Seance1 {

    private String codeMod ;
    private String jour ;
    private String horaires  ;

    private Seance1(String codeMod, String jour, String horaires){
        this.codeMod = codeMod ;
        this.jour = jour ;
        this.horaires = horaires ;
    }
    public static Seance1 of(String codeMod, String jour, String horaires){
        return new Seance1(codeMod, jour, horaires);
    }

    public String getCodeMod() {
        return codeMod;
    }

    public void setCodeMod(String codeMod) {
        this.codeMod = codeMod;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "codeMod='" + codeMod + '\'' +
                ", jour=" + jour +
                ", horaires='" + horaires + '\'' +
                '}';
    }
}
