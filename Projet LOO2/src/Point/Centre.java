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

    /**
     * Ajoute un point au centre
     * @param p Le point qu'on ajoute
     */
    public void addPoint(Point p) {
        copyListePoints(points, nbPoints + 1);
        points[nbPoints] = p;
        nbPoints += 1;
    }

    /**
     * Copie une liste sur celle ci
     * @param lpt La liste qu'on copie
     * @param nvSize La nouvelle taille de la liste
     */
    public void copyListePoints(Point[] lpt, int nvSize){
        System.out.println(lpt.length);
        points = new Point[nvSize];
        for(int i = 0; i < lpt.length; i++){
            points[i] = lpt[i];
        }
    }

    /**
     * Remplace les valeurs de ce point par celle du point en paramètre
     * @param p
     */
    public void copyPoint(Point p){
        x = p.getX();
        y = p.getY();
        setRGB(p.getR(), p.getG(), p.getB());
    }

    /**
     * Permet de changer le nombre de points 
     * @param v Nouveau nombre de points
     */
    public void setNbPoints(int v) {
        nbPoints = v;
    }

    /**
     * Renvoie le nombre de point du centre
     * @return Le nombre de point du centre
     */
    public int getNbPoints() {
        return nbPoints;
    }

    
    /**
     * Renvoie les points du centre
     * @return La liste de points du centre
     */
    public Point[] getPoints() {
        return points;
    }
    @Override
    public String toString() {
        String s =  "X = " + this.getX() + "\t Y = " + this.getY() + "\n Liste points (" +nbPoints + ") : \n" ;
        for(int i = 0; i < nbPoints; i++){
            s += "\t-" +  points[i] + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Centre c = new Centre(1,20);
        Point p = new Point(0, 0);
        c.setRandomRGB();
        c.addPoint(p);
        c.addPoint(p);
        c.addPoint(p);
        System.out.println(c);
    }
}
