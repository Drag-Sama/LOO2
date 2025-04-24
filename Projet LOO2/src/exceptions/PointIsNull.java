package exceptions;

/**
 * Exception retournée lorsqu'un point est null dans une situation où il ne devrait pas l'être.
 */
public class PointIsNull extends RuntimeException {
    public PointIsNull(String message) {
        super(message);
    }
    public PointIsNull(){super("Un point défini est null");}
}
