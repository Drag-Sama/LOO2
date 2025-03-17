package Formes;

import java.util.Arrays;
import java.util.HashSet;

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

    /**
     * Permet de mettre les points du polygone dans l'ordre selon l'angle avec leur centre
     */
    public void triPoints(){
        //On calcule le centre des points
        int moyX = 0, moyY = 0;
        for(int i = 0; i < nbPoints; i++){ // pour tous les points du polygone
            moyX += points[i].getX();
            moyY += points[i].getY();
        }
        moyY  = moyY/nbPoints;
        moyX = moyX/nbPoints;

        //On calcule l'angle des points avec le centre 
        Double[] angle = new Double[nbPoints];

        for(int i = 0; i < nbPoints; i++){ // pour tous les points du polygone
            angle[i] = Math.tan((moyY - points[i].getY()) / (moyX - points[i].getX()));
        }

        //On tri les points selon leur angle pour les mettres dans l'ordre
        HashSet<Integer> pointUsed = new HashSet<Integer>();
        int nbPointUsed = 0;
        while(nbPointUsed != nbPoints){
            double minAngle = 360;
            int j = 0;
            for(int i = 0; i < nbPoints; i++){// pour tous les points du polygone
                if(angle[i] < minAngle && pointUsed.contains(i)){
                    minAngle = angle[i];
                    j = i;
                }
            }
            pointUsed.add(j);
            nbPointUsed += 1;
            pointsOrdre[nbPointUsed] = points[j];
        }
        
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
