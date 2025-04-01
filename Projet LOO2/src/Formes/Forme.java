package Formes;


import Point.Point;

/**
 * Classe abstraite d'une forme
 * @param centre Le centre de la forme
 * @param rgb La couleur de la forme
 */
public abstract class Forme {
    /**
     * Centre de la forme de type Point
     */
    private Point centre;
    /**
     * Couleur de la forme composée de 3 int :  r g b
     */
    int[] rgb = new int[3];


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
    public void setCentre(Point nvCentre) {
        this.centre = nvCentre;
    }

    /**
     * Renvoie la couleur de la forme
     *
     * @return rgb
     */
    public int[] getRgb() {
        return rgb;
    }


    /**
     * Permet de change la couleur de la forme avec les 3 paramètres (R,G,B)
     *
     * @param nvRgb Rgb
     */
    public void setRGB(int[] nvRgb) {
        rgb = nvRgb;
    }

    /**
     * Renvoie un chiffre aléatoire entre 0 et 255
     *
     * @return
     */
    private int getRandomNumber() {
        return (int) ((Math.random() * (255)));
    }

    /**
     * Permet de changer la couleur de la forme aléatoirement
     */
    public void setRandomRGB() {
        int[] nvRgb = new int[3];
        for(int i = 0; i < 3; i++){
            nvRgb[i] = getRandomNumber();
        }
        setRGB(nvRgb);
    }
}