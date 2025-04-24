package exceptions;

public class PointIsNull extends RuntimeException {
    public PointIsNull(String message) {
        super(message);
    }
    public PointIsNull(){super("Un point défini est null");}
}
