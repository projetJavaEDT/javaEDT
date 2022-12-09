package fr.univ.tln.projets.projetJava.EDT.Classes;

import java.nio.charset.*;
import java.security.*;

public class Security {

            String str = "WayToLearnX";
            // convertir bytes en hexadécimal
            StringBuilder s = new StringBuilder();
            MessageDigest msg;

    {
        try {
            msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(str.getBytes(StandardCharsets.UTF_8));
            for(byte b : hash) {
                s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println(s.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



}
