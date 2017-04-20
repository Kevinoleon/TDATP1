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
        this.articulationPoints = new HashSet<Integer>();
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

        // Inicio el recorrido del grafo en un vértice predefinido
        int numeroVisita = 0;
        VerticeTarjan inicio = new VerticeTarjan(0, -1, numeroVisita, numeroVisita);

        Map<Integer, VerticeTarjan> infoVerticesVisitados = new HashMap<>();
        infoVerticesVisitados.put(inicio.id, inicio);
        this.findArticulationPoints(inicio, numeroVisita, infoVerticesVisitados);
    }

    /**
     * Se recorre el grafo usando DFS a fin de identificar los puntos de articulación del grafo.
     */
    private void findArticulationPoints(VerticeTarjan verticeTarjan, int numeroVisita, Map<Integer, VerticeTarjan> infoVerticesVisitados) {

        numeroVisita++;
        int hijosDelVertice = 0;
        boolean isArticulationPoint = false;

        Iterator<Arista> aristas = this.grafo.getAdyacentes(verticeTarjan.id);

        while (aristas.hasNext()) {
            int verticeAdyacenteId = aristas.next().getDst();

            // Chequeo que la arista no vaya hacia el mismo vértice
            if (verticeAdyacenteId == verticeTarjan.id) {
                continue;
            }

            // Chequeo que el nodo adyacente no haya sido visitado
            VerticeTarjan adyacente = infoVerticesVisitados.get(verticeAdyacenteId);
            if (adyacente == null) {
                hijosDelVertice++;
                VerticeTarjan hijoAdyacente = new VerticeTarjan(verticeAdyacenteId, verticeTarjan.id, numeroVisita, numeroVisita);
                infoVerticesVisitados.put(verticeAdyacenteId, hijoAdyacente);
                this.findArticulationPoints(hijoAdyacente, numeroVisita, infoVerticesVisitados);

                if (verticeTarjan.numeroVisita <= hijoAdyacente.numeroBajo) {
                    isArticulationPoint = true;
                } else {
                    verticeTarjan.numeroBajo = Math.min(verticeTarjan.numeroBajo, hijoAdyacente.numeroBajo);
                }
            } else {
                verticeTarjan.numeroBajo = Math.min(verticeTarjan.numeroBajo, adyacente.numeroVisita);
            }
        }

        // Es un punto de articulación si:
        // - Luego del recorrido DFS: numeroVisita <= numeroBajo (hallado en el loop)
        // - Es raiz y tiene más de 2 hijos
        if(verticeTarjan.padreId == -1 && hijosDelVertice >= 2
            || verticeTarjan.padreId != -1 && isArticulationPoint) {
            this.articulationPoints.add(verticeTarjan.id);
        }
    }
}


