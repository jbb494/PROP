package com.company.estadistica;

import java.io.File;
import java.util.Calendar;

public class Estadistica {

    Calendar start;
    Calendar end;

    public Estadistica() {
        start = Calendar.getInstance();
    }

    public int speed(Calendar i, Calendar f) {

        int ti = ((i.get(Calendar.HOUR) * 60) + i.get(Calendar.MINUTE)) * 60 + i.get(Calendar.SECOND);
        int tf = ((f.get(Calendar.HOUR) * 60) + f.get(Calendar.MINUTE)) * 60 + f.get(Calendar.SECOND);

        return tf - ti;
    }

    public void show_estadistica(String inp, String out) {
        end = Calendar.getInstance();
        int ts = speed(start,end);
        System.out.println("The text has been compressed in " + ts + " seconds.");

        File input = new File(inp);
        File output = new File(out);

        //Grado de compresión, como lo calculamos?
        long gc = -1;
        if (input.exists() && output.exists()) 
            gc = input.length() / output.length();
        else
            System.err.println("Generación de estadísticas fallida: Input o output no existe");

        if (gc != -1)
            System.out.println("El grau de compressió és de " + gc + ".");
    }




}