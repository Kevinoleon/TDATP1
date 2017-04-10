package com.fiuba.grafos;

import org.junit.Test;

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
        d.agregarArista(1, 5);
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

}