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
public class ConsolaV3 {

    public Jugador jugador;

    public static Scanner in = new Scanner(System.in);

    public ConsolaV3(Jugador jugador) {
        this.jugador = jugador;
    }
    //Hacemos el juego de tamaño estatico
    //x1 Acorazado - 4 casillas
    //x2 Cruceros - 3 casillas
    //x3 Submarinos - 2 casillas
    //x4 Desctructores - 1 casilla

    public void iniciarBarco() {
        System.out.println("Añade los barcos:");

        for (Barco barquito : jugador.listaBarcos) {
            //Un while para que ejecute esto mientras el tamaño del barco no sea igual al especificado
            //Also, un metodo para mirar eso.
            int columnaIni, filaIni, filaFin = 0, columnaFin = 0;
            String posInicial = "", posFinal = "";
            boolean correcto = false;

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
                        jugador.tablero.mostrarTablero();
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

                        correcto = devolverDimension(barquito, posInicial, posFinal, orientacion);
                        if (correcto) {

                            if (posValida(orientacion, columnaIni, columnaFin, filaIni, filaFin)) {
                                System.out.println("Es correcto");
                                AnadirbarcoTablero(orientacion, posInicial, posFinal);
                                bloqueaAdyacentes("V", columnaIni, columnaFin, filaIni, filaFin);

                            } else {
                                System.out.println("Es incorrecto");
                            }
                        } else {
                            System.out.println("Dimensión del barco erronea - "
                                    + "Dimensión Esperada: " + barquito.getTamanyo());
                        }
                    } while (!correcto);

                    break;

                case "H":
                    do {
                        jugador.tablero.mostrarTablero();
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

                        correcto = devolverDimension(barquito, posInicial, posFinal, orientacion);
                        if (correcto) {

                            if (posValida(orientacion, columnaIni, columnaFin, filaIni, filaFin)) {
                                System.out.println("Es correcto");
                                AnadirbarcoTablero(orientacion, posInicial, posFinal);
                                bloqueaAdyacentes("H", columnaIni, columnaFin, filaIni, filaFin);
                            } else {
                                System.out.println("La posición del barco es invalida."
                                        + " Se encuentra dentro de los limites de otro barco.");
                            }
                        } else {
                            System.out.println("Dimensión del barco erronea - "
                                    + "Dimensión Esperada: " + barquito.getTamanyo());
                        }
                    } while (!correcto);
                    break;
            }

            jugador.tablero.mostrarTablero();
            System.out.println("Barco: " + posInicial + "-" + posFinal);
            System.out.println("------------------------");
        }
    }

//No funciona bien
    public boolean posValida(String orientacion, int colInicial, int colFinal, int filInicial, int filFinal) {
        //comprobar que las posiciones no sean 0-0 ni lenght antes del for.(que no se salgan del tablero)
        boolean correcto = true;

        if (orientacion.equals("H")) {
            for (int i = colInicial; i < colFinal; i++) {
                if (jugador.tablero.tamanyo[filInicial][i] != 0) {
                    System.out.println("Posición invalida!");
                    correcto = false;
                }
            }
        } else {
            for (int i = filInicial; i < filFinal; i++) {
                if (jugador.tablero.tamanyo[i][colInicial] != 0) {
                    System.out.println("Posición invalida!");
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

    //Este método es demasiado largo.
    //Hay que comprobar muchas cosas.
    //Falla si se ponen los barcos al revés.
    public void bloqueaAdyacentes(String orientacion, int ColumnaIni, int ColumnaFin, int FilaIni, int FilaFin) {

        if (orientacion.equals("V")) {
            //orientacion vertical
            //Comprueba que no se salga por el lado izquierdo - tmb sirve un if
            if (ColumnaIni - 1 >= 0) {
                for (int x = FilaIni; x <= FilaFin; x++) {
                    jugador.tablero.tamanyo[x][ColumnaIni - 1] = 2;
                }
            }
            //Comprueba que no se salga por el lado derecho
            if (ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                for (int x = FilaIni; x <= FilaFin; x++) {
                    jugador.tablero.tamanyo[x][ColumnaIni + 1] = 2;
                }
            }
            //Comprueba la casilla justo arriba
            if (FilaIni - 1 >= 0 && FilaFin - 1 >= 0) {
                //Puedes poner los barcos hacia arriba o hacia abajo
                if (FilaIni <= FilaFin) {
                    jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni] = 2;
                    //Arriba a la izqierda
                    if (ColumnaIni - 1 >= 0) {
                        jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni - 1] = 2;
                    }
                    //Arriba a la derecha
                    if (ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                        jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni + 1] = 2;
                    }
                } else {
                    jugador.tablero.tamanyo[FilaFin - 1][ColumnaFin] = 2;
                }
            }
            //Comprueba la casilla justo abajo
            if (FilaIni + 1 >= 0 && FilaFin + 1 >= 0) {
                //Puedes poner los barcos hacia arriba o hacia abajo
                if (FilaIni <= FilaFin) {
                    jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni] = 2;
                    //Abajo a la izqierda
                    if (ColumnaIni - 1 >= 0) {
                        jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni - 1] = 2;
                    }
                    //Abajo a la derecha
                    if (ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                        jugador.tablero.tamanyo[FilaFin + 1][ColumnaIni + 1] = 2;
                    }
                } else {
                    jugador.tablero.tamanyo[FilaFin + 1][ColumnaFin] = 2;
                }

            }
        } else {
            //orientacion horizontal
            //Comprueba que no se salga por el lado izquierdo - tmb sirve un if
            //En horizontal la fila es la misma todo el rato.
            if (ColumnaIni - 1 >= 0 && ColumnaFin - 1 >= 0) {
                if (ColumnaIni <= ColumnaFin) {
                    //la incial es menor o igual.-.
                    jugador.tablero.tamanyo[FilaIni][ColumnaIni - 1] = 2;
                } else {
                    //ColumnaFin es menor 
                    jugador.tablero.tamanyo[FilaFin][ColumnaFin - 1] = 2;
                }
                //Compreuab arriba a la derecha
                if (FilaIni - 1 >= 0) {
                    jugador.tablero.tamanyo[FilaIni - 1][ColumnaIni - 1] = 2;
                }
                //comprueba abajo a la derecha
                if (FilaIni + 1 < jugador.tablero.tamanyo.length) {
                    jugador.tablero.tamanyo[FilaIni + 1][ColumnaIni - 1] = 2;
                }

            }
            //Comprueba que no se salga por el lado derecho
            if (ColumnaFin + 1 < jugador.tablero.tamanyo.length && ColumnaIni + 1 < jugador.tablero.tamanyo.length) {
                //FilaFin es menor 
                /////////////
                ///FALLA ESTO
                jugador.tablero.tamanyo[FilaIni][ColumnaFin + 1] = 2;
                /////////////
                /////////////
                //MIRA CON DEBUGGER EL VALOR DE COLUMFINAL
                if (FilaIni - 1 >= 0) {
                    jugador.tablero.tamanyo[FilaIni - 1][ColumnaFin + 1] = 2;
                }
                //comprueba abajo a la derecha
                if (FilaIni + 1 < jugador.tablero.tamanyo.length) {
                    jugador.tablero.tamanyo[FilaIni + 1][ColumnaFin + 1] = 2;
                }
            }
            //Compreuab arriba a la derecha

        }
        //
        //
        //
        //comprueba que no se salga por arriba
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
