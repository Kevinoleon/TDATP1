package com.fiuba.tarjan;

import java.util.Set;
import java.util.HashSet;
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
        this.articulationPoints = new HashSet<Integer>();
    }
}
