package fr.univ.tln.projets.projetJava.EDT;

import fr.univ.tln.projets.projetJava.EDT.Classes.*;


public class App 
{
    public static void main( String[] args )
    {
        String str = "prof";
        String email = "rawiabenamira@gmail.fr";
        Security sec = new Security(str);



        String x ="9bc2e1d2b5bcdababaedf20ddf58f004c495b235cbf2d18bcbbc528d2b67fc4d";
        String y = sec.hacherMdp(str);
        System.out.println(y);



        EDTApplication.main(args);
    }
}
