package Plan;

import java.util.HashSet;

import Formes.Formes;
import Point.Point;

public class Plan {
    private HashSet<Formes> formes = new HashSet<Formes>();
    private int nbFormes;
    private HashSet<Point> points = new HashSet<Point>();
    private int nbPoints;

    public Plan() {
        setNbFormes(0);
        setNbPoints(0);
    }

    ///////// ACCESSEURS /////////////

    public HashSet<Formes> getFormes() {
        return formes;
    }

    public HashSet<Point> getPoints() {
        return points;
    }

    public int getNbFormes() {
        return nbFormes;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setFormes(HashSet<Formes> formes) {
        this.formes = formes;
    }

    public void setPoints(HashSet<Point> points) {
        this.points = points;
    }

    public void setNbFormes(int nbFormes) {
        this.nbFormes = nbFormes;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }


    ///////// METHODES ////////////

    /**
     * Ajoute une forme à la liste des formes du plan et actualise nbFormes
     * @param forme à ajouter
     */
    public void addForme(Formes forme) {
        this.formes.add(forme);
        this.setNbFormes(this.getNbFormes()+1);
    }

    /**
     * Retire une forme de la liste des formes du plan et actualise nbFormes
     * @param forme
     * @return true si elle a pu être retirée, false sinon.
     */

    public boolean removeForme(Formes forme) {
        if (this.formes.remove(forme)) {
            this.setNbFormes(this.getNbFormes()-1);
            return true;
        }
        return false;
    }

    /**
     * Ajoute un point à la liste des points du plan et actualise nbPoints
     * @param point
     */
    public void addPoint(Point point) {
        this.points.add(point);
        this.setNbPoints(this.getNbPoints()+1);
    }

    /**
     * Retire un point de la liste des points du plan et actualise nbPoints
     * @param point
     * @return true si le point a pu être retiré, false sinon.
     */

    public boolean removePoint(Point point) {
        if (this.points.remove(point)){
            this.setNbPoints(this.getNbPoints()-1);
            return true;
        }
        return false;
    }

    //////// METHODES k-means ///////////

    /**
     * réalise l'algorithme k-means pour ce plan en utilisant tous les points de celui-ci
     * @param clusters
     */
    public void k_means(int clusters) {
        Point[] centres = new Point[clusters];
        boolean centresModif = true;
        while (centresModif) { // tant que les centres ont été modifiés (à chaque itération) :

        }
    }


    ////////////////////////////////////
    public static void main(String[] args) {
        
    }

}
