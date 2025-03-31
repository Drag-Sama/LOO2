package Plan;

import Point.Point;
import Point.Centre;
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
    private Matrice[] covariances;

    /**
     * Crée une classe Kmeans avec un Plan et son nombre de clusters
     * @param plan
     * @param clusters
     */
    public Kmeans(Plan plan, int clusters) {
        this.plan = plan;
        this.nbClusters = clusters;
        this.centres = new Centre[nbClusters];
        this.covariances = new Matrice[nbClusters];
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
    }

    /**
     * Retourne le plan associé
     * @return le plan associé (Plan)
     */
    public Plan getPlan() {
        return this.plan;
    }

    public void setCovariances(Matrice[] mat) {
        covariances = mat;
    }

    public Matrice[] getCovariances() {
        return covariances;
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
        for (int i = 0; i < this.getNbClusters(); i++) {
            centres[i] = new Centre(arrayPoints[i].getX(),arrayPoints[i].getY());
        }
        boolean centresModif = true;
        while (centresModif) { // tant que les centres ont été modifiés (à chaque itération) :
            for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
                centres[arrayPoints[i].getMinDistPoint(centres)].addPoint(arrayPoints[i]); // on attribue le point i au centre le plus proche.
            }
            centresModif = false;
            for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
                float nv_x = 0;
                float nv_y = 0;
                int nb = 0;
                for (Point p : centres[i].getPoints()) { // pour chaque points de ce cluster
                    //-> ajoute son x et y
                    nv_x += p.getX();
                    nv_y += p.getY();
                    nb++;
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
        for (int i = 0; i < this.getNbClusters(); i++) centres[i].copyPoint(arrayPoints[i]);
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
            centres[arrayPoints[i].getMinDistPoint(centres)].addPoint(arrayPoints[i]); // on attribue le point i au centre le plus proche.
        }
        centresModif = false;
        for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
            float nv_x = 0;
            float nv_y = 0;
            int nb = 0;
            for (Point p : centres[i].getPoints()) { // pour chaque points de ce cluster
                //-> ajoute son x et y
                nv_x += p.getX();
                nv_y += p.getY();
                nb++;
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
        Point[] arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        float maxDist = 0;
        Point maxPoint = null;
        for(int i = 0; i < centres[idCluster].getNbPoints(); i++){ // pour chaque point du cluster
            float dist = centres[idCluster].getDist(arrayPoints[i]);
            if(dist > maxDist){
                maxDist = dist;
                maxPoint = arrayPoints[i];
            }
        }
        return maxPoint;
    }

    /**
     * calcule les matrices de covariances des clusters
     * @param matrices
     * pas de return -> effet de bord sur matrices
     */
    public void matricesCovariances2D() throws ArithmeticException{
        Point[] points = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        for (int indCluster = 0; indCluster < getNbClusters(); indCluster++) { // pour chaque cluster
            covariances[indCluster] = new Matrice(2,2);
            for (Point p : centres[indCluster].getPoints()) { // pour chaque point du plan
                covariances[indCluster].setValeur(0,0,covariances[indCluster].getValeur(0,0) + (float) Math.pow(p.getX() - centres[indCluster].getX(),2)); // calcul des valeurs de la matrice de covariance
                covariances[indCluster].setValeur(1,0,covariances[indCluster].getValeur(1,0)
                        + (p.getX() - centres[indCluster].getX())*(p.getY() - centres[indCluster].getY()));
                covariances[indCluster].setValeur(0,1,covariances[indCluster].getValeur(0,1)
                        + (p.getX() - centres[indCluster].getX())*(p.getY() - centres[indCluster].getY()));
                covariances[indCluster].setValeur(1,1,covariances[indCluster].getValeur(1,1) + (float) Math.pow(p.getY() - centres[indCluster].getY(),2));
            }
            if (centres[indCluster].getNbPoints() == 0) throw new ArithmeticException("Division par zéro");
            covariances[indCluster].setValeur(0,0,covariances[indCluster].getValeur(0,0) / centres[indCluster].getNbPoints());
            covariances[indCluster].setValeur(1,0,covariances[indCluster].getValeur(1,0) / centres[indCluster].getNbPoints());
            covariances[indCluster].setValeur(0,1,covariances[indCluster].getValeur(0,1) / centres[indCluster].getNbPoints());
            covariances[indCluster].setValeur(1,1,covariances[indCluster].getValeur(1,1) / centres[indCluster].getNbPoints());
        }

    }

    /**
     * Réalise l'algorithme k-means classique  pour le plan en attribut en utilisant tous les points de celui-ci
     * pas de params
     * pas de return -> tout en effet de bord sur ses propres attributs.
     */
    public void elongatedKmeans() {

    }

    public static void main(String[] args){
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
        for (int i = 0; i < km.getPlan().getNbPoints(); i++) System.out.println(km.getPlan().getPoints().toArray(new Point[km.getPlan().getNbPoints()])[i]);
        km.k_means();
        for (int i = 0; i < km.getNbClusters(); i++) System.out.println(km.getCentres()[i]);
        km.matricesCovariances2D();
        for (int i = 0; i < km.getNbClusters(); i++) System.out.println(km.getCovariances()[i]);
    }
}

