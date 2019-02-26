/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Lorenzo
 */
public class Barco {
    String tipo;
    int tamanyo;
    int vidas;
    String[] cordenadas;
    
    
    public Barco(String tipo, String ... cordenada){
        this.tipo = tipo;
        this.cordenadas = cordenada;
    }
}
