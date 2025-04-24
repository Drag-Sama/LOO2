package exceptions;

/**
 * Exception retournée lorsque tous les points d'un plan sont identiques.
 */
public class IdenticalPoints extends RuntimeException {
    public IdenticalPoints(String message) {
        super(message);
    }
    public IdenticalPoints() {super("Problème : les points donnés sont tous identiques.");}
}
