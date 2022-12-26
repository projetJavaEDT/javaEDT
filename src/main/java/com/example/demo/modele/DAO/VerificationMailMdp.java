package com.example.demo.modele.DAO;

import com.example.demo.exception.ExceptionAge;
import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.user.Administrateur;

import java.sql.SQLException;

public interface VerificationMailMdp {
    boolean validate(String email, String password) throws SQLException;
}
