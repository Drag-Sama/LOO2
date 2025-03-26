package Formes;


import Point.Point;

/**
 * Classe abstraite d'une forme
 * @param centre Le centre de la forme
 * @param r,g,b La couleur de la forme
 */
public abstract class Forme {
    /**
     * Centre de la forme de type Point
     */
    private Point centre;
    /**
     * Couleur de la forme composée de 3 int :  r g b
     */
    int r = 0, g = 0, b = 0;


    /**
     * Renvoie le centre de la forme
     *
     * @return Centre de la forme
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Change le centre de la forme
     *
     * @param centre le nouveau centre
     */
    public void setCentre(Point centre) {
        this.centre = centre;
    }

    /**
     * Renvoie le r de la couleur de la forme
     *
     * @return R
     */
    public int getR() {
        return r;
    }

    /**
     * Renvoie le g de la couleur de la forme
     *
     * @return G
     */
    public int getG() {
        return g;
    }

    /**
     * Renvoie le b de la couleur de la forme
     *
     * @return B
     */
    public int getB() {
        return b;
    }

    /**
     * Permet de change la couleur de la forme avec les 3 paramètres (R,G,B)
     *
     * @param nvR R
     * @param nvG G
     * @param nvB B
     */
    public void setRGB(int nvR, int nvG, int nvB) {
        r = nvR;
        g = nvG;
        b = nvB;
    }

    /**
     * Renvoie un chiffre aléatoire entre 0 et 255
     *
     * @return
     */
    public int getRandomNumber() {
        return (int) ((Math.random() * (255)));
    }

    /**
     * Permet de changer la couleur de la forme aléatoirement
     */
    public void setRandomRGB() {
        setRGB(getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

}