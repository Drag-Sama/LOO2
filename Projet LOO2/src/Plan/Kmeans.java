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
     * 
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
     * 
     * @return le nombre de clusters (int)
     */
    public int getNbClusters() {
        return this.nbClusters;
    }

    /**
     * Modifie le nombre de clusters.
     * 
     * @param clusters le nombre de clusters, doit être > 0
     * @throws NegativeValue
     */
    public void setNbClusters(int clusters) throws NegativeValue {
        if (clusters >= 0) {
            this.nbClusters = clusters;
            this.centres = new Centre[nbClusters];
        } else {
            throw new NegativeValue();
        }
    }

    /**
     * Renvoie la liste des centres.
     * 
     * @return la liste des centres (Point[])
     */
    public Point[] getCentres() {
        return this.centres;
    }

    /**
     * Remplace la liste des centres (Point[]) par une nouvelle.
     * 
     * @param centres la nouvelle liste des centres
     * @throws Null
     */
    public void setCentres(Centre[] centres) throws PointIsNull {
        if (centres == null || Arrays.stream(centres).anyMatch(Objects::isNull)) {
            throw new PointIsNull("La liste de point ou l'un des points de la liste est nul.");
        } else {
            this.centres = centres;
        }
    }

    /**
     * Permet de changer le plan de Kmeans
     * 
     * @param nvPlan Le nouveau plan
     */
    public void setPlan(Plan nvPlan) {
        this.plan = nvPlan;
    }

    /**
     * Retourne le plan associé
     * 
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
     * Réalise l'algorithme k-means classique pour le plan en attribut en utilisant
     * tous les points de celui-ci
     * pas de params
     * pas de return -> tout en effet de bord sur ses propres attributs.
     * 
     * @throws ArithmeticException
     * @throws NegativeValue
     */
    public void k_means() throws ArithmeticException, NegativeValue {
        Point[] arrayPoints;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints));
        for (int i = 0; i < this.getNbClusters(); i++) {
            centres[i] = new Centre(arrayPoints[i].getX(), arrayPoints[i].getY());
        }
        boolean centresModif = true;
        while (centresModif) { // tant que les centres ont été modifiés (à chaque itération) :
            for (int i = 0; i < this.getPlan().getNbPoints(); i++) { // pour chaque points du plan on calcule la
                                                                     // distance
                centres[arrayPoints[i].getMinDistPoint(centres)].addPoint(arrayPoints[i]); // on attribue le point i au
                                                                                           // centre le plus proche.
            }
            centresModif = false;
            for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
                float nv_x = 0;
                float nv_y = 0;
                int nb = 0;
                for (Point p : centres[i].getPoints()) { // pour chaque points de ce cluster
                    // -> ajoute son x et y
                    nv_x += p.getX();
                    nv_y += p.getY();
                    nb++;
                }
                if (nb == 0) {
                    throw new ArithmeticException(); // pas de try catch pour celle-ci car la division par zéro ne cause
                                                     // pas d'erreur mais renvoie Infinity.
                }
                nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
                nv_y /= nb;
                Centre nvCentre = new Centre(nv_x, nv_y);
                if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY())
                    centresModif = true;
                System.out.println("Modif centres : ");
                for (int j = 0; j < this.getNbClusters(); j++)
                    System.out.println(" -  " + centres[j].getX() + " " + centres[j].getY());
                centres[i] = nvCentre;
            }
        }
        for (int i = 0; i < this.getPlan().getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
            centres[arrayPoints[i].getMinDistPoint(centres)].addPoint(arrayPoints[i]); // on attribue le point i au
                                                                                       // centre le plus proche.
            // System.out.println(centres[arrayPoints[i].getMinDistPoint(centres)]);
        }
    }

    /**
     * Mise en place pour le k_means classique
     * -> initialise la liste centres en choisissant aléatoirement des points du
     * plan.
     */
    public void setupK_means() {
        Point[] arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints));
        for (int i = 0; i < this.getNbClusters(); i++) {
            centres[i] = new Centre(0, 0);
            centres[i].setRandomRGB();
            centres[i].copyPoint(arrayPoints[i]);
        }
    }

    /**
     * Réalise UNE itération de l'algo kmeans → fonction pour dessiner étape par
     * étape
     * 
     * @return true si l'un des centres a été modifié, false sinon.
     * @throws ArithmeticException → division par zéro dans le cas où lors du calcul
     *                             des nouveaux centres, l'un d'eux n'est pas
     *                             retrouvé dans la liste d'indices.
     */
    public boolean k_meansOneStep() throws ArithmeticException, NegativeValue {
        boolean centresModif = false;
        Point[] arrayPoints;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints)); // mélange la liste arrayPoints
        for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
            centres[arrayPoints[i].getMinDistPoint(centres)].addPoint(arrayPoints[i]); // on attribue le point i au
                                                                      // centre le plus proche.
        }
        centresModif = false;
        for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
            float nv_x = 0;
            float nv_y = 0;
            int nb = 0;
            for (Point p : centres[i].getPoints()) { // pour chaque points de ce cluster
                // -> ajoute son x et y
                nv_x += p.getX();
                nv_y += p.getY();
                nb++;
            }
            if (nb == 0) {
                throw new ArithmeticException(); // pas de try catch pour celle-ci car la division par zéro ne cause pas
                                                 // d'erreur mais renvoie Infinity.
            }
            nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
            nv_y /= nb;
            Centre nvCentre = new Centre(nv_x, nv_y);
            if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY())
                centresModif = true;
            System.out.println("Modif centres : ");
            for (int j = 0; j < this.getNbClusters(); j++)
                System.out.println(" -  " + centres[j].getX() + " " + centres[j].getY());
            centres[i] = nvCentre;
        }
        for (int i = 0; i < this.getPlan().getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
            centres[arrayPoints[i].getMinDistPoint(centres)].addPoint(arrayPoints[i]); // on attribue le point i au
                                                                                       // centre le plus proche.
            // System.out.println(centres[arrayPoints[i].getMinDistPoint(centres)]);
        }
        
        return centresModif;
    }

    /**
     * Calcule le point le plus loin du cluster.
     * Utilisé pour calculer le rayon / diamètre du cercle pour celui-ci.
     * 
     * @param idCluster
     * @return le point le plus loin du cluster représenté par son id
     * @throws IndexOutOfBoundsException
     * @throws NegativeValue
     */
    public Point maxDistCluster(int idCluster) throws IndexOutOfBoundsException, NegativeValue {
        if (idCluster >= getNbClusters())
            throw new IndexOutOfBoundsException("L'id du cluster est trop grand");
        else if (idCluster < 0)
            throw new NegativeValue();
        Point[] arrayPoints = centres[idCluster].getPoints();
        float maxDist = -1;
        Point maxPoint = null;
        for (int i = 0; i < centres[idCluster].getNbPoints(); i++) { // pour chaque point du cluster
            float dist = centres[idCluster].getDist(arrayPoints[i]);
            if (dist > maxDist) {
                maxDist = dist;
                maxPoint = arrayPoints[i];
            }
        }
        return maxPoint;
    }

    public void setupk_meansElongated(){
        covariances = new Matrice[nbClusters];
        for(int i = 0; i < nbClusters; i ++){
            covariances[i] = new Matrice(2, 2);
            covariances[i].setToIdentite();
        }
        setupK_means();
    }

    /**
     * calcule les matrices de covariances des clusters
     * 
     * @param matrices
     *                 pas de return -> effet de bord sur matrices
     */
    public void matricesCovariances2D(){
        for(int i = 0; i < nbClusters; i ++){
            covariances[i] = new Matrice(2, 2);
            for (Point p : centres[i].getPoints()) { // pour chaque points de ce cluster
                Matrice a = new Matrice(2,1);
                a.setValeur(0, 0, p.getX() - centres[i].getX());
                a.setValeur(1,0,  p.getY() - centres[i].getY());
                a = a.multiplicationMatrice(a.getTransposee());
                covariances[i] = covariances[i].additionMatrice(a);
            }
        }
    }

    /**
     * Réalise une fois l'algorithme k-means classique pour le plan en attribut en utilisant
     * tous les points de celui-ci
     * pas de params
     * @return true si l'un des centres a été modifié, false sinon.
     */
    public boolean elongatedKmeansOneStep() {
        boolean centresModif = false;
        Point[] arrayPoints;
        arrayPoints = this.plan.getPoints().toArray(new Point[this.plan.getNbPoints()]);
        Collections.shuffle(Arrays.asList(arrayPoints)); // mélange la liste arrayPoints
        for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
            centres[getMinDistPointMahalanobis(arrayPoints[i],centres)].addPoint(arrayPoints[i]); // on attribue le point i au
                                                                      // centre le plus proche.
        }
        for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
            float nv_x = 0;
            float nv_y = 0;
            int nb = 0;
            for (Point p : centres[i].getPoints()) { // pour chaque points de ce cluster
                // -> ajoute son x et y
                nv_x += p.getX();
                nv_y += p.getY();
                nb++;
            }
            if (nb == 0) {
                throw new ArithmeticException(); // pas de try catch pour celle-ci car la division par zéro ne cause pas
                                                 // d'erreur mais renvoie Infinity.
            }
            nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
            nv_y /= nb;
            Centre nvCentre = new Centre(nv_x, nv_y);
            if (nvCentre.getX() != centres[i].getX() || nvCentre.getY() != centres[i].getY())
                centresModif = true;
            System.out.println("Modif centres : ");
            for (int j = 0; j < this.getNbClusters(); j++)
                System.out.println(" -  " + centres[j].getX() + " " + centres[j].getY());
            centres[i] = nvCentre;
        }
        for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
            centres[getMinDistPointMahalanobis(arrayPoints[i],centres)].addPoint(arrayPoints[i]); // on attribue le point i au
                                                                      // centre le plus proche.
        }
        matricesCovariances2D();
        return centresModif;
    }

   
    /**
     * Renvoie le centre le plus proche du point en calculant la distance de Mahalanobis
     * @param points le points
     * @param centre la liste des centres
     * @param numCluster le numéro du cluster du centre
     * @return l'id du centre le plus proche du point
     */
    public int getMinDistPointMahalanobis (Point point, Centre[] centres) {
        int min = 0;
        for (int i = 0; i < centres.length; i++) { // pour tous les points de la liste
            if (getDistMahalanobis(point, centres[i], i) <=getDistMahalanobis(point, centres[min], min)) {
                min = i;
            }
        }
        return min;
    }

    /**
     * Calcul et renvoie la distance de Mahalanobis entre deux points
     * @param centre le point du centre
     * @param point le point dont on veut la distance avec le centre
     * @param numCluster le numéro du cluster du point
     * @return la distance de Mahalanobis
     */
    float getDistMahalanobis(Point centre, Point point, int numCluster){
        Matrice a = new Matrice(2, 1);
        Matrice aTranspo = new Matrice(2, 2);
        Matrice covarienceInv = new Matrice(2, 2);
        a.setValeur(0, 0, point.getX() - centre.getX());
        a.setValeur(1, 0, point.getY() - centre.getY());
        aTranspo = a.getTransposee();
        System.out.println("covarience " + covariances[numCluster]);
        covarienceInv = covariances[numCluster].getInvert2x2();
        System.out.println("a : " + a + "covarience inv : " + covarienceInv);
        a = a.multiplicationMatrice(covarienceInv);
        System.out.println(a);
        a = a.multiplicationMatrice(aTranspo);
        return (float) Math.sqrt(a.getValeur(0,0));
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

        Point point5 = new Point(653, 452);
        plan.addPoint(point5);

        Point point6 = new Point(231, 321);
        plan.addPoint(point6);
        Kmeans km = new Kmeans(plan, 3);
        Point[] arrayPoints = km.getPlan().getPoints().toArray(new Point[km.getPlan().getNbPoints()]);
        for (int i = 0; i < km.getPlan().getNbPoints(); i++)
            System.out.println(arrayPoints[i]);
        km.k_means();
        for (int i = 0; i < km.getNbClusters(); i++)
            System.out.println(km.getCentres()[i]);
        // km.matricesCovariances2D();
        // for (int i = 0; i < km.getNbClusters(); i++)
        // System.out.println(km.getCovariances()[i]);
    }
}
