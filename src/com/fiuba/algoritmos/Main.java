package com.fiuba.algoritmos;

import com.fiuba.grafos.Grafo;
import com.fiuba.tarjan.Tarjan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("------------------------------------");
        System.out.println("\uD83D\uDE80 \uD83D\uDE80 TP1 Teoría de Algoritmos \uD83D\uDE80 \uD83D\uDE80");
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println("Elige el algoritmo a correr:");
        System.out.println();
        System.out.println("1. Asignación de residencias");
        System.out.println("2. Puntos de falla");
        System.out.println("3. Comunidades en redes");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        int opcion = Integer.parseInt(scanner.nextLine());
        System.out.println();

        switch(opcion) {
            case 1:
                System.out.println("Asignación de residencias");
                System.out.println("-------------------------");
                System.out.println();
                resolveResidences();
                break;
            case 2:
                System.out.println("Puntos de Falla");
                System.out.println("---------------");
                System.out.println();
                resolveTarjan();
                break;
            case 3:
            default:
                System.out.println("Comunidades en redes");
                System.out.println("--------------------");
                System.out.println();
                resolveKosaraju();
                break;
        }

        System.out.println();
        System.out.println("\uD83D\uDC7E \uD83D\uDC7E Fin! \uD83D\uDC7E \uD83D\uDC7E");
    }

    public static void resolveResidences() {

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
                    long tiempoInicio = System.nanoTime();
                    Tarjan tarjan = new Tarjan(grafo);
                    long tiempoDelAlgoritmo = System.nanoTime() - tiempoInicio;

                    Set<Integer> puntosDeArticulacion = tarjan.getArticulationPoints();

                    System.out.println("Puntos de articulación: " + puntosDeArticulacion.toString());
                    System.out.println("Tiempo de algoritmo: " + TimeUnit.NANOSECONDS.toMillis(tiempoDelAlgoritmo) + " (ms) - " + tiempoDelAlgoritmo + "(ns)");
                } catch(Exception e) {
                    System.out.println("Excepción al recorrer grafo: " + e.getMessage());
                } catch(Error e) {
                    System.out.println("Error al recorrer grafo: " + e.getMessage());
                }

                System.out.println();

            } catch(IOException e) {
                System.out.println("Excepción al leer archivo" + nombreArchivo);
            }
        }
    }

    public static void resolveKosaraju() {
        Path path = Paths.get("data/Kosaraju/d6.txt");
        Kosaraju k = new Kosaraju(path);
    }
}
