package fr.univ.tln.projets.projetJava.EDT.Classes;

import java.nio.charset.*;
import java.security.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Security {

            private String mdp ;
            // convertir bytes en hexadécimal
            public Security(String mdp){
                this.mdp = mdp ;

            }

            public String hacherMdp(String mdp){

                String s = "" ;
                MessageDigest msg;

                {
                try {
                msg = MessageDigest.getInstance("SHA-256");
                byte[] hash = msg.digest(mdp.getBytes(StandardCharsets.UTF_8));
                for(byte b : hash) {
                    s +=(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                }
                //System.out.println(s.toString());
                return s;
                } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
                }
            }
            }


}
