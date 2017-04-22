package com.fiuba.galeShapley;

import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by lt5420 on 22/04/2017.
 * Genera listas Aleatorias de numeros enteros
 */
public class HospitalRandomizer {

    private Integer cantEstudiantes, cantHospitales;

    public HospitalRandomizer(int cantEstudiantes, int cantHospitales) {
        this.cantEstudiantes = cantEstudiantes;
        this.cantHospitales = cantHospitales;
    }

    public void toFiles(String filename) {
        try {
            File file = new File(filename);
            if (file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "Cp1252"));
            //Imprimo data estudiantes
            bw.write(cantEstudiantes.toString());
            bw.newLine();
            for (int i = 0; i < cantEstudiantes; i++){
                bw.write(arrayListToString(Randomizer.CreateRandomUniqueVector(cantHospitales).iterator()));
                bw.newLine();
                if(i %1000 == 0)
                    bw.flush();
            }
                bw.flush();
            //Imprimo data hospitales
            bw.write(cantHospitales.toString());
            bw.newLine();
            for (int i = 0; i < cantHospitales; i++){
                bw.write(arrayListToString(Randomizer.CreateRandomUniqueVector(cantEstudiantes).iterator()));
                bw.newLine();
                if(i %1000 == 0)
                    bw.flush();
            }

            //Imprimo capacidad Hospitales
            bw.write(arrayListToString(Randomizer.CreateRandomVector(cantHospitales,cantEstudiantes).iterator()));
            bw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private String arrayListToString(Iterator<Integer>it){
            StringBuilder st = new StringBuilder();

            if(it.hasNext())
                st.append(it.next());

            while(it.hasNext()) {
                st.append(" ");
                st.append(it.next());
            }
            return st.toString();
    }

}


