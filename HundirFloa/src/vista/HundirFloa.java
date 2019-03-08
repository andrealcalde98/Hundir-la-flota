package vista;

import modelo.Tablero;

import modelo.*;
import controlador.*;
import java.util.Scanner;


/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class HundirFloa {

    static Scanner in = new Scanner(System.in);
    static Tablero tabJug1, tabJug2;
    static Jugador j1, j2;
    static ConsolaV3 consolaJ1, consolaJ2;

    //Hacemos el juego de tamaño estatico
    //x1 Acorazado - 4 casillas
    //x2 Cruceros - 3 casillas
    //x3 Submarinos - 2 casillas
    //x4 Desctructores - 1 casilla
    public static void main(String[] args) {

        inicializar();
        aJugar();

    }

    public static void inicializar() {

        tabJug1 = new Tablero(16);
        tabJug2 = new Tablero(16);

        tabJug1.tableroVAcio();
        tabJug2.tableroVAcio();

        j1 = new Jugador(tabJug1);
        j2 = new Jugador(tabJug2);

        j1.setEnemic(j2);
        j2.setEnemic(j1);
        
        consolaJ1 = new ConsolaV3(j1);
        consolaJ1.hola(1);
        consolaJ2 = new ConsolaV3(j2);
        consolaJ2.hola(2);

        consolaJ1.iniciarBarco();
        consolaJ2.iniciarBarco();
    }

    public static void aJugar() {
        while (j1.getVidas() > 0 && j2.getVidas() > 0) {
            consolaJ1.tirada();
            consolaJ2.tirada();
        }
    }

}
