package fr.univ.tln.projets.projetJava.EDT.Exceptions;

public class ExceptionHeureSeance extends Exception{
    public ExceptionHeureSeance(){
        super("Heure de debut superieur à heure de fin! ");
    }
}
