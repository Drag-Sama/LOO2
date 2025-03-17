package Dessin;
import processing.core.PApplet;

import java.util.HashSet;

import Formes.Aleatoire;
import Formes.Cercle;
import Formes.Ellipse;
import Formes.Formes;
import Plan.Kmeans;
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

        Point point2 = new Point(60, 80);
        plan.addPoint(point2);

        Point point3 = new Point(450, 300);
        plan.addPoint(point3);

        Point point4 = new Point(500, 250);
        plan.addPoint(point4);

        Kmeans km = new Kmeans(plan, 2);
        km.k_means();

        for(int i = 0; i < km.getNbClusters();i++){
            strokeWeight(4);
            stroke(0,0,0);
            System.out.println(km.getCentres()[i].getX());
            point(km.getCentres()[i].getX(), km.getCentres()[i].getY());
        }
        
    }

    /**
     * Permet de dessiner une forme selon sa nature
     * @param actForme
     */
    public void drawForme(Formes actForme){
        strokeWeight(3);
        stroke(actForme.getR(), actForme.getG(), actForme.getB());
        noFill();
        if(actForme instanceof Cercle){//Si c'est un cercle
            circle(actForme.getCentre().getX(),actForme.getCentre().getY(), ((Cercle)actForme).getRayon());
        }
        else if(actForme instanceof Ellipse){//Si c'est une ellipse
            ellipse(actForme.getCentre().getX(),actForme.getCentre().getY(), ((Ellipse)actForme).getLargeur(),((Ellipse)actForme).getLongueur());
        }
        else{//Si c'est une forme aleatoire
            Point prevPoint = new Point(-1,-1);
            Point actPoint = new Point(-1, -1);
            Point[] listPoints =  ((Aleatoire)actForme).getPoints();
            for (int i = 1; i < ((Aleatoire)actForme).getNbPoints(); i++) {
                prevPoint = listPoints[i-1];
                actPoint  = listPoints[i];
                line(prevPoint.getX(), prevPoint.getY(), actPoint.getX(), actPoint.getY()); 
            }
            prevPoint = listPoints[((Aleatoire)actForme).getNbPoints()-1];
            actPoint  = listPoints[0];
            line(prevPoint.getX(), prevPoint.getY(), actPoint.getX(), actPoint.getY()); 
        }
    }
    
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Dessin.Dessin" };
        PApplet.main(appletArgs);
    }
}
