package Formes;

import Point.Point;

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
    
}
