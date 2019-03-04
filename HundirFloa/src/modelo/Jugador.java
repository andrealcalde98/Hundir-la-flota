/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Map;

/**
 *
 * @author Lorenzo
 */
public class Jugador {

    public String id;
    public Barco[] listaBarcos;
    public Tablero tablero;
    public Tablero enemigo;
    public Map<String, Boolean> tiradas;
    public int vidas;

    public Jugador(String id, Tablero tablero, Tablero enemigo) {
        this.id = id;
        this.listaBarcos = listaBarcos;
        this.tablero = tablero;
        this.enemigo = enemigo;
        this.listaBarcos = IniciarBarcos();
    }

    public Barco[] IniciarBarcos() {
        Barco[] Barcos = new Barco[]{
            new Barco("Acorazado", 4),
            new Barco("Crucero 1", 3),
            new Barco("Crucero 2", 3),
            new Barco("Submarino 1", 2),
            new Barco("Submarino 2", 2),
            new Barco("Submarino 3", 2),
            new Barco("Destructor 1", 1),
            new Barco("Destructor 2", 1),
            new Barco("Destructor 3", 1),
            new Barco("Destructor 4", 1),};
        return Barcos;
    }

}
