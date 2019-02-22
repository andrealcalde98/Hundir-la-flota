package controlador;

import java.util.Scanner;
import modelo.Tablero;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class consola {

    static Scanner in = new Scanner(System.in);

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
