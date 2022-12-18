package fr.univ.tln.projets.projetJava.EDT.Exceptions;

public class ExceptionHeure extends Exception {
    public ExceptionHeure() {
        super("heure debut superiur à heure de fin ! ");
    }
}