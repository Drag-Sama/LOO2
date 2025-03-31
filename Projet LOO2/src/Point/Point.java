package Point;


import exceptions.NegativeValue;

/**
 * @param x Position x du point
 * @param y Position y du point
 * @param r,g,b La couleur rgb attribué au point
 */
public class Point {
    float x;
    float y;
    int r,g,b;

    /**
     * Crée un point avec les coordonnées X,Y
     * @param newX la coordonnée X
     * @param newY la coordonnée Y
     */
    public Point (float newX, float newY) throws NegativeValue {
        setX(newX);
        setY(newY);
    }


    /**
     * Modifie la coordonnée X du Point
     * @param newX la nouvelle coordonnée X
     * @throws NegativeValue
     */
    public void setX(float newX) throws NegativeValue {
        if (newX >= 0) {
            this.x = newX;
        }
        else {
            throw new NegativeValue();
        }
    }

    /**
     * Renvoie la coordonnée X du point
     * @return la coordonnée X du point (float)
     */
    public float getX(){
        return x;
    }

    /**
     * Modifie la coordonnée Y du Point
     * @param newY la nouvelle coordonnée Y
     * @throws NegativeValue
     */
    public void setY(float newY) throws NegativeValue {
        if (newY >= 0) {
            this.y = newY;
        }
        else {
            throw new NegativeValue();
        }
    }

    /**
     * Renvoie la coordonnée Y du Point
     * @return la coordonnée Y du Point (float)
     */
    public float getY(){
        return y;
    }

    /**
     * Renvoie la variable R de la couleur RGB attribué au point
     * @return r
     */
    public int getR(){
        return r;
    }

    /**
     * Renvoie la variable G de la couleur RGB attribué au point
     * @return g
     */
    public int getG(){
        return g;
    }

    /**
     * Renvoie la variable B de la couleur RGB attribué au point
     * @return b
     */
    public int getB(){
        return b;
    }

    /**
     * Permet de changer la couleur RGB attribué au point
     * @param nvR la nouvelle variable R
     * @param nvG la nouvelle variable G
     * @param nvB la nouvelle variable B
     */
    public void setRGB(int nvR, int nvG, int nvB){
        r = nvR;
        g = nvG;
        b = nvB;
    }

    /**
     * Renvoie un nombre aléatoire entre 0 et 255
     * @return nombre aléatoire entre 0 et 255
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

    /** Calcule la distance entre le point d'origine et le point en paramètre
     * 
     * @param a le 2e point
     * @return la distance entre les deux points
     */
    public float getDist(Point a){
        return Math.abs(this.x - a.getX()) + Math.abs(this.y - a.getY());
    }

    /**
     * Retourne la valeur de l'indice du point du tableau "points" le plus proche
     * @param points à comparer
     * @return
     */
    public int getMinDistPoint(Point[] points) {
        int min = 0;
        for (int i = 0; i < points.length; i++) { // pour tous les points de la liste
            if (this.getDist(points[i]) <= this.getDist(points[min])) {
                min = i;
            }
        }
        return min;
    }

    public void setRandom(int maxX, int maxY){
        x = (int)(Math.random() * maxX);
        y = (int)(Math.random() * maxY);
    }

    @Override
    public String toString() {
        String s = "X = " + this.getX() + "\t Y = " + this.getY();
        return s;
    }

    public static void main(String[] args) throws NegativeValue {
        Point[] lp = new Point[1];
        Point p = new Point(0, 0);
        p.setX(2);
        p.setY(2);
        lp[0] = p;

        p.getMinDistPoint(lp);
        p.getDist(p);
        System.out.println(p);
    }
}


