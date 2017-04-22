package com.fiuba.grafos;

import com.fiuba.kosaraju.RecorredorDeGrafo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by gatti2602 on 09/04/17.
 */
public class DigrafoTest {
    @Test
    public void cuentaDeVertices() throws Exception {
        Digrafo d = new Digrafo(5);

        assertEquals(Integer.valueOf(5), d.cuentaDeVertices());
    }

    @Test
    public void cuentaDeAristas() throws Exception {
        Digrafo d = new Digrafo(5);
        d.agregarArista(1, 4);
        d.agregarArista(1, 2);
        d.agregarArista(0, 3);
        assertEquals(Integer.valueOf(3), d.cuentaDeAristas());

        //Agrego arista invalida
        d.agregarArista(5, 2);
        assertEquals(Integer.valueOf(3), d.cuentaDeAristas());

    }

    @Test
    public void agregarAristasDuplicadasNoDuplica() throws Exception {
        Digrafo d = new Digrafo(5);
        d.agregarArista(1, 4);
        assertEquals(Integer.valueOf(1), d.cuentaDeAristas());
        d.agregarArista(1, 4);
        assertEquals(Integer.valueOf(1), d.cuentaDeAristas());
    }

    @Test
    public void getAdjList() throws Exception {
        Digrafo d = new Digrafo(5);
        d.agregarArista(1, 4);
        Iterator<Arista> it = d.getAdjList(1);

        assertTrue(it.hasNext());

        Arista a = it.next();
        assertFalse(it.hasNext());
        assertEquals(Integer.valueOf(1), a.getSrc());
        assertEquals(Integer.valueOf(4), a.getDst());
    }

    @Test
    public void agregarArista() throws Exception {
        Digrafo d = new Digrafo(5);
        d.agregarArista(1, 4);
        Arista a = d.getAdjList(1).next();

        assertEquals(Integer.valueOf(1), a.getSrc());
        assertEquals(Integer.valueOf(4), a.getDst());
    }

    @Test
    public void transponerGrafo() throws Exception {
        Digrafo d = new Digrafo(2);
        d.agregarArista(0, 1);
        d.agregarArista(1, 0);
        Digrafo t = d.transponer();

        assertEquals(Integer.valueOf(2), t.cuentaDeAristas());
        assertEquals(d.cuentaDeVertices(), t.cuentaDeVertices());
        assertTrue(t.existeArista(1, 1));
        assertTrue(t.existeArista(0, 0));
        assertNotSame(d, t);
    }

    @Test
    public void DFSDevuelveListaCorrecta() throws Exception {
        Digrafo d = new Digrafo(4);
        d.agregarArista(0, 1);
        d.agregarArista(1, 2);

        //Componentes conexas 0->1->2 y 3
        ArrayList<ArrayList<Integer>> componentesConexas = new RecorredorDeGrafo(d).DFS(null, null);

        assertEquals(Integer.valueOf(2), (Integer) componentesConexas.size());
        //Chequeo Componente 1
        assertEquals(Integer.valueOf(3), (Integer) componentesConexas.get(0).size());
        assertEquals(Integer.valueOf(0), (Integer) componentesConexas.get(0).get(0));
        assertEquals(Integer.valueOf(1), (Integer) componentesConexas.get(0).get(1));
        assertEquals(Integer.valueOf(2), (Integer) componentesConexas.get(0).get(2));
        //Chequeo Componente 2
        assertEquals(Integer.valueOf(1), (Integer) componentesConexas.get(1).size());
        assertEquals(Integer.valueOf(3), (Integer) componentesConexas.get(1).get(0));

    }

    @Test
    public void DFSDevuelveListaCorrectaConOrden() throws Exception {
        Digrafo d = new Digrafo(4);
        d.agregarArista(0, 1);
        d.agregarArista(1, 2);
        ArrayList<Integer> orden = new ArrayList<>();
        orden.add(3);
        orden.add(0);
        orden.add(1);
        orden.add(2);
        //Componentes conexas 0->1->2 y 3
        ArrayList<ArrayList<Integer>> componentesConexas = new RecorredorDeGrafo(d).DFS(null, orden);

        assertEquals(Integer.valueOf(2), (Integer) componentesConexas.size());
        //Chequeo Componente 1
        assertEquals(Integer.valueOf(3), (Integer) componentesConexas.get(1).size());
        assertEquals(Integer.valueOf(0), (Integer) componentesConexas.get(1).get(0));
        assertEquals(Integer.valueOf(1), (Integer) componentesConexas.get(1).get(1));
        assertEquals(Integer.valueOf(2), (Integer) componentesConexas.get(1).get(2));
        //Chequeo Componente 2
        assertEquals(Integer.valueOf(1), (Integer) componentesConexas.get(0).size());
        assertEquals(Integer.valueOf(3), (Integer) componentesConexas.get(0).get(0));

    }

}