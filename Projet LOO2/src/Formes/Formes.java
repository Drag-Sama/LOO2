package Formes;


import Point.Point;


public class Formes{
    private Point centre;
    int r = 0,g = 0,b = 0;

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

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


