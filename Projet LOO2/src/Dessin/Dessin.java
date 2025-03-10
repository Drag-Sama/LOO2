package Dessin;
import processing.core.PApplet;

import java.util.HashSet;

import Formes.Cercle;
import Formes.Ellipse;
import Formes.Formes;
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

        Point point2 = new Point(452, 563);
        plan.addPoint(point2);
        
        HashSet<Point> points = plan.getPoints();
        for (Point actPoint : points) {
            strokeWeight(2);
            stroke(0,0 ,255);
            point(actPoint.getX(), actPoint.getY());
        }
        

        Point point3 = new Point(70, 200);
        Cercle cercle = new Cercle(100,point3);
        plan.addForme(cercle);

        Point point4 = new Point(453, 486);
        Ellipse ellipse = new Ellipse(145,70,point4);
        plan.addForme(ellipse);
        HashSet<Formes> formes = plan.getFormes();
        for (Formes actFormes : formes) {
            if(actFormes instanceof Cercle){
                circle(actFormes.getCentre().getX(),actFormes.getCentre().getY(), ((Cercle)actFormes).getRayon());
            }
            else if(actFormes instanceof Ellipse){
                ellipse(actFormes.getCentre().getX(),actFormes.getCentre().getY(), ((Ellipse)actFormes).getLargeur(),((Ellipse)actFormes).getLongueur());
            }
        }
        
    }
    
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Dessin.Dessin" };
        PApplet.main(appletArgs);
    }
}
