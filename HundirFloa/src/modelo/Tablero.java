package modelo;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class Tablero {

    public int[][] tamanyo;
    public Barco[] listaBarcos;

    public Tablero(int cuadrillas) {
        this.tamanyo = new int[cuadrillas][cuadrillas];
    }

    public void tableroVAcio() {
        for (int[] tamanyo1 : tamanyo) {
            for (int j = 0; j < tamanyo1.length; j++) {
                tamanyo1[j] = 0;
            }
        }
    }

    public void mostrarTablero() {
        int x = 0;
        for (int i = 0; i < tamanyo.length; i++) {
            for (int j = 0; j < tamanyo.length; j++) {
                if (tamanyo[i][j] == 0) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.println("");
        }
    }
    
    //Las columnas sulen mostrarse como letras, esto es simplemente visual.
    //No debería afectar al codigo.
    public static char columnaALetra(int a){
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        return letras.charAt(a);
    }
    
    //Letra de la columna a Numero, esto sí que afecta al codigo.
    public static int columnaAInteger(String a){
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        return letras.indexOf(a);
        
    }
    

}
