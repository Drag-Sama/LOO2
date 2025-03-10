package Dessin;
import processing.core.PApplet;

import java.util.HashSet;

import Plan.Plan;
import Point.Point;

public class Dessin extends PApplet{

    Plan plan;

    public void settings(){
        size(1300, 800);
    }

    public void setup(){
        background(255);
    }

    public void draw(){
        plan = new Plan();
        Point point = new Point(50, 150);
        plan.addPoint(point);

        fill(0,0,255);
        HashSet<Point> points = plan.getPoints();
        for (Point actPoint : points) {
            point(actPoint.getX(), actPoint.getY());
        }
        point(100,100);
    }
    
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Dessin.Dessin" };
        PApplet.main(appletArgs);
    }
}
