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

    /**
     * Cada elemento contiene la lista de arsitas del vertice
     * referenciado
     */
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

    /**
     * Devuelve una lista con los vertices visitados desde el src
     *
     * @param src Si es null inicia del vertice 0, si es mayor o igual a cuentaVertices devuelve null
     * @param ordenDeVertices Opcional, indica el orden en que se deben recorrer los vertices al terminar la
     *                        exploracion de todos los caminos posibles del vertice actual.
     * @return Devuelve una lista de listas de vertices. Cada lista es una compponente conexa visitada con DFS
     */
    public ArrayList<ArrayList<Integer>> DFS(Integer src, ArrayList<Integer> ordenDeVertices) {

        if (src == null)
            src = 0;

        if (ordenDeVertices != null && ordenDeVertices.size() != this.cuentaDeVertices())
            ordenDeVertices = null;

        if (src >= this.cuentaDeVertices())
            return null;

        ArrayList<ArrayList<Integer>> listaVerticesVisitados = new ArrayList<>();
        Boolean[] verticeVisitado = new Boolean[this.cuentaDeVertices()];

        //Hago DFS desde el vertice indicado
        ArrayList<Integer> componenteConexa1 = new ArrayList<>();
        listaVerticesVisitados.add(componenteConexa1);
        DFS_Visitar(src, componenteConexa1, verticeVisitado);

        //Hago DFS desde los demas vertices
        for (int i = 0; i < this.cuentaDeVertices(); i++) {
            //Si hay orden de vertices voy chequeando en ese orden, sino orden natural.
            int proximoVertice = (ordenDeVertices != null) ? ordenDeVertices.get(i) : i;

            if (!verticeVisitado[proximoVertice]) {
                ArrayList<Integer> componenteConexa = new ArrayList<>();
                listaVerticesVisitados.add(componenteConexa);
                DFS_Visitar(proximoVertice, componenteConexa, verticeVisitado);
            }

        }
        return listaVerticesVisitados;
    }

    private void DFS_Visitar(Integer v, ArrayList<Integer> listaVerticesVisitados, Boolean[] verticeVisitado) {

        verticeVisitado[v] = true;
        listaVerticesVisitados.add(v);

        //El orden de visita depende de este iterador
        Iterator<Arista> it = this.getAdjList(v);
        while (it.hasNext()) {
            Arista a = it.next();
            if (!verticeVisitado[a.getDst()]) {
                DFS_Visitar(v, listaVerticesVisitados, verticeVisitado);
            }
        }
    }

    public Digrafo transponer() {
        Digrafo d = new Digrafo(this.cuentaDeVertices());

        for (int src = 0; src < this.cuentaDeVertices(); src++) {
            for (int dst = 0; dst < this.cuentaDeVertices(); dst++) {
                if (!existeArista(src, dst))
                    d.agregarArista(src, dst);
            }
        }
        return d;
    }
}
