/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Scanner;
import modelo.Barco;
import modelo.Jugador;
import modelo.Tablero;

/**
 *
 * @author Lorenzo
 */
public class consolaV2 {

    public Jugador jugador;

    public static Scanner in = new Scanner(System.in);

    public consolaV2(Jugador jugador) {
        this.jugador = jugador;
    }
    //Hacemos el juego de tamaño estatico
    //x1 Acorazado - 4 casillas
    //x2 Cruceros - 3 casillas
    //x3 Submarinos - 2 casillas
    //x4 Desctructores - 1 casilla

    public void anadirBarco() {
        System.out.println("Añade los barcos:");

        for (Barco barquito : jugador.listaBarcos) {
            int columnaIni, filaIni, filaFin = 0, columnaFin = 0;
            String posInicial = "", posFinal = "";

            //Mostrar el tablero
            System.out.println(barquito.getTipo() + " tamaño de "
                    + barquito.getTamanyo() + " casillas.");

            System.out.println("Orientación del barco (H/V):");
            String orientacion = in.nextLine().toUpperCase();

            while (!orientacion.equals("H") && !orientacion.equals("V")) {
                System.out.println("Valor incorrecto, vuelve a intentarlo.");
                System.out.println("Orientación del barco (H/V):");
                orientacion = in.nextLine().toUpperCase();
            }

            switch (orientacion) {
                case "V":
                    System.out.println("Posición incial:");
                    columnaIni = posicionColumna(jugador.tablero.tamanyo);
                    filaIni = posicionFila(jugador.tablero.tamanyo);
                    //Aquí hay que hacer comprobaciones.
                    //si tiene barcos adyacientes.

                    in.nextLine();

                    posInicial = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaIni);

                    System.out.println("Posición final:");
                    //Aquí hay que hacer comprobaciones.
                    //De tamaño y si tiene barcos adyacientes.
                    filaFin = posicionFila(jugador.tablero.tamanyo);
                    in.nextLine();

                    posFinal = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaFin);

                    if (posValida(orientacion, columnaFin, columnaFin, filaIni, filaFin)) {
                        System.out.println("Es correcto");
                        AnadirbarcoTablero(orientacion, posInicial, posFinal);
                        bloqueaAdyacentes(columnaIni, columnaFin, filaIni, filaFin);

                    } else {
                        System.out.println("Es incorrecto");
                    }

                    break;

                case "H":
                    System.out.println("Posición incial:");
                    columnaIni = posicionColumna(jugador.tablero.tamanyo);
                    filaIni = posicionFila(jugador.tablero.tamanyo);
                    //Aquí hay que hacer comprobaciones.
                    //si tiene barcos adyacientes.
                    in.nextLine();
                    posInicial = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaIni);

                    System.out.println("Posición final:");
                    //Aquí hay que hacer comprobaciones.
                    //De tamaño y si tiene barcos adyacientes.
                    columnaFin = posicionColumna(jugador.tablero.tamanyo);

                    posFinal = Tablero.ColumnaALetra(columnaFin) + String.valueOf(filaIni);

                    if (posValida(orientacion, columnaFin, columnaFin, filaIni, filaFin)) {
                        System.out.println("Es correcto");
                        AnadirbarcoTablero(orientacion, posInicial, posFinal);
                    } else {
                        System.out.println("Es incorrecto");
                    }
                    break;
            }

            jugador.tablero.mostrarTablero();
            System.out.println("Barco: " + posInicial + "-" + posFinal);
            System.out.println("------------------------");
        }
    }

    public boolean posValida(String orientacion, int colInicial, int colFinal, int filInicial, int filFinal) {
        //comprobar que las posiciones no sean 0-0 ni lenght antes del for.(que no se salgan del tablero)
        boolean correcto = true;

        if (orientacion.equals("H")) {
            for (int i = colInicial; i < colFinal; i++) {
                if (jugador.tablero.tamanyo[filInicial][i] != 0) {
                    System.out.println("Posición invalida.");
                    correcto = false;
                }
            }
        } else {
            for (int i = filInicial; i < filFinal; i++) {
                if (jugador.tablero.tamanyo[i][colInicial] != 0) {
                    System.out.println("Posición invalida.");
                    correcto = false;
                }
            }
        }
        return correcto;
    }

    public void AnadirbarcoTablero(String orientacion, String posInicial, String posFinal) {
        String ColumnaIncial = posInicial.substring(0, 1);
        String FilaIncial = posInicial.substring(posInicial.indexOf(ColumnaIncial) + 1);

        String ColumnaFinal = posFinal.substring(0, 1);
        String FilaFinal = posFinal.substring(posFinal.indexOf(ColumnaFinal) + 1);

        // variables para tratar
        int ColumnaIni = Tablero.ColumnaAInteger(ColumnaIncial);
        int FilaIni = Integer.parseInt(FilaIncial);

        int ColumnaFin = Tablero.ColumnaAInteger(ColumnaFinal);
        int FilaFin = Integer.parseInt(FilaFinal);

        if (orientacion.equals("H")) {
            for (int i = ColumnaIni; i <= ColumnaFin; i++) {
                jugador.tablero.tamanyo[FilaIni][i] = 1;
            }
        } else {
            for (int i = FilaIni; i <= FilaFin; i++) {
                jugador.tablero.tamanyo[i][ColumnaIni] = 1;
            }
        }
    }

    public static int posicionColumna(int[][] tablero) {
        int cambiado = -1;
        do {
            System.out.print("Columna [");
            for (int j = 0; j < tablero.length; j++) {
                if (j == tablero.length - 1) {
                    System.out.print(Tablero.ColumnaALetra(j) + "] :");
                } else {
                    System.out.print(Tablero.ColumnaALetra(j) + ", ");
                }
            }
            String aCambiar = in.nextLine().toUpperCase();
            cambiado = Tablero.ColumnaAInteger(aCambiar);
            //no pilla la Ñ - la ultima letra
        } while (cambiado < 0 || cambiado >= tablero.length + 1);

        return cambiado;
    }

    public static int posicionFila(int[][] tablero) {
        int fila = -1;
        do {
            System.out.print("Fila [");
            for (int j = 0; j < tablero.length; j++) {
                if (j == tablero.length - 1) {
                    System.out.print(j + "] :");
                } else {
                    System.out.print(j + ", ");
                }
            }
            try {
                fila = in.nextInt();
            } catch (Exception e) {
                System.out.println("Eso no es un numero, broder");
                fila = -1;
                in.nextLine();
            }
        } while (fila < 0 || fila >= tablero.length);

        return fila;
    }

    public static void comprobarHit(int Cini, int Fini, int[][] tablero) {

        int pos = tablero[Cini][Fini];
        if (pos != 0) {
            System.out.println("Agua");
        } else {
            System.out.println("Tocado");
            pos = 0; // poner que aqui el 0 pase de nuevo a ser una X
        }
    }

    public void bloqueaAdyacentes(int ColumnaIni, int ColumnaFin, int FilaIni, int FilaFin) {
        while(jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni]>0){
        jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni] = 2;
        jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni - 1] = 2;
        jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni + 1] = 2;
        jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni] = 2;
        jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni - 1] = 2;
        jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni + 1] = 2;
        for (int x = FilaIni; x <= FilaFin; x++) {

            jugador.tablero.tamanyo[x][ColumnaIni] = 1;
            jugador.tablero.tamanyo[x][ColumnaIni - 1] = 2;
            jugador.tablero.tamanyo[x][ColumnaIni + 1] = 2;
           }
    }
}
