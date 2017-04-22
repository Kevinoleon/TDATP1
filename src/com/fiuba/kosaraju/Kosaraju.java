package com.fiuba.kosaraju;

import com.fiuba.grafos.Digrafo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gatti2602 on 16/04/17.
 */
public class Kosaraju {
    private Digrafo d;
    private ArrayList<ArrayList<Integer>> CFC;
    private long totalTime;

    public Kosaraju(Path path) {
        List<String> file = new ArrayList<>();
        long time = System.nanoTime();

        try {
            file = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        //System.out.println("Kosaraju - File " + path.toString() + " - Parseo Completo en " + time + " mSeg.");

        time = System.nanoTime();
        Digrafo d = new Digrafo(Integer.parseInt(file.get(0)));
        for (int i = 2; i < file.size(); i++) {
            String[] aux = file.get(i).split(" ");
            d.agregarArista(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
        }
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toSeconds(time);
        //System.out.println("Kosaraju - File " + path.toString() + " - Digrafo Completo en " + time + " mSeg.");
        System.gc();

        this.calcKosaraju(d);
    }

    private void calcKosaraju(Digrafo d) {
        this.d = d;
        long time = System.nanoTime();
        totalTime = time;
        ArrayList<ArrayList<Integer>> componentes = new RecorredorDeGrafo(d).DFS();

        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        //System.out.println("Kosaraju - Algo " + " - Primer DFS Completo en " + time + " mSeg.");
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

        Collections.reverse(orden); //O(n)
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        //System.out.println("Kosaraju - Algo " + " - Ordenamiento Completo en " + time + " mSeg.");
        time = System.nanoTime();

        Digrafo t = d.invertirArcos();
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        //System.out.println("Kosaraju - Algo " + " - Inversion en " + time + " mSeg.");
        time = System.nanoTime();

        this.CFC = new RecorredorDeGrafo(t).DFS(null, orden);
        time = System.nanoTime() - time;
        totalTime = System.nanoTime() - totalTime;
        totalTime = TimeUnit.NANOSECONDS.toMillis(totalTime);
        time = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Kosaraju - Algo Vertices: "+ this.d.cuentaDeVertices() + " Aristas: " + this.d.cuentaDeAristas() +
                " Finalizado OK en " + totalTime + " mSeg.");
        System.out.println("Kosaraju - Algo " + "Componentes fuertemente conexos: " + CFC.size());
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
