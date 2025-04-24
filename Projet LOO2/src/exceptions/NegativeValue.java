package exceptions;

/**
 * Exception retournée lorsqu'une valeur est négative dans une situation ou elle ne devrait pas l'être, par exemple en tant que X ou Y
 */
public class NegativeValue extends RuntimeException{
    public NegativeValue(){
        super("Probleme: valeur négative non autorisé");
    }
}
