package Plan;

import Point.Point;
import Point.Centre
import exceptions.NegativeValue;
import exceptions.PointIsNull;

import java.util.*;

/**
 * Classe pour les différents algorithmes K-Means
 */
public class Kmeans {
    private int nbClusters;
    private Centre[] centres;
    private Plan plan;
    private int[] indicesCentres; // liste d'entiers représentant l'indice (dans la liste) des
                                  // centres attribuées aux points;


    /**
     * Crée une classe Kmeans avec un Plan et son nombre de clusters
     * @param plan
     * @param clusters
     */
    public Kmeans(Plan plan, int clusters) {
        this.plan = plan;
        this.nbClusters = clusters;
        this.centres = new Centre[nbClusters];
        this.indicesCentres = new int[this.plan.getNbPoints()];

    }

    /**
     * Renvoie le nombre de clusters.
     * @return le nombre de clusters (int)
     */
    public int getNbClusters() {return this.nbClusters;}

    /**
     * Modifie le nombre de clusters.
     * @param clusters le nombre de clusters, doit être > 0
     * @throws NegativeValue
     */
    public void setNbClusters(int clusters) throws NegativeValue{
        if (clusters >= 0) {
            this.nbClusters = clusters;
        }
        else {
            throw new NegativeValue();
        }
    }

    /**
     * Renvoie la liste des centres.
     * @return la liste des centres (Point[])
     */
    public Point[] getCentres() {return this.centres;}

    /**
     * Remplace la liste des centres (Point[]) par une nouvelle.
     * @param centres la nouvelle liste des centres
     * @throws Null
     */
    public void setCentres(Centre[] centres) throws PointIsNull {
        if (centres == null || Arrays.stream(centres).anyMatch(Objects::isNull)){
            throw new PointIsNull("La liste de point ou l'un des points de la liste est nul.");
        }
        else {
            this.centres = centres;
        }
    }

    /**
     * Permet de changer le plan de Kmeans
     * @param nvPlan Le nouveau plan
     */
    public void setPlan(Plan nvPlan){
        this.plan = nvPlan;
        this.indicesCentres = new int[this.plan.getNbPoints()];
    }

    /**
     * Retourne le plan associé
     * @return le plan associé (Plan)
     */
    public Plan getPlan() {
        return this.plan;
    }

    /**
     * Renvoie la liste des indices des centres indicesCentres.
     * @return la liste des indices des centres indicesCentres (liste d'entiers / int[])
     */
    public int[] getIndicesCentres () {return this.indicesCentres;}

    /**
     * Remplace la liste des indices des centres indicesCentres par une nouvelle.
     * @param indicesCentres la nouvelle liste des indices des centres
     * @throws NegativeValue
     * @throws IndexOutOfBoundsException
     */
    public void setIndicesCentres (int[] indicesCentres) throws NegativeValue,IndexOutOfBoundsException {
        if (Arrays.stream(indicesCentres).anyMatch(ind -> ind < 0)) {
            throw new NegativeValue();
        }
        else if (Arrays.stream(indicesCentres).anyMatch(ind -> ind >= this.getNbClusters())) {
            throw new IndexOutOfBoundsException("L'un des indices de la liste d'indices est trop grand.");
        }
        else {
            this.indicesCentres = indicesCentres;
        }
    }

    /////// METHODES K-MEANS ////////

