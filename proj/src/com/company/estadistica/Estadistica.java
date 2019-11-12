/**
 * @class Estadistica
 * @brief Generació de les estadístiques durant la compressió
 * @author Miguel Paracuellos Ocaña
 */


package com.company.estadistica;

import java.io.File;
import java.util.Calendar;

public class Estadistica {

    /**
     * @param start Instancia del moment en que comencem la compressió
     */
    Calendar start;

    /**
     * @param end Instancia del moment en que acabem la compressió
     */
    Calendar end;

    /**
     * @brief Constructor de la classe Estadística
     * @note Generem l'Instancia d'inici de temps de compressió
     */
    public Estadistica() {
        start = Calendar.getInstance();
    }

    //Functions

    /**
     * @fn private int speed(Calendar i, Calendar f)
     * @brief Càlcula del temps que hem trigat en comprimir l'arxiu
     * @param i Instancia del moment en que hem començat a comprimir
     * @param f Instancia del moment en que hem acabat de comprimir
     * @return Temps emprat per comprimir
     */
    private int speed(Calendar i, Calendar f) {

        int ti = ((i.get(Calendar.HOUR) * 60) + i.get(Calendar.MINUTE)) * 60 + i.get(Calendar.SECOND);
        int tf = ((f.get(Calendar.HOUR) * 60) + f.get(Calendar.MINUTE)) * 60 + f.get(Calendar.SECOND);

        return tf - ti;
    }

    /**
     * @fn public void show_estadistica(String inp, String out)
     * @brief Mostrarà les estadístiques de la compressió
     * @param inp Path de l'arxiu que haviem de comprimir
     * @param out Path de l'arxiu comprimit
     */
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