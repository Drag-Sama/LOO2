package Formes;
import Point.Point;
import exceptions.NegativeValue;

import java.io.File;

/**
 * Une forme Cercle
 * @param rayon le rayon du cercle
 * @param nvCentre le centre du cercle
 */
public class Cercle extends Forme {
    private float rayon;

    public Cercle(float rayon, Point nvCentre) throws NegativeValue{
        setCentre(nvCentre);
        setRayon(rayon);
        this.setRandomRGB();
    }

    /**
     * Renvoie le rayon du cercle
     * @return Le rayon
     */
    public float getRayon() {
        return rayon;
    }

    /**
     * Permet de changer le rayon du cercle
     * @param rayon Le nouveau rayon
     * @throws Negativevalue Les valeurs négatives ne sont pas accepté en paramètre
     */
    public void setRayon(float nvRayon) throws NegativeValue{
        if(nvRayon >= 0){
            this.rayon = nvRayon;
        }
        else{
            throw new NegativeValue();
        }
        
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() +  " : \n \t Centre : " + this.getCentre().toString() + " \n \t Rayon : " + rayon;
    }

    public static void main(String[] args) throws NegativeValue{
        Point p = new Point(0, 0);
        Cercle c = new Cercle(3, p);
        c.setRayon(2);
        c.setCentre(p);
        c.setRandomRGB();
        System.out.println(c);
        for (int i = 0; i < 4;i++) {
            File f = new File("..\\StockDessin\\dessin" + i + ".jpg");
            if (f.delete()) {
             System.out.println("supprimééé");
            }
            else {
                System.out.println("non");
            }
        }
    }
}
