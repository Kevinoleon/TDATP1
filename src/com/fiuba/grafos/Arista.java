package com.fiuba.grafos;

/**
 * Created by gatti2602 on 09/04/17.
 * Arista Dirigida.
 * Contiene campos src y dst inmutables al crear la arista.
 */
public class Arista {

    private Integer src, dst;

    Arista(Integer src, Integer dst) {
        this.src = src;
        this.dst = dst;
    }

    public Integer getSrc() {
        return src;
    }

    public Integer getDst() {
        return dst;
    }


}
