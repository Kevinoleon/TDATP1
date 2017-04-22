package com.fiuba.galeShapley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by lt5420 on 22/04/2017.
 */
public class Randomizer {
    public static ArrayList<Integer> CreateRandomVector(Integer m, Integer randLimit){
        Random r = new Random();
        ArrayList<Integer> aux = new ArrayList<>(m);
        for (int j=0;j<m;j++) {
            if (randLimit != null)
                aux.add(r.nextInt(randLimit));
            else
                aux.add(r.nextInt());
        }
        return aux;
    }

    /**
     * Carga una Matriz de n+m cuyos valores enteros no superan randLimit
     * @param n
     * @param m
     * @param randLimit
     * @return
     */
    public static ArrayList<ArrayList<Integer>>CreateRandomMatrix(Integer n, Integer m, Integer randLimit){
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>(n);
        for (int i=0; i<n;i++)
            matrix.add(Randomizer.CreateRandomVector(m, randLimit));
        return matrix;
    }

    public static HashMap<Integer,ArrayList<Integer>> CreateRandomUniqueMatrix(Integer n, Integer m){
        HashMap<Integer, ArrayList<Integer>> matrix = new HashMap<>(n);
        for (int i=0; i<n;i++)
            matrix.put(i,Randomizer.CreateRandomUniqueVector(m));
        return matrix;
    }

    public static ArrayList<Integer> CreateRandomUniqueVector(Integer n){
        ArrayList<Integer> aux = new ArrayList<>();
        for(int i=0; i<n;i++)
            aux.add(i);
        Collections.shuffle(aux);
        return aux;
    }
}


