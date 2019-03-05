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
        System.out.print("   ");
        for (int z = 0; z < tamanyo.length; z++) {
            System.out.print("  " + ColumnaALetra(z) + "  ");
        }
        System.out.println("\n");
        for (int i = 0; i < tamanyo.length; i++) {
            System.out.printf("%-3s", i);
            for (int j = 0; j < tamanyo.length; j++) {
                if (tamanyo[i][j] == 0) {
                    System.out.print("  X  ");

                } else if (tamanyo[i][j] == 1) {
                    System.out.print("  0  ");

                } else {
                    System.out.print("  A  ");
                }
            }
            System.out.println("");
        }
    }

//Son columnas en verda
//Las columnas sulen mostrarse como letras, esto es simplemente visual.
//No debería afectar al codigo.
    public static char ColumnaALetra(int a) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return letras.charAt(a);
    }

    //Letra de la columna a Numero, esto sí que afecta al codigo.
    public static int ColumnaAInteger(String a) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return letras.indexOf(a);

    }

}
