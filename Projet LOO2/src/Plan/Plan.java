package Plan;

import java.util.LinkedHashSet;

import Formes.Forme;
import Point.Point;

public class Plan {
    private LinkedHashSet<Forme> formes = new LinkedHashSet<Forme>();
    private int nbFormes;
    private LinkedHashSet<Point> points = new LinkedHashSet<Point>();
    private int nbPoints;

    public Plan() {
        setNbFormes(0);
        setNbPoints(0);
    }

    ///////// ACCESSEURS /////////////

    public LinkedHashSet<Forme> getFormes() {
        return formes;
    }

    public LinkedHashSet<Point> getPoints() {
        return points;
    }

    public int getNbFormes() {
        return nbFormes;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setFormes(LinkedHashSet<Forme> formes) {
        this.formes = formes;
    }

    public void setPoints(LinkedHashSet<Point> points) {
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
    public void addForme(Forme forme) {
        this.formes.add(forme);
        this.setNbFormes(this.getNbFormes()+1);
    }

    /**
     * Retire une forme de la liste des formes du plan et actualise nbFormes
     * @param forme
     * @return true si elle a pu être retirée, false sinon.
     */

    public boolean removeForme(Forme forme) {
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

    @Override
    public String toString() {
        String str = "Plan : \n \t";
        return str;
    }


    public static void main(String[] args) {

    }
}
