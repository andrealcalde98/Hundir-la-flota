package vista;

import modelo.Tablero;
import controlador.consola;
import controlador.consolaV2;
import java.util.Scanner;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class HundirFloa {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Tablero tab = new Tablero(15);
        tab.tableroVAcio();
        System.out.println("Tamaño del tablero de " + tab.tamanyo.length + " por " + tab.tamanyo.length);
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
        consolaV2 a = new consolaV2();
        a.anadirBarco();
        System.out.println("Introduce posicion a descubrir");
        String posicion = in.next();
        System.out.println(a.comprobarHit(posicion,tab.tamanyo));
         
    }

}
