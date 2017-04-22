package com.fiuba.galeShapley;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by marianovazquez on 4/22/17.
 */
public class GaleShapleyTest {

    @Test
    public void getParejas_basic_1() {
        HashMap<Integer, ArrayList<Integer>> listaDePreferenciasPreferentes = new HashMap<>();
        listaDePreferenciasPreferentes.put(0, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasPreferentes.put(1, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasPreferentes.put(2, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));

        HashMap<Integer, ArrayList<Integer>> listaDePreferenciasOferentes = new HashMap<>();
        listaDePreferenciasOferentes.put(0, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasOferentes.put(1, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasOferentes.put(2, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));

        GaleShapley galeShapley = new GaleShapley(listaDePreferenciasPreferentes, listaDePreferenciasOferentes);
        HashMap<Integer, Integer> resultado = galeShapley.getParejas();

        HashMap<Integer, Integer> esperado = new HashMap<>();
        esperado.put(0, 0);
        esperado.put(1, 1);
        esperado.put(2, 2);

        assertEquals(esperado, resultado);
    }

    @Test
    public void getParejas_basic_2() {
        HashMap<Integer, ArrayList<Integer>> listaDePreferenciasPreferentes = new HashMap<>();
        listaDePreferenciasPreferentes.put(0, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasPreferentes.put(1, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasPreferentes.put(2, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));

        HashMap<Integer, ArrayList<Integer>> listaPreferenciasOferentes = new HashMap<>();
        listaPreferenciasOferentes.put(0, new ArrayList<Integer>(Arrays.asList(2, 1, 0)));
        listaPreferenciasOferentes.put(1, new ArrayList<Integer>(Arrays.asList(2, 1, 0)));
        listaPreferenciasOferentes.put(2, new ArrayList<Integer>(Arrays.asList(2, 1, 0)));

        GaleShapley galeShapley = new GaleShapley(listaDePreferenciasPreferentes, listaPreferenciasOferentes);
        HashMap<Integer, Integer> resultado = galeShapley.getParejas();

        HashMap<Integer, Integer> esperado = new HashMap<>();
        esperado.put(0, 2);
        esperado.put(1, 1);
        esperado.put(2, 0);

        assertEquals(esperado, resultado);
    }

    @Test
    public void getParejas_basic_3() {
        HashMap<Integer, ArrayList<Integer>> listaDePreferenciasPreferentes = new HashMap<>();
        listaDePreferenciasPreferentes.put(0, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasPreferentes.put(1, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
        listaDePreferenciasPreferentes.put(2, new ArrayList<Integer>(Arrays.asList(0, 1, 2)));

        HashMap<Integer, ArrayList<Integer>> listaPreferenciasOferentes = new HashMap<>();
        listaPreferenciasOferentes.put(0, new ArrayList<Integer>(Arrays.asList(2, 0, 1)));
        listaPreferenciasOferentes.put(1, new ArrayList<Integer>(Arrays.asList(2, 0, 1)));
        listaPreferenciasOferentes.put(2, new ArrayList<Integer>(Arrays.asList(2, 0, 1)));

        GaleShapley galeShapley = new GaleShapley(listaDePreferenciasPreferentes, listaPreferenciasOferentes);
        HashMap<Integer, Integer> resultado = galeShapley.getParejas();

        HashMap<Integer, Integer> esperado = new HashMap<>();
        esperado.put(0, 1);
        esperado.put(1, 2);
        esperado.put(2, 0);

        assertEquals(esperado, resultado);
    }
}
