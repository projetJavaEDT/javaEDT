package com.example.demo.modele.DAO;

import java.sql.SQLException;

public interface VerificationMailMdp {
    boolean validate(String email, String password) throws SQLException;
}
