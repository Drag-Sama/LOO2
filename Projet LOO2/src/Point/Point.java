package Point;

public class Point {
    float x;
    float y;

    public Point (float newX, float newY){
        this.x = newX;
        this.y = newY;
    }

    public void setX(int newX){
        this.x = newX;
    }

    public float getX(){
        return x;
    }

    public void setY(int newY){
        this.y = newY;
    }

    public float getY(){
        return y;
    }

    /** Calcule la distance entre le point d'origine et le point en paramètre
     * 
     * @param a le 2e point
     * @return la distance entre les deux points
     */
    public float getDist(Point a){
        return Math.abs(this.x - a.getX() + this.y - a.getY());
    }

    /**
     * Retourne la valeur de l'indice du point du tableau "points" le plus proche
     * @param points à comparer
     * @return
     */
    public int getminDistPoint(Point[] points) {
        int min = 0;
        for (int i = 0; i < points.length; i++) {
            if (this.getDist(points[i]) <= this.getDist(points[min])) {
                min = i;
            }
        }
        return min;
    }
}


