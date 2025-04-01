package exceptions;

public class IdenticalPoints extends RuntimeException {
    public IdenticalPoints(String message) {
        super(message);
    }
    public IdenticalPoints() {super("Problème : les points donnés sont identiques.");}
}
