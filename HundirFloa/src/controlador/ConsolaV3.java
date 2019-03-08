/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Scanner;
import modelo.*;

/**
 *
 * @author Lorenzo
 */
public class ConsolaV3 {

    public Jugador jugador;

    public static Scanner in = new Scanner(System.in);

    public ConsolaV3(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public void hola(int player){
        System.out.println("Nombre del jugador :" + player);
        String nombre = in.nextLine();
        jugador.setId(nombre);
        
    }
    

    public void tirada() {

        boolean sigue = true;
        System.out.println("\nTurno del Jugador " + jugador.getId());
        while (sigue) {
            System.out.println("A donde quieres enviar tus torpedos?");
            jugador.tabEnemigo.mostrarTablero();
            int columna = posicionColumna(jugador.Enemic.tablero.tamanyo);
            int fila = posicionFila(jugador.Enemic.tablero.tamanyo);
            in.nextLine();
            System.out.println("Jugador " + jugador.getId()
                    + " lanza sus torpedos a " + Tablero.ColumnaALetra(columna)
                    + "" + fila + " y...");
            if (comprobarHit(fila, columna)) {

                System.out.println("Acierta ");
                jugador.tabEnemigo.tamanyo[fila][columna] = 5;

                for (Barco barquito : jugador.Enemic.listaBarcos) {
                    if (barquito.cordenadas.contains(Tablero.ColumnaALetra(columna) + Integer.toString(fila))) {
                        barquito.cordenadas.remove(barquito.cordenadas.
                                indexOf(Tablero.ColumnaALetra(columna) + Integer.toString(fila)));

                    }
                    if (barquito.cordenadas.isEmpty()) {
                        System.out.println(" Hundiendo un " + barquito.getTipo() + "!");
                    }
                }
            } else {
                sigue = false;
                System.out.println("Agua!");
                jugador.tabEnemigo.tamanyo[fila][columna] = 4;
            }
        }
    }

    public boolean comprobarHit(int Fini, int Cini) {
        return jugador.Enemic.tablero.tamanyo[Fini][Cini] == 1;
    }

    //Todo el heavy lifting, metodo que llama al resto de las clases.
    public void iniciarBarco() {

        System.out.println(jugador.getId() + " Añade los barcos:");

        for (Barco barquito : jugador.listaBarcos) {
            int columnaIni, filaIni, filaFin = 0, columnaFin = 0;
            String posInicial = "", posFinal = "";
            boolean correcto = false;

            jugador.tablero.mostrarTablero();
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
                    do {
                        System.out.println("Posición incial:");
                        columnaIni = posicionColumna(jugador.tablero.tamanyo);
                        filaIni = posicionFila(jugador.tablero.tamanyo);

                        in.nextLine();

                        posInicial = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaIni);
                        System.out.println("Posición final:");
                        filaFin = posicionFila(jugador.tablero.tamanyo);
                        in.nextLine();

                        posFinal = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaFin);

                        correcto = devolverDimension(barquito, posInicial, posFinal, orientacion);
                        if (correcto) {
                            correcto = posValida(orientacion, columnaIni, columnaFin, filaIni, filaFin);
                        }
                    } while (!correcto);
                    System.out.println("Es correcto");
                    AnadirbarcoTablero(orientacion, posInicial, posFinal);
                    bloqueaAdyacentes("V", columnaIni, columnaFin, filaIni, filaFin);
                    barquito.setCordenadas(posInicial, posFinal);
                    System.out.println("\n//////////////////////\n"
                            + barquito.getTipo() + " Creado con cordenadas "
                            + barquito.getCordenadas());
                    break;

                case "H":
                    do {
                        jugador.tablero.mostrarTablero();
                        System.out.println("Posición incial:");
                        columnaIni = posicionColumna(jugador.tablero.tamanyo);
                        filaIni = posicionFila(jugador.tablero.tamanyo);
                        in.nextLine();
                        posInicial = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaIni);

                        System.out.println("Posición final:");
                        columnaFin = posicionColumna(jugador.tablero.tamanyo);

                        posFinal = Tablero.ColumnaALetra(columnaFin) + String.valueOf(filaIni);

                        correcto = devolverDimension(barquito, posInicial, posFinal, orientacion);
                        if (correcto) {
                            correcto = posValida(orientacion, columnaIni, columnaFin, filaIni, filaFin);
                        }
                    } while (!correcto);

