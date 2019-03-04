package controlador;

import java.util.Scanner;
import modelo.Barco;
import modelo.Tablero;

/**
 *
 * @author Andre Alcalde, Raúl Barrero y Lorenzo Scardino.
 */
public class consola {

    static Scanner in = new Scanner(System.in);
    static boolean sigue = true;

    //Por poder, puedes cambiar el tamaño del tablero
    //y el número de barcos, pero creemos que la mejor experiencia consiste en: 
    // R- Eso es lo que piensas tu aun que estoy de acuerdo con el tablero
    // R- creo que es mejor pedir cuanto barcos quieres
    //un tablero de 12x12
    //x1 Acorazado - 4 casillas
    //x2 Cruceros - 3 casillas
    //x3 Submarinos - 2 casillas
    //x4 Desctructores - 1 casilla
    //Recordad que a la mayoria de variables hay que sumarles +1.
    public static void llenarTablero(int[][] tablero, int n_barcos) {

        //Por pura estetica, lo lento que sea el progama da igual , so..
        //Quizas una lista con los tipos de barcos
        String posInicial = "", posFinal = "";
        int columnaIni, filaIni, columnafin, filafin;
        for (int i = 0; i < n_barcos; i++) {
            System.out.println("Orientación del barco (H/V):");
            String orientacion = in.nextLine().toUpperCase();

            while (!orientacion.equals("H") && !orientacion.equals("V")) {
                System.out.println("Valor incorrecto, vuelve a intentarlo.");
                System.out.println("Orientación del barco (H/V):");
                orientacion = in.nextLine().toUpperCase();
            }

            switch (orientacion) {
                case "H":
                    System.out.println("Posición incial:");
                    columnaIni = posicionColumna(tablero);
                    filaIni = posicionFila(tablero);
                    //Aquí hay que hacer comprobaciones.
                    //si tiene barcos adyacientes.
                    in.nextLine();
                    posInicial = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaIni);

                    System.out.println("Posición final:");
                    //Aquí hay que hacer comprobaciones.
                    //De tamaño y si tiene barcos adyacientes.
                    columnafin = posicionColumna(tablero);
                    posFinal = Tablero.ColumnaALetra(columnafin) + String.valueOf(filaIni);
                    break;

                case "V":
                    System.out.println("Posición incial:");
                    columnaIni = posicionColumna(tablero);
                    filaIni = posicionFila(tablero);
                    //Aquí hay que hacer comprobaciones.
                    //si tiene barcos adyacientes.
                    in.nextLine();
                    posInicial = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filaIni);

                    System.out.println("Posición final:");
                    //Aquí hay que hacer comprobaciones.
                    //De tamaño y si tiene barcos adyacientes.
                    filafin = posicionFila(tablero);
                    in.nextLine();
                    posFinal = Tablero.ColumnaALetra(columnaIni) + String.valueOf(filafin);
                    break;
            }

