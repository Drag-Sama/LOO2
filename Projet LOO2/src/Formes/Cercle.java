package Formes;
import Point.Point;


public class Cercle extends Formes {
    private float rayon;

    /**
     * Une forme Cerlce
     * @param rayon le rayon du cercle
     * @param nvCentre le centre du cercle
     */
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
        Cercle  c = new Cercle(rayon, getCentre());
    }
}
