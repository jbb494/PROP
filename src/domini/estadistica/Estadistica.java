package domini.estadistica;

import java.io.File;
import java.util.Date;
import java.lang.Math;

/**
 * @class Estadistica
 * @brief Generació de les estadístiques durant la compressió
 * @author Manel Aguilar
 */

public class Estadistica {

    /**
     * @param start Instància del moment en que comencem la compressió
     */
    Date start;

    /**
     * @param end Instància del moment en que acabem la compressió
     */
    Date end;

    /**
     * @brief Constructor de la classe Estadística
     * @note Generem l'Instància d'inici de temps de compressió
     */
    public Estadistica() {
        start = new Date();
    }

    //Functions

    /**
     * @fn private int speed(Calendar i, Calendar f)
     * @brief Càlcul del temps que hem trigat en comprimir l'arxiu
     * @param i Instància del moment en que hem començat a comprimir
     * @param f Instància del moment en que hem acabat de comprimir
     * @return Temps emprat per comprimir
     */
    private long time(Date i, Date f) {
        long res = (f.getTime() - i.getTime());
        return res;
    }

    /**
     * @fn public void show_estadistica(String inp, String out)
     * @brief Mostrarà les estadístiques de la compressió
     * @param inp Path de l'arxiu que havíem de comprimir
     * @param out Path de l'arxiu comprimit
     */
    public void show_estadistica(String inp, String out) {
        end = new Date();
        long ts = time(start,end);
        
        File input = new File(inp);
        File output = new File(out);

        if (input.exists() && output.exists()) {
            long spd = input.length() / ts;
            double gc = (double)input.length() / (double)output.length();
            if(input.length().equals(output.length())) gc = 1.0;
            if (ts == 0) ts = 1;

            System.out.println("La velocitat de compressió ha sigut de " + spd + " bytes/ms.");
            System.out.println("El grau de compressió és de " + String.format("%.6f", gc));
            if(ts/1000 < 1) System.out.println("Temps en comprimir: " + ts + " ms.\n");
            else System.out.println("Temps en comprimir: " + (ts/1000) + " segons.\n");
        
        }
        else
            System.err.println("Generación de estadísticas fallida: Input o output no existe");

            
    }




}
