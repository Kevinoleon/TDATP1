package com.fiuba.grafos;

import java.util.Iterator;

/**
 * Created by marianovazquez on 4/19/17.
 */
public class Grafo {

    private Digrafo digrafo;

    public Grafo(int vertices) {
        this.digrafo = new Digrafo(vertices);
    }

    /**
     * Devuelve la cantidad de Vertices del Grafo
     */
    public Integer getCantidadDeVertices() {

        return this.digrafo.cuentaDeVertices();
    }

    /**
     * Devuelve la cantidad de Aristas del Grafoo
     */
    public Integer getCantidadDeAristas() {
        
        return this.digrafo.cuentaDeAristas() / 2;
    }

    /**
     * Devuelve un iterador sobre la lista de adyacencia del vertice indicado.
     * Devuelve null si el vertice no existe
     */
    public Iterator<Arista> getAdyacentes(int vertice) {

        return this.digrafo.getAdjList(vertice);
    }

    /**
     * Agrega una Arista si el src esta dentro de los vertices del grafo y la arista no existe
     */
    public void agregarArista(int src, int dest) {

        this.digrafo.agregarArista(src, dest);
        this.digrafo.agregarArista(dest, src);
    }
}
