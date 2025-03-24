package exceptions;

import Formes.Ellipse;

public class NegativeValueEllipse extends Exception{
    public NegativeValueEllipse(Ellipse ellipse){
        System.err.println("Probleme: valeur négative dans ellipse");
    }


}
