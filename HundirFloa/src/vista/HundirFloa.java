package vista;

import modelo.Tablero;

import controlador.ConsolaV3;
import java.util.ArrayList;
import java.util.List;
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

        List<String> a = new ArrayList<>();
        a.add("e");
        a.add("i");
        a.remove(a.indexOf("e"));
        for (String string : a) {
            System.out.println(string);
        }
        a.remove(a.indexOf("i"));
        System.out.println(a.isEmpty());
        
       // inicializar();
       // aJugar();
    }

    public static void inicializar() {

        tabJug1 = new Tablero(16);
        tabJug2 = new Tablero(16);

        j1 = new Jugador("Jugador 1", tabJug1, tabJug2);
        j2 = new Jugador("Jugador 2", tabJug2, tabJug1);

        j1.setEnemic(j2);
        j2.setEnemic(j1);

        tabJug1.tableroVAcio();
        tabJug2.tableroVAcio();

        consolaJ1 = new ConsolaV3(j1);
        consolaJ2 = new ConsolaV3(j2);

        consolaJ1.iniciarBarco();
        consolaJ2.iniciarBarco();

    }

    public static void aJugar() {
        while (j1.getVidas() > 0 && j2.getVidas() > 0) {
            consolaJ1.comprobarHit(0, 0);
            consolaJ2.comprobarHit(0, 0);

        }
    }

}
