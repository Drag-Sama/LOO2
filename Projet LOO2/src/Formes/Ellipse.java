package Formes;

import Point.Point;
import exceptions.NegativeValue;

/**
 * Ellipse
 * @param longueur Grand demi axe de l'ellipse
 * @param largeur Petit demi axe de l'ellipse
 */
public class Ellipse extends Formes {
    private float longueur; // = "grand" demi-axe
    private float largeur; // = "petit" demi-axe

    
    public Ellipse(float nvLongueur, float nvLargeur, Point centre) throws NegativeValue{
        setCentre(centre);
        setLargeur(nvLargeur);
        setLongueur(nvLongueur);
        this.setRandomRGB();
    }

    /**
     * Renvoie le petit demi axe de l'ellipse (la largeur)
     * @return Le petit demi axe de l'ellipse
     */
    public float getLargeur() {
        return largeur;
    }

    /**
     * Renvoie le grand demi axe de l'ellipse (lalongueur)
     * @return Le grand demi axe de l'ellipse
     */
    public float getLongueur() {
        return longueur;
    }

    /**
     * Permet de changer le petit demi axe de l'ellipse (la largeur)
     * @param largeur La nouvelle valeur du petit demi axe
     */
    public void setLargeur(float largeur) throws NegativeValue {
        if(largeur >= 0){
            this.largeur = largeur;
        }
        else{
            throw new NegativeValue();
        }
       
    }

    /**
     * Permet de changer le grand demi axe de l'ellipse (la longueur)
     * @param longueur La nouvelle valeur du grand demi axe
     */
    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +  " Centre : " + this.getCentre().toString() + "Grand demi axe : " + longueur + " Petit demi axe : " + largeur;
    }

    public static void main(String[] args) throws NegativeValue {
        Point p = new Point(0, 0);
        Ellipse e = new Ellipse(0, 0, p);
        e.setLargeur(3);
        e.setLongueur(2);
        System.out.println(e);
    }
    
}
