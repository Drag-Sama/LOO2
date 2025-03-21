package Plan;

import Point.Point;
import java.util.Random;

public class Kmeans {
    private int nbClusters;
    private Point[] centres;
    private Plan plan;
    private int[] indicesCentres; // liste d'entiers représentant l'indice (dans la liste) des
                                  // centres attribuées aux points;



    public Kmeans(Plan plan, int clusters) {
        this.plan = plan;
        this.nbClusters = clusters;
        this.centres = new Point[nbClusters];
        this.indicesCentres = new int[this.plan.getNbPoints()];

    }

    public int getNbClusters() {return this.nbClusters;}
    public void setNbClusters(int clusters) {this.nbClusters = clusters;}
    public Point[] getCentres() {return this.centres;}
    public void setCentres(Point[] centres) {this.centres = centres;}
    public int[] getIndicesCentres () {return this.indicesCentres;}
    public void setIndicesCentres (int[] indicesCentres) {this.indicesCentres = indicesCentres;}

    /////// METHODES K-MEANS ////////

    /**
     * réalise l'algorithme k-means classique  pour le plan en attribut en utilisant tous les points de celui-ci
     * pas de params
     * pas de return -> tout en effet de bord sur ses propres attributs.
     * */
    public void k_means() {
        //float[] distances = new float[this.plan.getNbPoints()];
        Point[] arrayPoints;
        Random rand = new Random();
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        boolean centresModif = true;
        for (int i = 0; i < nbClusters; i++) centres[i] = arrayPoints[rand.nextInt((plan.getNbPoints()))];
        while (centresModif) { // tant que les centres ont été modifiés (à chaque itération) :
            for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
                indicesCentres[i] = arrayPoints[i].getMinDistPoint(centres); // on attribue le centre d'indice min au point i
            }
            centresModif = false;
            for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
                float nv_x = 0;
                float nv_y = 0;
                int nb = 0;
                for (int j = 0; j < this.plan.getNbPoints(); j++) { // pour chaque points, on vérifie si son centre est d'indice i
                    if (indicesCentres[j] == i) {
                        ; // si oui -> ajoute son x et y
                        nv_x += arrayPoints[j].getX();
                        nv_y += arrayPoints[j].getY();
                        nb++;
                    }
                }
                nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
                nv_y /= nb;
                Point nvCentre = new Point(nv_x, nv_y);
                if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY()) centresModif = true;
                centres[i] = nvCentre;
            }
        }
    }

    public Point maxDistCluster(int idCluster){
        Point[] arrayPoints;
        float maxDist = 0;
        Point maxPoint = null;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        for(int i = 0; i < plan.getNbPoints(); i++){
            if(indicesCentres[i] == idCluster){
                float dist = centres[idCluster].getDist(arrayPoints[i]);
                if(dist > maxDist){
                    maxDist = dist;
                    maxPoint = arrayPoints[i];
                }
            }
        }
        return maxPoint;
    }

    public static void main(String[] args) {
        Plan plan = new Plan();
        Point pt = new Point(2f,3f);
        plan.addPoint(pt);
        pt = new Point(3f,4f);
        plan.addPoint(pt);
        pt = new Point(4f,5f);
        plan.addPoint(pt);
        pt = new Point(5f,6f);
        plan.addPoint(pt);
        Kmeans km = new Kmeans(plan, 1);
        km.k_means();
    }
}

