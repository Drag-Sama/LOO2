package Formes;


import Point.Point;

/**
 * Classe abstraite d'une forme
 * @param centre Le centre de la forme
 * @param r,g,b La couleur de la forme
 */
public abstract class Formes{
    /**
     * Centre de la forme de type Point
     */
    private Point centre;
    /**
     *  Couleur de la forme composée de 3 int :  r g b
     */
    int r = 0,g = 0,b = 0;


    /**
     * Renvoie le centre de la forme
     * @return Centre de la forme
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Change le centre de la forme
     * @param centre le nouveau centre
     */
    public void setCentre(Point centre) {
        this.centre = centre;
    }

    /**
     * Renvoie le r de la couleur de la forme
     * @return
     */
    public int getR(){
        return r;
    }

    public int getG(){
        return g;
    }

    public int getB(){
        return b;
    }

    public void setRGB(int nvR, int nvG, int nvB){
        r = nvR;
        g = nvG;
        b = nvB;
    }

    public int getRandomNumber() {
        return (int) ((Math.random() * (255)));
    }

    public void setRandomRGB(){
        setRGB(getRandomNumber(), getRandomNumber(), getRandomNumber());
    }


}


