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

    private String tipo;
    private int tamanyo;
    private int vidas;
    private String[] cordenadas;

    public Barco(String tipo, int tamanyo) {
        this.tipo = tipo;
        this.tamanyo = tamanyo;
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

    public String getTipo() {
        return tipo;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public int getVidas() {
        return vidas;
    }

    public String[] getCordenadas() {
        return cordenadas;
    }

}
