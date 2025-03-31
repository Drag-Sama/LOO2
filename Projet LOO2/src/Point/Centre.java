package Point;

import exceptions.NegativeValue;

/**
 * Classe Centre
 * Pour éviter de rechercher quels points sont attribués à quel cluster.
 * @param points les points attribués à ce centre
 * @param nbPoints le nombre de points attribués à ce centre
 */
public class Centre extends Point {
    private Point[] points = new Point[0];
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
        copyPoints(points, nbPoints + 1);
        points[nbPoints] = p;
        nbPoints += 1;
    }

    public void copyPoints(Point[] lpt, int nvSize){
        System.out.println(lpt.length);
        points = new Point[nvSize];
        for(int i = 0; i < lpt.length; i++){
            points[i] = lpt[i];
        }
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
    @Override
    public String toString() {
        String s =  "X = " + this.getX() + "\t Y = " + this.getY() + "\n Liste points : ";
        for(int i = 0; i < nbPoints; i++){
            s += points[i] + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Centre c = new Centre(1,20);
        Point p = new Point(0, 0);
        c.setRandomRGB();
        c.addPoint(p);
        System.out.println(c);
    }
}
