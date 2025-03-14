package Dessin;
import processing.core.PApplet;

import java.util.HashSet;

import Formes.Aleatoire;
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
        Cercle cercle = new Cercle(100, point3);
        plan.addForme(cercle);

        Point point4 = new Point(453, 486);
        Ellipse ellipse = new Ellipse(145,70,point4);
        plan.addForme(ellipse);

        Aleatoire alea = new Aleatoire(point4);
        alea.addPoint(point);
        alea.addPoint(point2);
        alea.addPoint(point3);
        alea.addPoint(point4);
        plan.addForme(alea);

        HashSet<Formes> formes = plan.getFormes();
        for (Formes actFormes : formes) {
            if(actFormes instanceof Cercle){//Si c'est un cercle
                circle(actFormes.getCentre().getX(),actFormes.getCentre().getY(), ((Cercle)actFormes).getRayon());
            }
            else if(actFormes instanceof Ellipse){//Si c'est une ellipse
                ellipse(actFormes.getCentre().getX(),actFormes.getCentre().getY(), ((Ellipse)actFormes).getLargeur(),((Ellipse)actFormes).getLongueur());
            }
            else{//Si c'est une forme aleatoire
                Point prevPoint = new Point(-1,-1);
                Point actPoint = new Point(-1, -1);
                for (int i = 1; i < ((Aleatoire)actFormes).getNbPoints(); i++) {
                    prevPoint = ((Point[])((Aleatoire)actFormes).getPoints().toArray())[i-1];
                    actPoint  = ((Point[])((Aleatoire)actFormes).getPoints().toArray())[i];
                    line(prevPoint.getX(), prevPoint.getY(), actPoint.getX(), actPoint.getY()); 
                    
                    
                }
                prevPoint = ((Point[])((Aleatoire)actFormes).getPoints().toArray())[((Aleatoire)actFormes).getNbPoints()];
                actPoint  = ((Point[])((Aleatoire)actFormes).getPoints().toArray())[0];
                line(prevPoint.getX(), prevPoint.getY(), actPoint.getX(), actPoint.getY()); 
            }
        }
        
    }
    
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Dessin.Dessin" };
        PApplet.main(appletArgs);
    }
}