                    System.out.println("Es correcto");
                    AnadirbarcoTablero(orientacion, posInicial, posFinal);
                    bloqueaAdyacentes("H", columnaIni, columnaFin, filaIni, filaFin);
                    barquito.setCordenadas(posInicial, posFinal);
                    System.out.println("\n//////////////////////\n"
                            + barquito.getTipo() + " Creado con cordenadas "
                            + barquito.getCordenadas());
                    break;
            }
            System.out.println("Barco: " + posInicial + "-" + posFinal);
            System.out.println("------------------------");

        }
        System.out.println("TABLERO FINAL \n");
        jugador.tablero.mostrarTablero();
        System.out.println("\n\n");
    }

    //compruba si donde se va a insertar ya existe un barco o si es una casilla adyacente.
    public boolean posValida(String orientacion, int colInicial, int colFinal, int filInicial, int filFinal) {
        boolean correcto = true;

        if (orientacion.equals("H")) {
            for (int i = colInicial; i <= colFinal; i++) {
                if (jugador.tablero.tamanyo[filInicial][i] != 0) {
                    System.out.println("La posición del barco es invalida."
                            + " Se encuentra dentro de los limites de otro barco.");
                    correcto = false;
                }
            }
        } else {
            for (int i = filInicial; i <= filFinal; i++) {
                if (jugador.tablero.tamanyo[i][colInicial] != 0) {
                    System.out.println("La posición del barco es invalida."
                            + " Se encuentra dentro de los limites de otro barco.");
                    correcto = false;
                }
            }
        }
        return correcto;
    }

    //Devuelve la dimension del barco que se ha añadido, para hacer comprobaciones.
    //Este metodo debería debería ser intercambiado por funciones de la clase Barco.
    public boolean devolverDimension(Barco barquito, String posInicial, String posFinal, String orientacion) {
        String ColumnaIncial = posInicial.substring(0, 1);
        String FilaIncial = posInicial.substring(posInicial.indexOf(ColumnaIncial) + 1);

        String ColumnaFinal = posFinal.substring(0, 1);
        String FilaFinal = posFinal.substring(posFinal.indexOf(ColumnaFinal) + 1);

        int ColumnaIni = Tablero.ColumnaAInteger(ColumnaIncial);
        int FilaIni = Integer.parseInt(FilaIncial);

        int ColumnaFin = Tablero.ColumnaAInteger(ColumnaFinal);

        int FilaFin = Integer.parseInt(FilaFinal);
        System.out.println("Fila");

        if (orientacion.equals("H")) {
            System.out.println("Tamaño del barco Dado = " + ((ColumnaFin - ColumnaIni) + 1));
            return (ColumnaFin - ColumnaIni) + 1 == barquito.getTamanyo();
        } else {
            System.out.println("Tamaño del barco Dado = " + ((FilaFin - FilaIni) + 1));
            return (FilaFin - FilaIni) + 1 == barquito.getTamanyo();
        }

    }

    public void AnadirbarcoTablero(String orientacion, String posInicial, String posFinal) {
        String ColumnaIncial = posInicial.substring(0, 1);
        String FilaIncial = posInicial.substring(posInicial.indexOf(ColumnaIncial) + 1);

        String ColumnaFinal = posFinal.substring(0, 1);
        String FilaFinal = posFinal.substring(posFinal.indexOf(ColumnaFinal) + 1);

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
        } while (cambiado < 0 || cambiado > tablero.length);

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
                System.out.println("Eso no es un Integer, amigo");
                fila = -1;
                in.nextLine();
            }
        } while (fila < 0 || fila >= tablero.length);
        return fila;
    }

    //Este método es demasiado largo.
    //Hay que comprobar muchas cosas.
    //Falla si se ponen los barcos al revés.
    //Da un problema especifico si pones un barco vertical con posicion inicial en A0.
    public void bloqueaAdyacentes(String orientacion, int ColumnaIni, int ColumnaFin, int FilaIni, int FilaFin) {
        if (orientacion.equals("V")) {
            if (ColumnaIni - 1 >= 0) {
                for (int x = FilaIni; x <= FilaFin; x++) {
                    jugador.tablero.tamanyo[x][ColumnaIni - 1] = 2;
                }
            }
            if (ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                for (int x = FilaIni; x <= FilaFin; x++) {
                    jugador.tablero.tamanyo[x][ColumnaIni + 1] = 2;
                }
            }
            if (FilaIni - 1 >= 0 && FilaFin - 1 >= 0) {
                if (FilaIni <= FilaFin) {
                    jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni] = 2;
                    if (ColumnaIni - 1 >= 0) {
                        jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni - 1] = 2;
                    }
                    if (ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                        jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni + 1] = 2;
                    }
                } else {
                    jugador.tablero.tamanyo[FilaFin - 1][ColumnaFin] = 2;
                }
            }
            if (FilaIni + 1 < jugador.tablero.tamanyo.length && FilaFin + 1 < jugador.tablero.tamanyo.length) {
                jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni] = 2;

                if (ColumnaIni - 1 >= 0) {
                    jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni - 1] = 2;
                }
                if (ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                    jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni + 1] = 2;
                }
            }
        } else {
            if (ColumnaIni - 1 >= 0 && ColumnaFin - 1 >= 0) {
                if (ColumnaIni <= ColumnaFin) {
                    jugador.tablero.tamanyo[FilaIni][ColumnaIni - 1] = 2;
                } else {
                    jugador.tablero.tamanyo[FilaFin][ColumnaFin - 1] = 2;
                }
                if (FilaIni - 1 >= 0) {
                    jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni - 1] = 2;
                }
                if (FilaIni + 1 < jugador.tablero.tamanyo.length) {
                    jugador.tablero.tamanyo[FilaIni + 1][ColumnaIni - 1] = 2;
                }
            }
            if (ColumnaFin + 1 < jugador.tablero.tamanyo.length && ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                jugador.tablero.tamanyo[FilaIni][ColumnaFin + 1] = 2;

                if (FilaIni - 1 >= 0) {
                    jugador.tablero.tamanyo[FilaIni - 1][ColumnaFin + 1] = 2;
                }
                if (FilaIni + 1 < jugador.tablero.tamanyo.length) {
                    jugador.tablero.tamanyo[FilaIni + 1][ColumnaFin + 1] = 2;
                }
            }
        }
        if (FilaIni - 1 >= 0) {
            for (int x = ColumnaIni; x <= ColumnaFin; x++) {
                jugador.tablero.tamanyo[FilaIni - 1][x] = 2;
            }
        }

        if (FilaIni + 1 < jugador.tablero.tamanyo.length) {
            for (int x = ColumnaIni; x <= ColumnaFin; x++) {
                jugador.tablero.tamanyo[FilaIni + 1][x] = 2;
            }
        }
    }
}
