package Plan;

/**
 * Matrice
 * @param dimX La taille x de la matrice (int)
 * @param dimY La taille y de la matrice (int)
 * @param valeur Les valeurs de la matrice (float)
 */
public class Matrice {
    private int dimX;
    private int dimY;
    float valeur[][];

    /**
     * Crée une matrice de taille nvDimX * nvDimY (int)
     * @param nvDimX (float)
     * @param nvDimY (float)
     */
    public Matrice(int nvDimX, int nvDimY){
        dimX = nvDimX;
        dimY = nvDimY;
        valeur = new float[dimY][dimX];
        for(int y = 0; y < dimY; y++){
            for(int x =0; x < dimX; x++){
                valeur[y][x] = 0;
                
            }
        }
    }

    //Getter Setter

    /**
     * Renvoie la dimension X de la matrice
     * @return Dimension X
     */
    public int getDimX() {
        return dimX;
    }

    /**
     * Renvoie la dimension Y de la matrice
     * @return Dimension Y
     */
    public int getDimY() {
        return dimY;
    }




    /**
     * met la nouvelle valeur en x et y dans la matrice
     * @param x position x
     * @param y position y
     * @param nvVal nouvelle valeur
     */
    public void setValeur(int x, int y, float nvVal){
        if(x >= 0 && x < dimX && y >= 0 && y < dimY)
            valeur[x][y] = nvVal;
        else{
            System.out.println("Erreur de paramètre, x ou y ne sont pas compris entre 0 et "+ dimX + " ou " + dimY);
        }
    }

    /**
     * Renvoie la valeur en x et y de la matric
     * @param x position x
     * @param y position y
     * @return la valeur en x et y
     */
    public float getValeur(int x, int y){
        if(x >= 0 && x < dimX && y >= 0 && y < dimY)
            return valeur[x][y];
        else{
            System.out.println("Erreur de paramètre, x ou y ne sont pas compris entre 0 et "+ dimX + " ou " + dimY);
            return -1;
        }
    }

   /**
    * Renvoie la matrice transposée
    * @return matrice transposée
    */
    public Matrice getTransposee(){
        Matrice transpose = new Matrice(dimY, dimX);
        for(int y = 0; y < dimY; y ++){
            for(int x = 0; x < dimX; x++){
                transpose.setValeur(y, x, this.getValeur(x, y));
            }
        }
        return transpose;  
    }

    /**
     * Renvoie le résultat de la multiplication de 2 matrices
     * @param mat2 la deuxième matrice
     * @return matrice résultant de la multiplication
     */
    public Matrice multiplicationMatrice(Matrice mat2){
        Matrice resultat = new Matrice(dimX, mat2.getDimY());
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){ 
              resultat.setValeur(i, j, 0);   
              for(int k=0; k<2 ;k++)    
              { 
                resultat.setValeur(i, j, resultat.getValeur(i, j) + getValeur(i, k) * mat2.getValeur(k, j));    
              }
            }
          }  
        return resultat;
    }

    public Matrice additionMatrice(Matrice mat2){
        if(dimX == mat2.getDimX() && dimY == mat2.dimY){
            Matrice resultat = new Matrice(dimX, dimY);
            for(int i=0; i<dimY; i++){
                for(int j=0; j<dimX; j++){ 
                  resultat.setValeur(j, i, this.getValeur(j, i) + mat2.getValeur(j, i));   
                }
              }  
              return resultat;
        }
        else{
            System.out.println("Erreur: Les deux matrices ne sont pas de la même dimension");
            return null;
        }
        

    }

    /**
     * Transforme la matrice en matrice d'identité, ne renvoie rien
     */
    public void setToIdentite(){
        for(int y = 0; y < dimY; y++){
            for(int x =0; x < dimX; x++){
                if(x != y)
                    valeur[y][x] = 0;
                else 
                    valeur[y][x] = 1;
            }
        }
    }

    /**
     * Renvoie l'inverse de la matrice
     * @return une nouvelle matrice qui est l'inverse de la matrice actuel
     */
    public Matrice getInvert2x2(){
        float a = getValeur(0,0);
        float b = getValeur(0, 1);
        float c = getValeur(1,0);
        float d = getValeur(1,1);
        float det =  a * d - b * c;
        if (Math.abs(det) < 1e-10) {
            throw new RuntimeException("Matrice non inversible (det = 0)");
        }
        Matrice inv = new Matrice(2,2);
        inv.setValeur(0, 0, d/det);
        inv.setValeur(0, 1, -b/det);
        inv.setValeur(1, 0, -c/det);
        inv.setValeur(1, 1, a/det);

        return inv;
    }

    @Override
    public String toString() {
        String s = "Matrice : \n";
        for(int y = 0; y < dimY; y++){
            for(int x = 0; x < dimX; x++){
                s+= getValeur(x, y) + " ";
            }
            s+= "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Matrice m = new Matrice(2, 2);
        m.getTransposee();
        m.setValeur(0, 0, 2);
        m.setValeur(1, 1, 1);
        m.setValeur(1, 0, 1);
        m.setValeur(0, 1, 2);
        m.getValeur(0, 0);
        System.out.println(m);
        Matrice res =  m.multiplicationMatrice(m);
        System.out.println(res);
    }
}
