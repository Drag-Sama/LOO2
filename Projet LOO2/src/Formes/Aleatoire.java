package Formes;

import Point.Point;

public class Aleatoire extends Formes{
    Point[] points;
    int nbPoints = 0;

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public void addPoint(Point newPoint){
        points[nbPoints] = newPoint;
        nbPoints += 1;
    }
}
