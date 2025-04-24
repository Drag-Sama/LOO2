package exceptions;

public class TooMuchClusters extends RuntimeException {
    public TooMuchClusters(String message) {
        super(message);
    }
    public TooMuchClusters() {
        super("Le nombre de clusters défini est trop grand.");
    }
}
