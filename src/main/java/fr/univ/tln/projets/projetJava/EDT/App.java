package fr.univ.tln.projets.projetJava.EDT;

import fr.univ.tln.projets.projetJava.EDT.Classes.*;
import fr.univ.tln.projets.projetJava.EDT.DAO.*;


import java.util.*;
import java.sql.*;


public class App 
{
    public static void main( String[] args )
    {
        String str = "rawiamariamedorsaf";
        String email = "rawiabenamira@gmail.fr";
        Security sec = new Security(str, email);

        String x ="9bc2e1d2b5bcdababaedf20ddf58f004c495b235cbf2d18bcbbc528d2b67fc4d";
        String y = sec.hacherMdp(str);

        if(sec.hacherMdp(str).equals(x)){
            System.out.println( "Hello World!" );
        }
        System.out.println(sec.verifieEmail(email));
    }
}
