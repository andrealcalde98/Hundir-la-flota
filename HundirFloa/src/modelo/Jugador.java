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
    public  Barco[] listaBarcos;
    public Tablero tablero;
    public Tablero enemigo;
    public Map<String, Boolean> tiradas;
    public int vidas;
    
    public Jugador(String id, Barco[] listaBarcos, Tablero tablero, Tablero enemigo){
        this.id = id;
        this.listaBarcos = listaBarcos;
        this.tablero = tablero;
        this.enemigo = enemigo;        
    }
    
    
    
    
    
}
