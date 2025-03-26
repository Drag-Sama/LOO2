package exceptions;


public class NegativeValue extends RuntimeException{
    public NegativeValue(){
        super("Probleme: valeur négative non autorisé");
    }
}
