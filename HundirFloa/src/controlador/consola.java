package controlador;

import java.util.Scanner;
import modelo.Tablero;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class consola {

    static Scanner in = new Scanner(System.in);
    static boolean sigue = true;

    //Recordar que a la mayoria de variables hay que sumarles +1.
    public static void llenarTablero(int[][] tablero, int n_barcos) {

        String posInicial = "", posFinal;

        //Pedir el tamaño del barco y la orientación.
        for (int i = 0; i < n_barcos; i++) {

            System.out.println("Tamaño del barco:");

            for (int z = 0; z < 2; z++) {

                if (z == 0) {
                    System.out.print("Posición Inicial");
                } else {
                    System.out.print("Posición Final");
                    //Si es posición final no debería pedir la columna/Fila
                    //Dependiendo la orientación.
                }

                System.out.println(" del barco " + (i + 1) + ":");
                System.out.println("------------------------");

                int cambiado = -1;
                //Comprueba si está dentro del tablero.
                do {
                    //Introduce columna
                    System.out.print("Columna [");
                    for (int j = 0; j < tablero.length; j++) {
                        if (j == tablero.length - 1) {
                            System.out.print(Tablero.columnaALetra(j) + "] :");
                        } else {
                            System.out.print(Tablero.columnaALetra(j) + ", ");
                        }
                    }
                    String aCambiar = in.nextLine().toUpperCase();
                    cambiado = Tablero.columnaAInteger(aCambiar);
                } while (cambiado < 0 || cambiado >= tablero.length);

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
                System.out.println("------------------------");
                if (z == 0) {
                    posInicial = Tablero.columnaALetra(cambiado) + String.valueOf(fila);
                } else {
                    posFinal = Tablero.columnaALetra(cambiado) + String.valueOf(fila);
                    System.out.println("------------------------\n"
                            + "Posición final: " + posInicial + "-" + posFinal
                            + "\n------------------------\n");
                }
                in.nextLine();

            }
        }
    }
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
                        tablero[FilaFin+ 1][j] = 1;

                    } else if (j == ColumnaFin + 1) {
                        tablero[FilaFin][j] = 1;//valor columna superior
                        // si es horizontal bloquearemos los bordes
                        // no se solaparan si es vertical ya que tambien deben estar bloqueadas
                        tablero[FilaIni - 1][j] = 1;
                        tablero[FilaIni + 1][j] = 1;
                    }

                }
            } sigue = false;
        } while (sigue);
    }

    public static String comprobarTamano(String posInicial, String posFinal, boolean horiz,int[][] tablero) {

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
                int pos = tablero[Cini][Fini+i];
                if(pos !=0){
                    return "Agua";
                }else{
                    return "Acierto";
                }
            }
        } else {
            for (int i = Cini; i < Cfin; i++) {
                int pos = tablero[Fini][Cini+i];
                if(pos !=0){
                   return "Agua";
                }else{
                   return "Acierto";
                }
            }
        }
        return "Error";
    }

    public static String setColumna(int tamMax) {
        int cambiado = -1;
        String aCambiar;
        do {
            //Introduce columna
            System.out.print("Columna [");
            for (int j = 0; j < tamMax; j++) {
                if (j == tamMax - 1) {
                    System.out.print(Tablero.columnaALetra(j) + "] :");
                } else {
                    System.out.print(Tablero.columnaALetra(j) + ", ");
                }
            }
            aCambiar = in.nextLine().toUpperCase();
            cambiado = Tablero.columnaAInteger(aCambiar);
        } while (cambiado < 0 || cambiado >= tamMax);
        System.out.println("A cambiarrrrr: " + aCambiar);
        return aCambiar;
    }

}
