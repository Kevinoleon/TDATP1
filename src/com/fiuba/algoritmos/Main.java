package com.fiuba.algoritmos;

import com.fiuba.grafos.Digrafo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
	// write your code here
        //TODO: Write main

        Path path = Paths.get("data/Kosaraju/d3.txt");
        List<String> file = new ArrayList<>();
        long time = System.nanoTime();
        try {
            file = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Kosaraju - File " + path.toString() + " - Parseo Completo en " + time + " mSeg.");

        time = System.nanoTime();
        Digrafo d = new Digrafo(Integer.parseInt(file.get(0)));
        for (int i = 2; i < file.size(); i++) {
            String[] aux = file.get(i).split(" ");
            d.agregarArista(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
        }
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toSeconds(time);
        System.out.println("Kosaraju - File " + path.toString() + " - Digrafo Completo en " + time + " mSeg.");

        time = System.nanoTime();
        Kosaraju k = new Kosaraju(d);

        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.toSeconds(time);
        System.out.println("Kosaraju - File " + path.toString() + " - Kosaraju Completo en " + time + " mSeg.");

    }
}
