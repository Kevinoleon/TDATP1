package com.fiuba.algoritmos;

import com.fiuba.grafos.Digrafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by gatti2602 on 16/04/17.
 */
public class Kosaraju {
    private Digrafo d;
    private ArrayList<ArrayList<Integer>> CFC;

    Kosaraju(Digrafo d) {
        this.d = d;
        long time = System.nanoTime();
        ArrayList<ArrayList<Integer>> componentes = d.DFS();

        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Kosaraju - Algo " + " - Primer DFS Completo en " + time + " mSeg.");
        time = System.nanoTime();
        //Ordenar componentes
        ArrayList<Integer> orden = new ArrayList<>();
        Iterator<ArrayList<Integer>> componente = componentes.iterator();
        while (componente.hasNext()) {
            Iterator<Integer> vertices = componente.next().iterator();
            while (vertices.hasNext()) {
                Integer v = vertices.next();
                orden.add(v);
            }
        }
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Kosaraju - Algo " + " - Ordenamiento Completo en " + time + " mSeg.");
        time = System.nanoTime();

        Collections.reverse(orden); //O(n)
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Kosaraju - Algo " + " - Inversion Completo en " + time + " mSeg.");
        time = System.nanoTime();

        Digrafo t = d.transponer();
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Kosaraju - Algo " + " - Transpuesto en " + time + " mSeg.");
        time = System.nanoTime();

        this.CFC = t.DFS(null, orden);
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Kosaraju - Algo " + " - Segundo DFS Completo en " + time + " mSeg.");
    }

    public Integer cuentaComponentesConexas() {
        return this.CFC.size();
    }

    public Digrafo getDigrafo() {
        return d;
    }

    public ArrayList<Integer> getComponenteFuertementeConexa(Integer componente) {
        return (componente >= CFC.size()) ? null : CFC.get(componente);
    }
}
