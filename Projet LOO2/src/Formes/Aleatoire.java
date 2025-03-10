package Formes;

import java.util.LinkedHashSet;

import Point.Point;

public class Aleatoire extends Formes{
    int nbPoints = 0;
    LinkedHashSet<Point> points = new LinkedHashSet<Point>();
    

    public LinkedHashSet<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedHashSet<Point> points) {
        this.points = points;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public void addPoint(Point newPoint){
        this.points.add(newPoint);
        this.nbPoints += 1;
    }

    public void removePoint(Point point){
        if(this.points.remove(point))
            this.nbPoints -= 1;
    }
}
