package com.fiuba.grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by gatti2602 on 09/04/17.
 * Implementa la clase Digrafo inmutable.
 * Representa los vertices como una entrada en la lista de adyacencia
 * Cada vertice se representa por una lista de Aristas. Los vertices empiezan en 0
 * El digrafo es no pesado.
 */
public class Digrafo {

    /**
     * Cada elemento contiene la lista de arsitas del vertice
     * referenciado
     */
    private ArrayList<HashMap<Integer, Arista>> adjList;
    private int cuentaAristas = 0;
    private int cuentaVertices;

    public Digrafo(int vertices) {
        this.adjList = new ArrayList<>(vertices);
        this.cuentaVertices = vertices;

        for (int i = 0; i < vertices; i++) {
            this.adjList.add(new HashMap<>());
        }
    }

    /**
     * Devuelve la cantidad de Vertices del Grafo
     */
    public Integer cuentaDeVertices() {
        return cuentaVertices;
    }

    /**
     * Devuelve la cantidad de Aristas del Grafoo
     */
    public Integer cuentaDeAristas() {
        return cuentaAristas;
    }

    /**
     * Devuelve un iterador sobre la lista de adyacencia del vertice indicado.
     * Devuelve null si el vertice no existe
     */
    public Iterator<Arista> getAdjList(int vertice) {
        if (vertice >= adjList.size())
            return null;

        return adjList.get(vertice).values().iterator();

    }

    /**
     * Agrega una Arista si el src esta dentro de los vertices del grafo y la arista no existe
     */
    public void agregarArista(int src, int dst) {

        if (src >= cuentaVertices || dst >= cuentaVertices)
            return;

        Arista previa = adjList.get(src).put(dst, new Arista(src, dst));
        if (previa == null)
            cuentaAristas++;
    }

    public Boolean existeArista(int src, int dst) {
        Boolean existe = false;
        if (src < cuentaVertices) {
            existe = this.adjList.get(src).containsKey(dst);
        }
        return existe;
    }

    public Digrafo transponer() {
        Digrafo d = new Digrafo(this.cuentaDeVertices());

        for (int src = 0; src < this.cuentaDeVertices(); src++) {
            for (int dst = 0; dst < this.cuentaDeVertices(); dst++) {
                if (!this.existeArista(src, dst))
                    d.agregarArista(src, dst);
            }
        }
        return d;
    }

    public Digrafo invertirArcos() {
        Digrafo d = new Digrafo(this.cuentaDeVertices());

        for (int i = 0; i < this.cuentaDeVertices(); i++) {
            //System.out.println("Transponiendo vertice: " + i);
            Iterator<Arista> it = this.getAdjList(i);
            while (it.hasNext()) {
                Arista aux = it.next();
                d.agregarArista(aux.getDst(), aux.getSrc());
            }
        }
        return d;
    }
}
