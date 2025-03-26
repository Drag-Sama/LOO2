package Dessin;
import processing.core.PApplet;
import processing.core.PImage;
import Formes.Polygone;
import Formes.Cercle;
import Formes.Ellipse;
import Formes.Forme;
import Plan.Kmeans;
import Plan.Plan;
import Point.Point;
import exceptions.NegativeValue;

import java.util.Scanner;

/**
 * Dessine les éléments du plan
 * @param plan Le plan du dessin
 */
public class Dessin extends PApplet{
    
    Plan plan;
    int act_save = 0; // indice de la sauvegarde actuelle.
    int nb_save = 0; //Nombre d'image sauvegardé et donc d'étape effectué 

    /**
     * Permet de changer les paramètres de base du dessin
     */
    public void settings(){
        size(1300, 800);
    }


    public void setup(){
        background(255);
    }

    public void draw() {
        Scanner scan = new Scanner(System.in);
        
        Plan plan = new Plan();
        background(255);
        try{
            
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
            km.setupK_means();
            
            
            System.out.println("Etape actuel : " + act_save + "\nNombre d'étape max : " + nb_save + " \n ------------ \nEtape précédente : 0\nEtape suivante : 1");
            int userAns = scan.nextInt();

            if(userAns == 1){
                background(255);
                
                if(act_save == nb_save){
                    drawKmeans(plan,km);
                    save("StockDessin\\dessin" + act_save + ".jpg");
                    act_save += 1;
                    nb_save += 1;
                }
                else{
                    loadAndDrawImage();
                    act_save += 1;
                }
            }
            else{
                if(act_save > 0){
                    act_save -= 1;
                    loadAndDrawImage();
                }
                else{
                    System.out.println("Erreur, il n'y a pas d'étape précédente");
                }
                
            }

            
        }
        catch(NegativeValue e){
            e.printStackTrace();
        }

    }

    public void loadAndDrawImage(){
        background(255);
        PImage img = loadImage("StockDessin\\dessin" + act_save + ".jpg");
        image(img, 0, 0);
    }



    /**
     * Actuellement un simple test pour kmeans
     * @param plan
     * @param km (Kmeans)
     * pas de return
     */
    public void drawKmeans(Plan plan,Kmeans km) throws NegativeValue{
        background(255);
        km.k_meansOneStep();
        //Dessine le centre des clusters
        strokeWeight(5);
        stroke(0,0,0);
        drawPoint(km.getCentres(),km.getNbClusters());

        //Dessine les points du plan
        Point[] arrayPoints;
        arrayPoints = plan.getPoints().toArray(new Point[plan.getNbPoints()]);
        stroke(255,0,0);
        strokeWeight(3);
        drawPoint(arrayPoints, plan.getNbPoints());
        
    }

    /**
     * Dessine les points de la liste passée en paramètre
     * @param lPoints Liste de points à dessiner
     * @param nbPoints Nombre de points à dessiner
     */
    public void drawPoint(Point[] lPoints, int nbPoints){
        for(int i = 0; i < nbPoints; i ++){ //Boucle for qui parcours les points de la liste passée en paramètre
            point(lPoints[i].getX(),lPoints[i].getY());
        }
    }

    /**
     * Dessine les Forme de la liste passée en paramètre
     * @param lForme Liste de Forme à dessiner
     * @param nbForme Nombre de Forme à dessiner
     * @throws NegativeValue
     */
    public void drawAllForme(Forme[] lForme, int nbForme)throws NegativeValue{
        for(int i = 0; i < nbForme; i++){ //Boucle for qui parcours les Forme de la liste passée en paramètre
            drawForme(lForme[i]);
        }
    }

    /**
     * Permet de dessiner une forme selon sa nature
     * @param actForme la forme que l'on souhaite dessiner
     */
    public void drawForme(Forme actForme) throws NegativeValue{
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
