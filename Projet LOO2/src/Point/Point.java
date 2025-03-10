package Point;

public class Point {
    float x;
    float y;

    public Point (int newX, int newY){
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

    float getDist(Point a){
        return Math.abs(this.x - a.getX() + this.y - a.getY());
    }
}

