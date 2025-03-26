package Dessin;
import processing.core.PApplet;

import Formes.Polygone;
import Formes.Cercle;
import Formes.Ellipse;
import Formes.Formes;
import Plan.Kmeans;
import Plan.Plan;
import Point.Point;
import exceptions.NegativeValue;

import java.util.Optional;
import java.util.Scanner;

public class Dessin extends PApplet{
    
    Plan plan;

    public void settings(){
        size(1300, 800);
    }

    public void setup(){
        background(255);
    }

    public void draw() {
        Scanner scan = new Scanner(System.in);
        int act_save = 0; // indice de la sauvegarde actuelle.
        Plan plan = new Plan();
        background(255);
        Point point = new Point(50, 150);
        plan.addPoint(point);

        Point point2 = new Point(60, 80);
        plan.addPoint(point2);

        Point point3 = new Point(450, 300);
        plan.addPoint(point3);

        Point point4 = new Point(500, 250);
        plan.addPoint(point4);

        Point point5 = new Point(653,452);
        plan.addPoint(point5);

        Point point6 = new Point(231,321);
        plan.addPoint(point6);
        Kmeans km = new Kmeans(plan,3);
        km.k_means();
        //drawKmeans(plan,km);

        Cercle e = new Cercle(100, point6);
        plan.addForme(e);
        drawForme(e);

        scan.nextLine();
        scan.close();
    }



    /**
     * Actuellement un simple test pour kmeans
     * @param plan
     * @param km (Kmeans)
     * pas de return
     */

    public void drawKmeans(Plan plan,Kmeans km) {

        System.out.println("Centres : ");
        for(int i = 0; i < km.getNbClusters();i++){
            stroke(0,0,0);
            System.out.println(i + ": " + km.getCentres()[i].getX() + " " + km.getCentres()[i].getY()); // afficher les coordonnées du centre


            strokeWeight(4);
            point(km.getCentres()[i].getX(), km.getCentres()[i].getY()); // dessine ce centre
        }
        Point[] arrayPoints;
        arrayPoints = plan.getPoints().toArray(new Point[plan.getNbPoints()]);
        System.out.println("Points : ");
        for(int i = 0; i < plan.getNbPoints();i++){
            System.out.println(i + ": " + arrayPoints[i].getX() + " " + arrayPoints[i].getY());strokeWeight(4);
            strokeWeight(3);
            stroke(255,0,0);
            point(arrayPoints[i].getX(),arrayPoints[i].getY());
        }

        
    }

    //draw Point ici

    /**
     * Permet de dessiner une forme selon sa nature
     * @param actForme la forme que l'on souhaite dessiner
     */
    public void drawForme(Formes actForme) throws NegativeValue{
        strokeWeight(2);
        stroke(actForme.getR(), actForme.getG(), actForme.getB());
        noFill();
        if(actForme instanceof Cercle){//Si c'est un cercle
            circle(actForme.getCentre().getX(),actForme.getCentre().getY(), ((Cercle)actForme).getRayon());
        }
        else if(actForme instanceof Ellipse){//Si c'est une ellipse
            ellipse(actForme.getCentre().getX(),actForme.getCentre().getY(), ((Ellipse)actForme).getLargeur(),((Ellipse)actForme).getLongueur());
        }
        else{//Si c'est une forme Polygone
            Point prevPoint = new Point(-1,-1);
            Point actPoint = new Point(-1, -1);
            Point[] listPoints =  ((Polygone)actForme).getPoints();
            for (int i = 1; i < ((Polygone)actForme).getNbPoints(); i++) {
                prevPoint = listPoints[i-1];
                actPoint  = listPoints[i];
                //trace une ligne entre le point i-1 et le point 1
                line(prevPoint.getX(), prevPoint.getY(), actPoint.getX(), actPoint.getY()); 
            }
            //trace une ligne entre le dernier et le premier point de la forme
            prevPoint = listPoints[((Polygone)actForme).getNbPoints()-1];
            actPoint  = listPoints[0];
            line(prevPoint.getX(), prevPoint.getY(), actPoint.getX(), actPoint.getY()); 
        }
    }
    
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Dessin.Dessin" };
        PApplet.main(appletArgs);
    }
}
