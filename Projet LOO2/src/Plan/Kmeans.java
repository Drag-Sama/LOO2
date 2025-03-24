package Plan;

import Point.Point;

import java.util.*;

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

    /**
     * retourne un tableau de d'entiers uniques aléatoire de 0 à max
     * @param max
     * @return le tableau d'entiers aléatoires
     */
    public int[] TabIntAleaDistinct(int max) {
        Random randNum = new Random();
        Set<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < max) {
            set.add(randNum.nextInt(10)+1);
        }
        return null;
    }

    /////// METHODES K-MEANS ////////

    /**
     * réalise l'algorithme k-means classique  pour le plan en attribut en utilisant tous les points de celui-ci
     * pas de params
     * pas de return -> tout en effet de bord sur ses propres attributs.
     * */
    public void k_means() throws ArithmeticException {
        Point[] arrayPoints;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints));
        for (int i = 0; i < this.getNbClusters(); i++) centres[i] = arrayPoints[i];
        boolean centresModif = true;
        while (centresModif) { // tant que les centres ont été modifiés (à chaque itération) :
            for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
                indicesCentres[i] = arrayPoints[i].getMinDistPoint(centres); // on attribue le centre d'indice min au point i
            }
            centresModif = false;
            for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
                float nv_x = 0;
                float nv_y = 0;
                int nb = 0;
                System.out.println("Tableau indices centres : ");
                for (int j = 0; j < this.plan.getNbPoints(); j++) { // pour chaque points, on vérifie si son centre est d'indice i
                    System.out.println(" -  " + indicesCentres[j]);
                    if (indicesCentres[j] == i) {
                        ; // si oui -> ajoute son x et y
                        nv_x += arrayPoints[j].getX();
                        nv_y += arrayPoints[j].getY();
                        nb++;
                    }
                }
                nb = 0;
                if (nb == 0) {
                    throw new ArithmeticException(); // pas de try catch pour celle-ci car la division par zéro ne cause pas d'erreur mais renvoie Infinity.
                }
                    nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
                    nv_y /= nb;
                Point nvCentre = new Point(nv_x, nv_y);
                if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY()) centresModif = true;
                System.out.println("Modif centres : ");
                for (int j = 0; j < this.getNbClusters(); j++) System.out.println(" -  " + centres[j].getX() + " " + centres[j].getY());
                centres[i] = nvCentre;
            }
        }
    }

    /**
     * réalise UNE itération de l'algo kmeans -> fonction pour dessiner étape par étape
     * @return true si l'un des centres a été modifié, false sinon.
     */
    public boolean k_meansOneStep() throws ArithmeticException{
        boolean centresModif = false;
        Point[] arrayPoints;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints));
        for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
            indicesCentres[i] = arrayPoints[i].getMinDistPoint(centres); // on attribue le centre d'indice min au point i
        }
        for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
            float nv_x = 0;
            float nv_y = 0;
            int nb = 0;
            System.out.println("Tableau indices centres : ");
            for (int j = 0; j < this.plan.getNbPoints(); j++) { // pour chaque points, on vérifie si son centre est d'indice i
                System.out.println(" -  " + indicesCentres[j]);
                if (indicesCentres[j] == i) {
                    ; // si oui -> ajoute son x et y
                    nv_x += arrayPoints[j].getX();
                    nv_y += arrayPoints[j].getY();
                    nb++;
                }
            }
            nb = 0;
            if (nb == 0) {
                throw new ArithmeticException(); // pas de try catch pour celle-ci car la division par zéro ne cause pas d'erreur mais renvoie Infinity.
            }
            nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
            nv_y /= nb;
            Point nvCentre = new Point(nv_x, nv_y);
            if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY()) centresModif = true;
            System.out.println("Modif centres : ");
            for (int j = 0; j < this.getNbClusters(); j++) System.out.println(" -  " + centres[j].getX() + " " + centres[j].getY());
            centres[i] = nvCentre;
        }
        return centresModif;
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
    }
}

