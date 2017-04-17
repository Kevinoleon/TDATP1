package com.fiuba.algoritmos;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //TODO: Write main

        Path path = Paths.get("data/Kosaraju/d6.txt");
        Kosaraju k = new Kosaraju(path);

    }
}
