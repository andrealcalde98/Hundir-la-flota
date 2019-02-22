package vista;

import modelo.Tablero;
import controlador.consola;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class HundirFloa {

    public static void main(String[] args) {
        Tablero tab = new Tablero(9);
        tab.tableroVAcio();
        System.out.println("Tamaño del tablero de " + tab.tamanyo.length+" por "+tab.tamanyo.length);
        tab.mostrarTablero();
        consola.llenarTablero(tab.tamanyo, 9);
    }

}
