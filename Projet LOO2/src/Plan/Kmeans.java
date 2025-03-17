package Plan;

import Point.Point;

public class Kmeans {
    private int nbClusters;
    private Point[] centres;
    private Plan plan;
    private int[] indicesCentres;



    public Kmeans(Plan plan, int clusters) {
        this.plan = plan;
        this.nbClusters = clusters;
        this.centres = new Point[nbClusters];
        this.indicesCentres = new int[this.plan.getNbPoints()]; // liste d'entiers représentant l'indice (dans la liste) des centres attribuées aux points;
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
     * @param
     */
    public void k_means() {
        float[] distances = new float[this.plan.getNbPoints()];
        Point[] arrayPoints = (Point[]) this.plan.getPoints().toArray();
        boolean centresModif = true;
        for (int i = 0; i < nbClusters; i++) centres[i] = arrayPoints[i];
        while (centresModif) { // tant que les centres ont été modifiés (à chaque itération) :
            for (int i = 0; i < this.plan.getNbPoints(); i++) { // pour chaque points du plan on calcule la distance
                //entre ce point et chacun des centres et l'on attribue le centre le plus proche à chacun des points
                int min = 0;
                for (int j = 0; j < centres.length; j++) { // pour chaque centres
                    distances[j] = arrayPoints[i].getDist(centres[j]); // on calcule la distance entre le point i et le centre j (indices)
                    if (distances[j] <= distances[min]) { // si la distance actuelle et la plus petite, min devient l'indice i
                        min = i;
                    }
                }
                indicesCentres[i] = min; // on attribue le centre d'indice min au point i
            }
            centresModif = false;
            for (int i = 0; i < nbClusters; i++) { // pour chaque cluster
                float nv_x = 0;
                float nv_y = 0;
                int nb = 0;
                for (int j = 0; j < this.plan.getNbPoints(); j++) { // pour chaque points, on vérifie si son centre est d'indice i
                    if (indicesCentres[j] == i) {
                        ; // si oui -> ajoute son x et y
                        nb++;
                    }
                }
                nv_x /= nb; // on calcule les moyennes des x et y obtenues -> nouveau centre
                nv_y /= nb;
                Point nvCentre = new Point(nv_x, nv_y);
                if (nvCentre.getX() != centres[i].getX() && nvCentre.getY() != centres[i].getY()) centresModif = true;
                centres[i] = nvCentre;
            }
        }
    }
}
