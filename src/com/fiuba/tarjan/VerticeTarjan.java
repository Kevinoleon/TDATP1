package com.fiuba.tarjan;

/**
 * Created by marianovazquez on 4/20/17.
 */
/**
 * Created by marianovazquez on 4/20/17.
 */
public class VerticeTarjan {
    public int id;
    public int padreId;
    public int numeroVisita;
    public int numeroBajo;

    public VerticeTarjan(int id, int padreId, int numeroVisita, int numeroBajo) {
        this.id = id;
        this.padreId = padreId;
        this.numeroVisita = numeroVisita;
        this.numeroBajo = numeroBajo;
    }
}
