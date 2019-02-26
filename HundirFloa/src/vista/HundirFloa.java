package vista;

import modelo.Tablero;
import controlador.consola;
import java.util.Scanner;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class HundirFloa {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Tablero tab = new Tablero(12);
        tab.tableroVAcio();
        System.out.println("Tamaño del tablero de " + tab.tamanyo.length+" por "+tab.tamanyo.length);
        tab.mostrarTablero();
        System.out.println("Cuantos barcos quieres introducir?");
        int num = in.nextInt();
        consola.llenarTablero(tab.tamanyo, num);
    }

}
