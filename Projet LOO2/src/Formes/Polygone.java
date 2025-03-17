package Formes;

import java.util.Arrays;

import Point.Point;

public class Polygone extends Formes{
    private int nbPoints = 0;
    private Point[] points = new Point[50];
    private Point[] pointsOrdre = new Point[50];

    public Polygone(Point centre){
        this.setCentre(centre);
        this.setRandomRGB();
        System.out.println("yay");
    }
    

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
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
        this.points[nbPoints] = newPoint;
        this.nbPoints += 1;
    }

    /** Enlève un point de la liste de points
     * 
     * @param point
     * @return true si on a pu retirer le point, false sinon.
     */
    public void removePoint(Point point){
        int j = 0;
        for(int i = 0; i < nbPoints; i++){
            if(points[i] != point){
                points[j++] = points[i]; 
            }
        }
        points = Arrays.copyOf(points, j);
    }

    public static void main(String[] args) {
        Point[] testPoints =new Point[50];
        Point centre = new Point(0, 0);
        Polygone a = new Polygone(centre);
        testPoints = a.getPoints();
        a.setPoints(testPoints);
        int testNbPoints = a.getNbPoints();
        a.setNbPoints(testNbPoints);
        Point point = new Point(1,2);
        a.addPoint(point);
        a.removePoint(point);
    }
}
