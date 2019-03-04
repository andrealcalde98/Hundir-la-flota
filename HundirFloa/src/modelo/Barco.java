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
