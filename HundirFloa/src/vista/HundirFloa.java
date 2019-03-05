package vista;

import modelo.Tablero;
import controlador.consola;
import controlador.consolaV2;
import java.util.Scanner;
import modelo.Barco;
import modelo.Jugador;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class HundirFloa {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        Tablero tabJug1 = new Tablero(16);
        Tablero tabJug2 = new Tablero(15);
        
        Jugador j1 = new Jugador("Jugador 1", tabJug1, tabJug2);
        Jugador j2 = new Jugador("Jugador 2", tabJug2, tabJug1);
        
        tabJug1.tableroVAcio();
        tabJug2.tableroVAcio();
        
        consolaV2 con1 = new consolaV2(j1);
        consolaV2 con2 = new consolaV2(j2);  
        
        
        System.out.println("///////////////");
        System.out.println(j1.tablero.tamanyo.length);
        System.out.println(Tablero.ColumnaAInteger("A"));
        //System.out.println(j1.tablero.ColumnaALetra(12));
                System.out.println("///////////////");
        
        con1.anadirBarco();
        con2.anadirBarco();
        
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

}
