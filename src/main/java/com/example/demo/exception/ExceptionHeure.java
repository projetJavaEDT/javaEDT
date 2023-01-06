package com.example.demo.exception;

public class ExceptionHeure extends Exception {
    public ExceptionHeure() {
        super("heure debut superiur à heure de fin ! ");
    }
}