package com.fiuba.kosaraju;

import com.fiuba.grafos.Arista;
import com.fiuba.grafos.Digrafo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by marianovazquez on 4/22/17.
 */
public class RecorredorDeGrafo {

    Digrafo digrafo;

    public RecorredorDeGrafo(Digrafo digrafo) {
        this.digrafo = digrafo;
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
            src = (ordenDeVertices != null) ? ordenDeVertices.get(0) : 0;

        if (ordenDeVertices != null && ordenDeVertices.size() != this.digrafo.cuentaDeVertices())
            ordenDeVertices = null;

        if (src >= this.digrafo.cuentaDeVertices())
            return null;

        ArrayList<ArrayList<Integer>> listaVerticesVisitados = new ArrayList<>();
        boolean[] verticeVisitado = new boolean[this.digrafo.cuentaDeVertices()];

        //Hago DFS desde el vertice indicado
        ArrayList<Integer> componenteConexa1 = new ArrayList<>();
        listaVerticesVisitados.add(componenteConexa1);
        DFS_Visitar(src, componenteConexa1, verticeVisitado);

        //Hago DFS desde los demas vertices
        for (int i = 0; i < this.digrafo.cuentaDeVertices(); i++) {
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

    public ArrayList<ArrayList<Integer>> DFS() {
        return DFS(null, null);
    }

    private void DFS_Visitar(Integer v, ArrayList<Integer> listaVerticesVisitados, boolean[] verticeVisitado) {

        verticeVisitado[v] = true;
        listaVerticesVisitados.add(v);

        //El orden de visita depende de este iterador
        Iterator<Arista> it = this.digrafo.getAdjList(v);
        while (it.hasNext()) {
            Arista a = it.next();
            if (!verticeVisitado[a.getDst()]) {
                DFS_Visitar(a.getDst(), listaVerticesVisitados, verticeVisitado);
            }
        }
    }
}
