package exceptions;

/**
 * Exception retournée lorsqu'une forme est nulle dans une situation où elle ne devrait pas l'être.
 */
public class FormeIsNull extends RuntimeException {
    public FormeIsNull(String message) {
        super(message);
    }
}
