package Formes;

import java.util.LinkedHashSet;

import Point.Point;

public class Aleatoire extends Formes{
    private int nbPoints = 0;
    private LinkedHashSet<Point> points = new LinkedHashSet<Point>();

    public Aleatoire(Point centre){
        this.setCentre(centre);
    }
    

    public LinkedHashSet<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedHashSet<Point> points) {
        this.points = points;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    /** Ajoute un point à la liste de points
     * 
     * @param newPoint
     */
    public void addPoint(Point newPoint){
        this.points.add(newPoint);
        this.nbPoints += 1;
    }

    /** Enlève un point de la liste de points
     * 
     * @param point
     * @return true si on a pu retirer le point, false sinon.
     */
    public boolean removePoint(Point point){
        if(this.points.remove(point)) {
            this.nbPoints -= 1;
            return true;
        }
        return false; 
    }

    public static void main(String[] args) {
        LinkedHashSet<Point> testPoints = new LinkedHashSet<Point>();
        Point centre = new Point(0, 0);
        Aleatoire a = new Aleatoire(centre);
        testPoints = a.getPoints();
        a.setPoints(testPoints);
        int testNbPoints = a.getNbPoints();
        a.setNbPoints(testNbPoints);
        Point point = new Point(1,2);
        a.addPoint(point);
        a.removePoint(point);
    }
}
