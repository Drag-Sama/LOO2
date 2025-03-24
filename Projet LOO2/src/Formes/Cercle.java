package Formes;
import Point.Point;

/**
 * Une forme Cerlce
 * @param rayon le rayon du cercle
 * @param nvCentre le centre du cercle
 */
public class Cercle extends Formes {
    private float rayon;

    public Cercle(float rayon, Point nvCentre){
        setCentre(nvCentre);
        setRayon(rayon);
        this.setRandomRGB();
    }

    public float getRayon() {
        return rayon;
    }

    public void setRayon(float rayon) {
        this.rayon = rayon;
    }

    @Override
    public String toString() {
        return "Centre : " + this.getCentre().toString() + " Rayon : " + rayon;
    }

    public static void main(String[] args) {
        Point p = new Point(0, 0);
        Cercle c = new Cercle(3, p);
        c.setRayon(2);
        c.setCentre(p);
    }
}
