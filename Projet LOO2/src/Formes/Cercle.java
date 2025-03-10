package Formes;
import Point.Point;

public class Cercle extends Formes {
    private float rayon;

    public Cercle(float rayon, Point nvCentre){
        setCentre(nvCentre);
        setRayon(rayon);
    }

    public float getRayon() {
        return rayon;
    }

    public void setRayon(float rayon) {
        this.rayon = rayon;
    }
}
