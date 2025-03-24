package Formes;

import Point.Point;

/**
 * Ellipse
 * @param longueur Grand demi axe de l'ellipse
 * @param largeur Petit demi axe de l'ellipse
 */
public class Ellipse extends Formes {
    private float longueur; // = "grand" demi-axe
    private float largeur; // = "petit" demi-axe

    
    public Ellipse(float nvLongueur, float nvLargeur, Point centre){
        setCentre(centre);
        setLargeur(nvLargeur);
        setLongueur(nvLongueur);
        this.setRandomRGB();
    }

    public float getLargeur() {
        return largeur;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public static void main(String[] args) {
        Point p = new Point(0, 0);
        Ellipse e = new Ellipse(0, 0, p);
        e.setLargeur(3);
        e.setLongueur(2);
    }
    
}
