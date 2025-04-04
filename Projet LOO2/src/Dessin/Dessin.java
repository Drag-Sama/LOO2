package Dessin;
import exceptions.IdenticalPoints;
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
 * @param act_save Etape actuel du dessin
 * @param nb_save Nombre d'étapes effectuées
 */
public class Dessin extends PApplet{
    int MAX_X;
    int MAX_Y;
    Plan plan = new Plan();
    int act_save = 0; // indice de la sauvegarde actuelle.
    int nb_save = 0; //Nombre d'images sauvegardées et donc d'étapes effectuées
    Kmeans km = new Kmeans(plan,0);


    /**
     * Permet une mise en place avant le dessin (prédéfinie par Processing)
     */
    public void setup() throws IdenticalPoints{
        background(255);
        int repUser;
        Scanner scan = new Scanner(System.in);
        boolean identical = true;

        System.out.println("Mise en place des points aléatoire : 0 \nMise en place des points manuelle : 1 \nScénarios critiques : 2");
        repUser = scan.nextInt();
        while (repUser < 0 || repUser > 2) {
            System.out.println("-- Entrée invalide ! --");
            System.out.println("Mise en place des points aléatoire : 0 \nMise en place des points manuelle : 1 \nScénarios critiques : 2");
            repUser = scan.nextInt();
        }

        if (repUser == 0) { // si rep = 0, on remplit le plan par des points aléatoires.
            System.out.println("Combien de points ? ");
            repUser = scan.nextInt();
            while (repUser < 0) {
                System.out.println("-- Nombre de points invalide ! --");
                System.out.println("Combien de points ? ");
                repUser = scan.nextInt();
            }
            for(int i = 0; i < repUser; i++){
                Point point = new Point(0,0);
                point.setRandom(MAX_X,MAX_Y);
                plan.addPoint(point);
            }
        }

        else if (repUser == 1) {
            System.out.println("Combien de points ? ");
            repUser = scan.nextInt();
            while (repUser < 0) {
                System.out.println("-- Nombre de points invalide ! --");
                System.out.println("Combien de points ? ");
                repUser = scan.nextInt();
            }
            for(int i = 0; i < repUser; i ++){
                System.out.println("Nouveau Point : \n \t X ? ");
                int X = scan.nextInt();
                while (X < 0 || X > MAX_X) {
                    System.out.println("-- X invalide ! (négatif ou > " + MAX_X + " --");
                    System.out.println("X ? ");
                    X = scan.nextInt();
                }
                System.out.println("\t Y ? ");
                int Y = scan.nextInt();
                while (Y < 0 || Y > MAX_Y) {
                    System.out.println("-- Y invalide ! (négatif ou > " + MAX_Y + " --");
                    System.out.println("Y ? ");
                    Y = scan.nextInt();
                }
                Point point = new Point(X,Y);
                plan.addPoint(point);
            }
        }

        else {
            System.out.println("Scénario des points identiques : 0 \nScénario - : 1");
            repUser = scan.nextInt();
            if (repUser == 0) ScenarioIdenticalPoints();
            //else
        }

        System.out.println("Nombre de clusters ? ");
        repUser = scan.nextInt();
        km.setNbClusters(repUser);
        while (km.getNbClusters() < 0 ||km.getNbClusters() > plan.getNbPoints()) {
            System.out.println("-- Nombre de clusters invalide (négatif ou > nb de points du plan (" + plan.getNbPoints() +")");
            System.out.println("Nombre de clusters ? ");
            repUser = scan.nextInt();
            km.setNbClusters(repUser);
            if (km.getNbClusters() < Math.sqrt(plan.getNbPoints())) System.out.println("!! Attention : votre nombre de cluster est sûrement trop petit !!");
            else if (km.getNbClusters() > Math.pow(plan.getNbPoints(),2)) System.out.println("!! Attention : votre nombre de cluster est sûrement trop grand !!");
        }

        Point[] arrayPoints = plan.getPoints().toArray((new Point[plan.getNbPoints()]));
        for (int i = 1; i < plan.getNbPoints(); i++) {
            if (arrayPoints[i] != arrayPoints[i-1]) {
                identical = false;
                break;
            }
        }
        if (identical) throw new IdenticalPoints();

        km.setPlan(plan);
        km.setupK_means();
        System.out.println("Setup terminé");
    }


    /**
     * Permet de changer les paramètres de base du dessin (prédéfinie par Processing)
     */
    public void settings(){
        int repUser;
        Scanner scan = new Scanner(System.in);
        System.out.println("Maximum X du plan ? ");
        repUser = scan.nextInt();
        while (repUser < 0) {
            System.out.println("-- Valeur négative ! --");
            System.out.println("Maximum X du plan ? ");
            repUser = scan.nextInt();
        }
        MAX_X = repUser;

        System.out.println("Maximum Y du plan ? ");
        repUser = scan.nextInt();
        while (repUser < 0) {
            System.out.println("-- Valeur négative ! --");
            System.out.println("Maximum Y du plan ? ");
            repUser = scan.nextInt();
        }
        MAX_Y = repUser;
        size(MAX_X, MAX_Y);
    }

    /**
     * Permet de dessiner (prédéfinie par la bibliothèque graphique : Processing) -> se recall indéfiniment
     */
    public void draw() throws NegativeValue {
        Scanner scan = new Scanner(System.in);
        background(255);
        try{
            System.out.println("Etape actuel : " + act_save + "\nNombre d'étape max : " + nb_save + " \n ------------ \nEtape précédente : 0\nEtape suivante : 1");
            int userAns = scan.nextInt();
            if(userAns == 1){
                background(255); //Pour dissocier chaque dessin → sinon les dessins se fusionnent.
                if(act_save == nb_save){ //S'il n'y a pas d'image sauvegardée pour l'étape suivante
                    drawKmeans();
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

    public void ScenarioIdenticalPoints() {
        for (int i = 0; i < 15; i++) {
            plan.addPoint(new Point(100,100));
        }
    }

    public void ScenarioTooMuchClusters() {

    }

    /**
     * Charge et dessine une image selon la sauvegarde actuelle
     */
    public void loadAndDrawImage(){
        background(255);
        PImage img = loadImage("StockDessin\\dessin" + act_save + ".jpg");
        image(img, 0, 0);
    }



    /**
     * Actuellement un simple test pour kmeans
     * pas de return
     */
    public void drawKmeans() throws NegativeValue{
        km.k_meansOneStep();
        background(255);

        plan.resetForme();
        for(int i = 0; i < km.getNbClusters(); i++){ //On ajoute les cercles pour chaque cluster
            Point pt =  km.maxDistCluster(i);
            System.out.println(km.getCentres()[i] + " rayon : " + km.getCentres()[i].getDist(pt) + " Max dist " + pt);
            Cercle cercle = new Cercle(km.getCentres()[i].getDist(pt) * 2, km.getCentres()[i]);
            cercle.setRGB(km.getCentres()[i].getRgb()); //On copie la couleur du centre du cluster dans le cercle
            plan.addForme(cercle);
        }
        drawAllForme((plan.getFormes()), plan.getNbFormes());

        //Dessine le centre des clusters
        strokeWeight(6);
        stroke(0,0,0);
        drawPoint(km.getCentres(),km.getNbClusters());

        //Dessine les points du plan
        Point[] arrayPoints;
        arrayPoints = plan.getPoints().toArray(new Point[plan.getNbPoints()]);
        stroke(255,0,0);
        strokeWeight(4);
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
        strokeWeight(1);
        int[] formeRgb = actForme.getRgb();
        stroke(formeRgb[0], formeRgb[1], formeRgb[2]);
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
