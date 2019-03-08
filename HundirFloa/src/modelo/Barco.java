/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Andre Alcalde, Lorenzo Scardino i Raúl Barrero
 */
public class Barco {

    private String tipo;
    private int tamanyo;
    public  ArrayList<String> cordenadas;

    public Barco(String tipo, int tamanyo) {
        this.tipo = tipo;
        this.tamanyo = tamanyo;
        this.cordenadas = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public ArrayList getCordenadas() {
        return cordenadas;
    }

    public void setCordenadas(String... cordenadas) {
        this.cordenadas.addAll(Arrays.asList(cordenadas));
    }
    

}