            System.out.println("Barco " + (i + 1) + ": " + posInicial + "-" + posFinal);
            System.out.println("------------------------");
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
        } while (cambiado < 0 || cambiado >= tablero.length);

        return cambiado;
    }

    public static int posicionFila(int[][] tablero) {
        int fila = -1;
        do {
            System.out.print("Fila [");
            for (int j = 0; j < tablero.length; j++) {
                if (j == tablero.length - 1) {
                    System.out.print(j + 1 + "] :");
                } else {
                    System.out.print(j + 1 + ", ");
                }
            }
            try {
                fila = in.nextInt();
            } catch (Exception e) {
                System.out.println("Eso no es un numero, broder");
                fila = -1;
                in.nextLine();
            }
        } while (fila <= 0 || fila > tablero.length);

        return fila;
    }

    //Metodo para saber si los limites del barco están al borde del tablero.
    //Si está al inicio(Ya sea vertical o horizontal) devuelve 1.
    //Si está al final, devuelve 2.
    //Cualquier otro caso, devuelve un 0.
    //(En un principio, es imposible que esté en ambos bordes a la vez)
    //Aunque se debería añadir una comprobación por si está a la vez en el borde
    //inicial/Final tanto horizontalmente como verticalemnte - en plan A1-A3
    //Este método es para que no pete a la hora de recorrer el tablero - cuando
    //comprueba que hay barcos en blqoeus adyacentes, ahorra iteraciones.
    public static int Borde(int[][] tablero, String orientacion, String posInicial, String posFinal) {
        //Partimos los strings para poder usarlos como int. Nada nuevo.
        //Aunque esto no debería repetirse tanto.
        //Asumimos que todas las variables on numeros
        String FilaIncial = posInicial.substring(0, 1);
        String ColumnaIncial = posInicial.substring(posInicial.indexOf(FilaIncial));

        String FilaFinal = posFinal.substring(0, 1);
        String ColumnaFinal = posFinal.substring(posFinal.indexOf(FilaFinal));

        // variables para tratar
        int FilaIni = columnaAInteger(FilaIncial);
        int ColumnaIni = Integer.parseInt(ColumnaIncial);

        int FilaFin = columnaAInteger(FilaFinal);
        int ColumnaFin = Integer.parseInt(ColumnaFinal);

        //Int de salida
        int borde = 0;

        if (ColumnaIni == 0 || ColumnaFin == 0) {
            borde = 1;
        } else if (ColumnaIni == tablero.length || ColumnaFin == tablero.length) {
            borde = 2;
        }

        return 0;
    }

    //Habría que dar opciones al jugador a la hora de poner el barco, es decir,
    //si tiene un tamaño de 3 casillas, darle sus opcines automaticamente.
    //esto lo complicaria mucho tbh.
    public static void bloqueaAdyacentes(String posInicial, String posFinal, int[][] tablero) {

        String FilaIncial = posInicial.substring(0, 1);
        String ColumnaIncial = posInicial.substring(posInicial.indexOf(FilaIncial));

        String FilaFinal = posFinal.substring(0, 1);
        String ColumnaFinal = posFinal.substring(posFinal.indexOf(FilaFinal));
        // variables para tratar
        int FilaIni = Integer.parseInt(FilaIncial);
        int ColumnaIni = Integer.parseInt(ColumnaIncial);

        int FilaFin = Integer.parseInt(FilaFinal);
        int ColumnaFin = Integer.parseInt(ColumnaFinal);

        do {
            System.out.println("No podras poner barcos en espacios adyacentes");

            for (int j = 0; j < tablero.length; j++) {//columnas
                for (int x = 0; x < tablero[j].length; x++) {//filas
                    //posicion incial del array
                    if (x == FilaIni - 1) {
                        tablero[x][ColumnaIni] = 1;//valor fila inferior
                        // si es vertical bloquearemos los bordes
                        // no se solaparan si es horizontal ya que tambien deben estar bloqueadas
                        tablero[x][ColumnaIni - 1] = 1;
                        tablero[x][ColumnaIni + 1] = 1;

                    } else if (x == FilaIni + 1) { // 
                        tablero[x][ColumnaIni] = 1;//valor fila superior
                        //si es vertical bloquearemos los dos adyacentes del valor medio
                        tablero[x][ColumnaIni - 1] = 1;
                        tablero[x][ColumnaIni + 1] = 1;

                    } else if (j == ColumnaIni - 1) {
                        tablero[FilaIni][j] = 1;//valor columna inferior
                        // si es horizontal bloquearemos los bordes
                        // no se solaparan si es vertical ya que tambien deben estar bloqueadas
                        tablero[FilaIni - 1][j] = 1;
                        tablero[FilaIni + 1][j] = 1;

                    } else if (j == ColumnaIni + 1) {
                        tablero[FilaIni][j] = 1;//valor columna superior
                        //si es horizontal bloquearemos los dos adyacentes del valor medio
                        tablero[FilaIni - 1][j] = 1;
                        tablero[FilaIni + 1][j] = 1;

                        //posicion final array
                    } else if (x == FilaFin - 1) {
                        tablero[x][ColumnaFin] = 1;//valor fila inferior
                        // si es vertical bloquearemos los bordes(si es de cuatro) Si es de menos no se solapa
                        // no se solaparan si es horizontal ya que tambien deben estar bloqueadas
                        tablero[x][ColumnaFin - 1] = 1;
                        tablero[x][ColumnaFin + 1] = 1;

                    } else if (x == FilaFin + 1) {
                        tablero[x][ColumnaIni] = 1;//valor fila superior
                        // si es vertical bloquearemos los bordes
                        // no se solaparan si es horizontal ya que tambien deben estar bloqueadas
                        tablero[x][ColumnaFin - 1] = 1;
                        tablero[x][ColumnaFin + 1] = 1;

                    } else if (j == ColumnaFin - 1) {
                        tablero[FilaFin][j] = 1;//valor columna inferior
                        // si es horizontal bloquearemos los bordes
                        // no se solaparan si es vertical ya que tambien deben estar bloqueadas
                        tablero[FilaFin - 1][j] = 1;
                        tablero[FilaFin + 1][j] = 1;

                    } else if (j == ColumnaFin + 1) {
                        tablero[FilaFin][j] = 1;//valor columna superior
                        // si es horizontal bloquearemos los bordes
                        // no se solaparan si es vertical ya que tambien deben estar bloqueadas
                        tablero[FilaIni - 1][j] = 1;
                        tablero[FilaIni + 1][j] = 1;
                    }

                }
            }
            sigue = false;
        } while (sigue);
    }

    public static String comprobarTamano(String posInicial, String posFinal, boolean horiz, int[][] tablero) {

        String FIncial = posInicial.substring(0, 1);
        String CIncial = posInicial.substring(posInicial.indexOf(FIncial));

        String FFinal = posFinal.substring(0, 1);
        String CFinal = posFinal.substring(posFinal.indexOf(FFinal));

        int Fini = Integer.parseInt(FIncial);
        int Cini = Integer.parseInt(CIncial);

        int Ffin = Integer.parseInt(FFinal);
        int Cfin = Integer.parseInt(CFinal);

        if (horiz = true) {

            for (int i = Fini; i < Ffin; i++) {
                int pos = tablero[Cini][Fini + i];
                if (pos != 0) {
                    return "Agua";
                } else {
                    return "Acierto";
                }
            }
        } else {
            for (int i = Cini; i < Cfin; i++) {
                int pos = tablero[Fini][Cini + i];
                if (pos != 0) {
                    return "Agua";
                } else {
                    return "Acierto";
                }
            }
        }
        return "Error";
    }

    public static char columnaALetra(int a) {
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        return letras.charAt(a);
    }

    //Letra de la columna a Numero, esto sí que afecta al codigo.
    public static int columnaAInteger(String a) {
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        return letras.indexOf(a);

    }
}
