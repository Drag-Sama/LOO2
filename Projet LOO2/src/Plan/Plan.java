package Plan;

import java.util.LinkedHashSet;

import Formes.Cercle;
import Formes.Formes;
import Point.Point;
import exceptions.NegativeValue;

/**
 * Un plan permet de contenir des points et des formes
 * @param formes Les formes qui sont dans le plan
 * @param nbFormes Le nombre de formes dans le plan
 * @param points Les points qui sont dans le plan
 * @param nbPoints Le nombre de points dans le plan
 */
public class Plan {
    private LinkedHashSet<Formes> formes = new LinkedHashSet<Formes>();
    private int nbFormes;
    private LinkedHashSet<Point> points = new LinkedHashSet<Point>();
    private int nbPoints;

    public Plan() {
        setNbFormes(0);
        setNbPoints(0);
    }

    ///////// ACCESSEURS /////////////

    public LinkedHashSet<Formes> getFormes() {
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

    public void setFormes(LinkedHashSet<Formes> formes) {
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

    @Override
    public String toString() {
        String str = "Plan : \n \t";
        return str;
    }


    public static void main(String[] args) throws NegativeValue {
    Plan p = new Plan();
    Point p2 = new Point(0, 0);
    p.addPoint(p2);
    Cercle c = new Cercle(0, p2);
    p.getFormes();
    p.getNbFormes();
    p.getPoints();
    p.getNbPoints();
    p.addForme(c);
    p.removePoint(p2);
    p.removeForme(c);
    }
}
