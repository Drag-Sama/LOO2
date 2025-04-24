package exceptions;

/**
 * Exception retournée lorsque le nombre de clusters est trop grand par rapport au nombre de points (à partir de N-2, avec N nombre de points)
 */
public class TooMuchClusters extends RuntimeException {
    public TooMuchClusters(String message) {
        super(message);
    }
    public TooMuchClusters() {
        super("Le nombre de clusters défini est trop grand.");
    }
}
