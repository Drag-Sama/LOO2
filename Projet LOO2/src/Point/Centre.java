package Point;

import exceptions.NegativeValue;

/**
 * Classe Centre
 * Pour éviter de rechercher quels points sont attribués à quel cluster.
 * @param points les points attribués à ce centre
 * @param nbPoints le nombre de points attribués à ce centre
 */
public class Centre extends Point {
    private Point[] points;
    private int nbPoints;

    /**
     * Crée un point avec les coordonnées X,Y
     *
     * @param newX la coordonnée X
     * @param newY la coordonnée Y
     */
    public Centre(float newX, float newY) throws NegativeValue {
        super(newX, newY);
    }

    public void addPoint(Point p) {
        points.
    }

    public void setNbPoints(int v) {
        nbPoints = v;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }
}