    /**
     * Réalise l'algorithme k-means classique  pour le plan en attribut en utilisant tous les points de celui-ci
     * pas de params
     * pas de return -> tout en effet de bord sur ses propres attributs.
     * @throws ArithmeticException
     * @throws NegativeValue
     * */
    public void k_means() throws ArithmeticException, NegativeValue{
        Point[] arrayPoints;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints));
        for (int i = 0; i < this.getNbClusters(); i++) centres[i] = (Centre)arrayPoints[i];
        boolean centresModif = true;
        while (centresModif) { // tant que les centres ont été modifiés (à chaque itération) :
            for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
                centres[arrayPoints[i].getMinDistPoint(centres)].; // on attribue le centre d'indice min au point i
            }
            centresModif = false;
            for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
                float nv_x = 0;
                float nv_y = 0;
                int nb = 0;
                for (int j = 0; j < this.plan.getNbPoints(); j++) { // pour chaque points, on vérifie si son centre est d'indice i
                    System.out.println(" -  " + indicesCentres[j]);
                    if (indicesCentres[j] == i) {
                        ; // si oui -> ajoute son x et y
                        nv_x += arrayPoints[j].getX();
                        nv_y += arrayPoints[j].getY();
                        nb++;
                    }
                }
                if (nb == 0) {
                    throw new ArithmeticException(); // pas de try catch pour celle-ci car la division par zéro ne cause pas d'erreur mais renvoie Infinity.
                }
                    nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
                    nv_y /= nb;
                Centre nvCentre = new Centre(nv_x, nv_y);
                if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY()) centresModif = true;
                System.out.println("Modif centres : ");
                for (int j = 0; j < this.getNbClusters(); j++) System.out.println(" -  " + centres[j].getX() + " " + centres[j].getY());
                centres[i] = nvCentre;
            }
        }
    }

    /**
     * Mise en place pour le k_means classique
     * -> initialise la liste centres en choisissant aléatoirement des points du plan.
     */
    public void setupK_means() {
        Point[] arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints));
        for (int i = 0; i < this.getNbClusters(); i++) centres[i] = arrayPoints[i];
    }

    /**
     * Réalise UNE itération de l'algo kmeans → fonction pour dessiner étape par étape
     * @return true si l'un des centres a été modifié, false sinon.
     * @throws ArithmeticException → division par zéro dans le cas où lors du calcul des nouveaux centres, l'un d'eux n'est pas retrouvé dans la liste d'indices.
     */
    public boolean k_meansOneStep() throws ArithmeticException, NegativeValue{
        boolean centresModif = false;
        Point[] arrayPoints;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        System.out.println("Nb Points : " + plan.getNbPoints());
        Collections.shuffle(Arrays.asList(arrayPoints)); // mélange la liste arrayPoints
        for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
            indicesCentres[i] = arrayPoints[i].getMinDistPoint(centres); // on attribue le centre d'indice min au point i
        }
        for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
            float nv_x = 0;
            float nv_y = 0;
            int nb = 0;
            System.out.println("Tableau indices centres : ");
            for (int j = 0; j < this.plan.getNbPoints(); j++) { // pour chaque point, on vérifie si son centre est d'indice i
                System.out.println(" -  " + indicesCentres[j]);
                if (indicesCentres[j] == i) {
                    ; // si oui → ajoute son x et y.
                    nv_x += arrayPoints[j].getX();
                    nv_y += arrayPoints[j].getY();
                    nb++;
                }
            }
            if (nb == 0) {
                throw new ArithmeticException(); // pas de try catch pour celle-ci, car la division par zéro ne cause pas d'erreur mais renvoie Infinity.
            }
            nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
            nv_y /= nb;
            Point nvCentre = new Point(nv_x, nv_y);
            if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY()) centresModif = true; // si le centre a été modifié
            System.out.println("Modif centres : ");
            for (int j = 0; j < this.getNbClusters(); j++) System.out.println(" -  " + centres[j].getX() + " " + centres[j].getY()); // pour afficher
            centres[i] = nvCentre;
        }
        return centresModif;
    }

    /**
     * Calcule le point le plus loin du cluster.
     * Utilisé pour calculer le rayon / diamètre du cercle pour celui-ci.
     * @param idCluster
     * @return le point le plus loin du cluster représenté par son id
     * @throws IndexOutOfBoundsException
     * @throws NegativeValue
     */
    public Point maxDistCluster(int idCluster) throws IndexOutOfBoundsException, NegativeValue {
        if (idCluster >= getNbClusters()) throw new IndexOutOfBoundsException("L'id du cluster est trop grand");
        else if (idCluster < 0) throw new NegativeValue();
        Point[] arrayPoints;
        float maxDist = 0;
        Point maxPoint = null;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        for(int i = 0; i < plan.getNbPoints(); i++){ // pour chaque point
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


    public void matricesCovariances2D(Matrice[] matrices) {
        Point[] points = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        for (int indCluster = 0; indCluster < getNbClusters(); indCluster++) { // pour chaque cluster
            float moyenneX = 0;
            float moyenneY = 0;
            int nbPoints = 0;
            for (int indPoint = 0; indPoint < getPlan().getNbPoints(); indPoint++) { // pour chaque point du plan
                if (getIndicesCentres()[indPoint] == indCluster) { // si le point appartient au cluster
                    moyenneX += points[indPoint].getX(); // on ajoute son X et Y à leurs moyennes respectives
                    moyenneY += points[indPoint].getY();
                    nbPoints++;
                }
            }
            moyenneX /= nbPoints;
            moyenneX /= nbPoints;

        }
    }

    /**
     * Réalise l'algorithme k-means classique  pour le plan en attribut en utilisant tous les points de celui-ci
     * pas de params
     * pas de return -> tout en effet de bord sur ses propres attributs.
     */
    public void elongatedKmeans() {

    }

    public static void main(String[] args) throws NegativeValue{
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

