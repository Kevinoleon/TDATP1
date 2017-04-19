package com.fiuba.tarjan;

import java.util.*;

import com.fiuba.grafos.Arista;
import com.fiuba.grafos.Grafo;

/**
 * Created by marianovazquez on 4/19/17.
 */
public class Tarjan {

    private Grafo grafo;
    private Set<Integer> articulationPoints;

    public Tarjan(Grafo g) {
        this.grafo = g;
        this.calculateArticulationPoints();
    }

    /**
     * Devuelve el conjunto de vértices que son puntos de articulación del grafo.
     * @return   El conjunto de vértices que son puntos de articulación.
     */
    public Set<Integer> getArticulationPoints()  {
        return this.articulationPoints;
    }

    private void calculateArticulationPoints() {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> puntosDeArticulacion = new HashSet<>();
        boolean[] isNodoVisitado = new boolean[this.grafo.getCantidadDeVertices()];

        // Inicio DFS en un vértice predefinido
        int raiz = 0;
        stack.push(raiz);
        puntosDeArticulacion.add(raiz);

        while (!stack.empty()) {
            int nodo = stack.pop();
            if (!isNodoVisitado[nodo]) {

                // Chequeo si es punto de articulación
                if (this.isPuntoDeArticulacion(nodo)) {
                    puntosDeArticulacion.add(nodo);
                }

                isNodoVisitado[nodo] = true;
                Iterator<Arista> aristas = this.grafo.getAdyacentes(nodo);
                while (aristas.hasNext()) {
                    Arista arista = aristas.next();
                    stack.push(arista.getDst());
                }
            }
        }

        this.articulationPoints = puntosDeArticulacion;
    }

    private boolean isPuntoDeArticulacion(int nodo) {
        return true;
    }
}
