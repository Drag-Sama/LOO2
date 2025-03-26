package exceptions;


public class NegativeValue extends Exception{
    public NegativeValue(){
        System.err.println("Probleme: valeur négative non autorisé");
    }


}
