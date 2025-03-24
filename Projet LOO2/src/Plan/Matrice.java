package Plan;

public class Matrice {
    int dimX;
    int dimY;
    float valeur[][];

    public Matrice(int nvDimX, int nvDimY){
        dimX = nvDimX;
        dimY = nvDimY;
        valeur = new float[dimY][dimX];
    }

    //Getter Setter

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public void setDimX(int dimX) {
        this.dimX = dimX;
    }

    public void setDimY(int dimY) {
        this.dimY = dimY;
    }


    /**
     * met la nouvelle valeur en x et y dans la matrice
     * @param x position x
     * @param y position y
     * @param nvVal nouvelle valeur
     */
    public void setValeur(int x, int y, float nvVal){
        if(x > 0 && x < dimX && y > 0 && y < dimY)
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
        if(x > 0 && x < dimX && y > 0 && y < dimY)
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
}
