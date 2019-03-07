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

    private String id;
    public Barco[] listaBarcos;
    public Tablero tablero;
    public Tablero tabEnemigo;
    public Jugador Enemic;
    public Map<String, Boolean> tiradas;
    public int vidas;

    public Jugador(String id, Tablero tablero, Tablero enemigo) {
        this.id = id;
        this.tablero = tablero;
        this.tabEnemigo = enemigo;
        this.listaBarcos = IniciarBarcos();
        this.vidas = iniciarVidas();
    }

    public final Barco[] IniciarBarcos() {
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
    
    public final int iniciarVidas(){
        int vida = 0;
        for (Barco barco : listaBarcos) {
            vida+= barco.getVidas();
        }
        return vida;
    }

    public Jugador getEnemic() {
        return Enemic;
    }

    public void setEnemic(Jugador Enemic) {
        this.Enemic = Enemic;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

}
