package Plan;

import java.util.LinkedHashSet;
import java.util.Objects;

import Formes.Forme;
import Point.Point;
import exceptions.FormeIsNull;
import exceptions.NegativeValue;
import exceptions.PointIsNull;

/**
 * Classe représentant un plan en 2D avec longueur * largeur infinie / non définie.
 * @param formes la liste (Hashset) des formes dans le plan
 * @param nbFormes le nombre de formes dans le plan
 * @param points la liste (Hashset) des points dans le plan
 * @param nbPoints le nombre de points dans le plan
 */
public class Plan {
    private LinkedHashSet<Forme> formes = new LinkedHashSet<Forme>();
    private int nbFormes;
    private LinkedHashSet<Point> points = new LinkedHashSet<Point>();
    private int nbPoints;

    /**
     * Crée un Plan avec par défaut le nombre de formes et de points à 0.
     */
    public Plan() {
        setNbFormes(0);
        setNbPoints(0);
    }

    ///////// ACCESSEURS /////////////

    /**
     * Renvoie la liste des formes du plan
     * @return la liste des formes du plan (Hashset)
     */
    public LinkedHashSet<Forme> getFormes() {
        return formes;
    }

    /**
     * Renvoie la liste des points du plan
     * @return la liste des points du plan (Hashset)
     */
    public LinkedHashSet<Point> getPoints() {
        return points;
    }

    /**
     * Renvoie le nombre de formes dans le plan
     * @return le nombre de formes (int)
     */
    public int getNbFormes() {
        return nbFormes;
    }

    /**
     * Renvoie le nombre de points dans le plan
     * @return le nombre de points (int)
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Remplace la liste des formes par une nouvelle
     * @param formes la nouvelle liste des formes du plan
     */
    public void setFormes(LinkedHashSet<Forme> formes) throws FormeIsNull {
        if (formes.stream().anyMatch(Objects::isNull)) throw new FormeIsNull("L'une des formes de la nouvelle liste = null");
        this.formes = formes;
        setNbFormes(formes.size());
    }

    /**
     * Remplace la liste des points du par une nouvelle
     * @param points la nouvelle liste des points du plan
     */

    public void setPoints(LinkedHashSet<Point> points) throws PointIsNull {
        if (points.stream().anyMatch(Objects::isNull)) throw new PointIsNull("L'un des points de la nouvelle liste = null.");
        this.points = points;
        setNbPoints(points.size());
    }

    /**
     * Modifie la valeur du nombre de formes
     * @param nbFormes le nombre de formes
     * @throws NegativeValue
     */
    public void setNbFormes(int nbFormes) throws NegativeValue {
        if (nbFormes < 0) throw new NegativeValue();
        this.nbFormes = nbFormes;
    }

    /**
     * Modifie la valeur du nombre de points
     * @param nbPoints le nombre de points
     * @throws NegativeValue
     */
    public void setNbPoints(int nbPoints) throws NegativeValue {
        if (nbPoints < 0) throw new NegativeValue();
        this.nbPoints = nbPoints;
    }


    ///////// METHODES ////////////

    /**
     * Ajoute une forme à la liste des formes du plan et actualise nbFormes
     * @param forme à ajouter
     */
    public void addForme(Forme forme) throws FormeIsNull {
        if (forme == null) throw new FormeIsNull("La nouvelle forme = null");
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
    public void addPoint(Point point) throws PointIsNull {
        if (point == null) throw new PointIsNull("Le nouveau point = null");
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
