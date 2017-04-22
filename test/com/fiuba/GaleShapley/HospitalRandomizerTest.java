package com.fiuba.galeShapley;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lt5420 on 22/04/2017.
 */
public class HospitalRandomizerTest {
    @Test
    public void toFiles() throws Exception {
        HospitalRandomizer hosp = new HospitalRandomizer(10000,2000);

        hosp.toFiles("fileprueba.txt");

        assertTrue(true);
    }

}