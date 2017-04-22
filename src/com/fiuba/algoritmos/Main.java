package com.fiuba.algoritmos;

import com.fiuba.grafos.Arista;
import com.fiuba.grafos.Grafo;
import com.fiuba.grafos.Digrafo;
import com.fiuba.tarjan.Tarjan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException {

        // TODO: obtener opcion por línea de comandos
        int opcion = 3;

        switch(opcion) {
            case 0:
                resolveMarriages();
                break;
            case 1:
                resolveTarjan();
                break;
            case 2:
            default:
                resolveKosaraju();
                break;
        }

        System.out.println("Fin!");
       // System.in.read();
    }

    public static void resolveMarriages() {

    }

    public static void resolveTarjan() {

        int cantidadDeArchivos = 6;

        for (int i = 0; i < cantidadDeArchivos; i++) {
            String nombreArchivo = "data/tarjan/d" + (i + 1) + ".txt";
            Path pathArchivo = Paths.get(nombreArchivo);

            try {
                List<String> lineas = Files.readAllLines(pathArchivo);
                int vertices = Integer.parseInt(lineas.get(0));
                int aristas = Integer.parseInt(lineas.get(1));
                Grafo grafo = new Grafo(vertices);

                for (int j = 2; j < aristas; j++) {
                    String[] aristaInfo = lineas.get(j).split("\\s+");
                    grafo.agregarArista(Integer.parseInt(aristaInfo[0]), Integer.parseInt(aristaInfo[1]));
                }

                System.out.println((i + 1) + ": Grafo creado con " + grafo.getCantidadDeVertices() + " vertices y " + grafo.getCantidadDeAristas() + " aristas.");

                try {
                    Tarjan tarjan = new Tarjan(grafo);

                    long tiempoInicio = System.nanoTime();
                    Set<Integer> puntosDeArticulacion = tarjan.getArticulationPoints();
                    long tiempoFin = System.nanoTime();
                    long tiempoDelAlgoritmo = tiempoFin - tiempoInicio;

                    System.out.println("Puntos de articulación: " + puntosDeArticulacion.toString());
                    System.out.println("Tiempo de algoritmo: " + TimeUnit.NANOSECONDS.toMillis(tiempoDelAlgoritmo) + " (ms) - " + tiempoDelAlgoritmo + "(ns)");
                } catch(Exception e) {
                    System.out.println("Excepción al recorrer grafo: " + e.getMessage());
                } catch(Error e) {
                    System.out.println("Error al recorrer grafo: " + e.getMessage());
                }

            } catch(IOException e) {
                System.out.println("Excepción al leer archivo" + nombreArchivo);
            }
        }
    }

    public static void resolveKosaraju() {
        for(int i=1;i<7;i++) {
            Path path = Paths.get("data/Kosaraju/d"+String.valueOf(i)+".txt");
            Kosaraju k = new Kosaraju(path);
        }
    }
}
