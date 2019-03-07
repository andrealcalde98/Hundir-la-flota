package vista;

import modelo.Tablero;

import controlador.ConsolaV3;
import java.util.Scanner;
import modelo.Barco;
import modelo.Jugador;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class HundirFloa {

    static Scanner in = new Scanner(System.in);
    static Tablero tabJug1, tabJug2;
    static Jugador j1, j2;
    static ConsolaV3 consolaJ1, consolaJ2;

    public static void main(String[] args) {

        inicializar();
        
        System.out.println("///////////////");
        System.out.println(j1.tablero.tamanyo.length);
        System.out.println(Tablero.ColumnaAInteger("A"));
        //System.out.println(j1.tablero.ColumnaALetra(12));
        System.out.println("///////////////");

        consolaJ1.iniciarBarco();
        consolaJ2.iniciarBarco();

        System.out.println("Tamaño del tablero de " + tabJug1.tamanyo.length + " por " + tabJug1.tamanyo.length);
        /*tab.mostrarTablero();

        tab.tamanyo[0][Tablero.FilaAInteger("A")] = 3;
        tab.tamanyo[1][Tablero.FilaAInteger("A")] = 3;
        tab.tamanyo[2][Tablero.FilaAInteger("A")] = 3;

        tab.tamanyo[2][Tablero.FilaAInteger("C")] = 3;
        tab.tamanyo[2][Tablero.FilaAInteger("D")] = 3;
        tab.tamanyo[2][Tablero.FilaAInteger("E")] = 3;
        tab.tamanyo[2][Tablero.FilaAInteger("F")] = 3;

        tab.mostrarTablero();

         */

        //System.out.println("Introduce posicion a descubrir");
        //String posicion = in.next();
    }

    public static void inicializar() {

        tabJug1 = new Tablero(16);
        tabJug2 = new Tablero(16);

        j1 = new Jugador("Jugador 1", tabJug1, tabJug2);
        j2 = new Jugador("Jugador 2", tabJug2, tabJug1);

        tabJug1.tableroVAcio();
        tabJug2.tableroVAcio();

        consolaJ1 = new ConsolaV3(j1);
        consolaJ2 = new ConsolaV3(j2);

    }

}
