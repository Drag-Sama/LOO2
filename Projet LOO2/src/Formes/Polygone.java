package Formes;

import java.util.Arrays;
import java.util.HashSet;

import Point.Point;
import exceptions.NegativeValue;

/**
 * Polygone
 * @param nbPoints Nombre de points du polygone
 * @param points Liste des points du polygone
 */
public class Polygone extends Formes{
    private int nbPoints = 0;
    private Point[] points = new Point[50];

    public Polygone(Point centre){
        this.setCentre(centre);
        this.setRandomRGB();
        System.out.println("yay");
    }
    

    /**
     * Renvoie les points du polygone
     * @return
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * Permet de changer les points du polygone
     * @param points Nouveau points
     */
    public void setPoints(Point[] points) {
        this.points = points;
    }

    /**
     * Renvoie le nombre de point(s) du polygone
     * @return
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Permet de changer le nombre de points 
     * @param nvNbPoints Le nouveau nombre de points
     * @throws NegativeValue Les valeurs négatives ne sont pas accepté en paramètre
     */
    public void setNbPoints(int nvNbPoints) throws NegativeValue{
        if(nvNbPoints >= 0){
            this.nbPoints = nvNbPoints;
        }
        else{
            throw new NegativeValue();
        }
       
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
            points[nbPointUsed] = points[j];
        }
        
    }

    @Override
    public String toString() {
        String s = this.getClass().getSimpleName() + " : \n";
        for (int i = 0; i < this.getNbPoints(); i++) {
            s+= "\t -  " + this.getClass().getSimpleName();
        }
        return s;
    }

    public static void main(String[] args) throws NegativeValue {
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
        System.out.println(a);
    }
}
