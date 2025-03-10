package Formes;

public class Ellipse extends Formes {
    float longueur; // = "grand" demi-axe
    float largeur; // = "petit" demi-axe

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
