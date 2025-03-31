package Point;

import exceptions.NegativeValue;

/**
 * Classe Centre
 * Pour éviter de rechercher quels points sont attribués à quel cluster.
 * @param points les points attribués à ce centre
 */
public class Centre extends Point {
    private Point[] points;

    /**
     * Crée un point avec les coordonnées X,Y
     *
     * @param newX la coordonnée X
     * @param newY la coordonnée Y
     */
    public Centre(float newX, float newY) throws NegativeValue {
        super(newX, newY);
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }
}
