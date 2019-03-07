package modelo;

import java.io.Console;

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
                switch (tamanyo[i][j]) {
                    case 0:
                        System.out.print("  X  ");
                        break;
                    case 1:
                        System.out.print("  B  ");
                        break;
                    case 2:
                        System.out.print("  A  ");
                        break;
                    case 3:
                        System.out.print("  T  ");
                        break;
                    case 4:
                        System.out.print("  W  ");
                        break;
                    case 5:
                        System.out.print("  H  ");
                        break;
                }
            }
            System.out.println(" ");
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
