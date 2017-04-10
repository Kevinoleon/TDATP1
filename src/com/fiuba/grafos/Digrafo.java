package com.fiuba.grafos;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by gatti2602 on 09/04/17.
 * Implementa la clase Digrafo inmutable.
 * Representa los vertices como una entrada en la lista de adyacencia
 * Cada vertice se representa por una lista de Aristas. Los vertices empiezan en 0
 * El digrafo es no pesado.
 */
public class Digrafo {

    private ArrayList<ArrayList<Arista>> adjList;

    Digrafo(Integer vertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++)
            adjList.add(new ArrayList<>());
    }

    /**
     * Devuelve la cantidad de Vertices del Grafo
     */
    public Integer cuentaDeVertices() {
        return adjList.size();
    }

    /**
     * Devuelve la cantidad de Aristas del Grafoo
     */
    public Integer cuentaDeAristas() {
        Integer aux = 0;
        for (ArrayList lista : adjList) {
            aux += lista.size();
        }
        return aux;
    }

    /**
     * Devuelve un iterador sobre la lista de adyacencia del vertice indicado.
     * Devuelve null si el vertice no existe
     */
    public Iterator<Arista> getAdjList(Integer vertice) {
        if (vertice >= adjList.size())
            return null;
        return adjList.get(vertice).iterator();
    }

    /**
     * Agrega una Arista si el src esta dentro de los vertices del grafo y la arista no existe
     */
    public void agregarArista(Integer src, Integer dst) {

        if (src >= adjList.size())
            return;

        if (!existeArista(src, dst)) {
            ArrayList<Arista> verticeAdjList = adjList.get(src);
            verticeAdjList.add(new Arista(src, dst));
        }
    }

    public Boolean existeArista(Integer src, Integer dst) {
        Boolean existe = false;
        if (src < adjList.size()) {
            ArrayList<Arista> verticeAdjList = adjList.get(src);

            //Chequeo que no exista la arista
            Iterator<Arista> it = verticeAdjList.iterator();
            while (it.hasNext() && !existe) {
                Arista e = it.next();
                if (e.getDst().equals(dst))
                    existe = true;
            }

        }
        return existe;
    }

}
