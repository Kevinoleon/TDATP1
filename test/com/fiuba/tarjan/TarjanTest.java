package com.fiuba.tarjan;

import com.fiuba.grafos.Arista;
import com.fiuba.grafos.Grafo;
import com.fiuba.tarjan.Tarjan;
import org.junit.Test;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by marianovazquez on 4/19/17.
 */
public class TarjanTest {

    @Test
    public void getArticulationPoints_basic_1() {
        /*
            Grafo:
                    0---1---2---3

            Puntos de articulación: {1, 2}
        */
        Grafo grafo = new Grafo(4);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(2, 3);

        Tarjan tarjan = new Tarjan(grafo);
        Set<Integer> resultado = tarjan.getArticulationPoints();
        Set<Integer> esperado = new HashSet<>(2);
        esperado.add(1);
        esperado.add(2);

        assertEquals(esperado, resultado);
    }

    @Test
    public void getArticulationPoints_basic_2() {
        /*
            Grafo:
                    1-----0-----3
                    |    /      |
                    2---/       4

            Puntos de articulación: {0, 3}
        */
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(0, 3);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(3, 4);

        Tarjan tarjan = new Tarjan(grafo);
        Set<Integer> resultado = tarjan.getArticulationPoints();
        Set<Integer> esperado = new HashSet<>(2);
        esperado.add(0);
        esperado.add(3);

        assertEquals(esperado, resultado);
    }

    @Test
    public void getArticulationPoints_basic_3() {
        /*
            Grafo:
                    0-----1-----3-----5
                    |    /|\         /
                    2---/ 6 \---4---/


            Puntos de articulación: {1}
        */
        Grafo grafo = new Grafo(7);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 6);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(3, 5);
        grafo.agregarArista(4, 5);

        Tarjan tarjan = new Tarjan(grafo);
        Set<Integer> resultado = tarjan.getArticulationPoints();
        Set<Integer> esperado = new HashSet<>(1);
        esperado.add(1);

        assertEquals(esperado, resultado);
    }
}
