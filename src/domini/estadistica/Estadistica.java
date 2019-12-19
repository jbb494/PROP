package domini.estadistica;

import java.util.Date;

import domini.utils.Pair;
import persistencia.browser.Ctrl_Browser;

/**
 * @class Estadistica
 * @brief Generació de les estadístiques durant la compressió
 * @file

 * @author Manel Aguilar
 */

public class Estadistica {

    /**
     * @param start Instància del moment en que comencem la compressió
     */
    private Date start;

    /**
     * @param end Instància del moment en que acabem la compressió
     */
    private Date end;

    /**
     * @param info Indica si es tracta d'una compressio o descompressio
     * @brief Si es True es tracta d'una compressio, sino d'una descompressio
     */
    private Pair<Boolean,String> info;

    /**
     * @brief Constructor de la classe Estadística
     * @note Generem l'Instància d'inici de temps de compressió
     */
    public Estadistica(Boolean b) {
        start = new Date();
        if (b) 
            info = new Pair<Boolean,String>(b, "compresion");
        else 
            info = new Pair<Boolean,String>(b,"descompresion");
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

    public void work_done() {
        end = new Date();
    } 

    /**
     * @fn public void show_estadistica(String inp, String out)
     * @brief Mostrarà les estadístiques de la compressió
     * @param inp Path de l'arxiu que havíem de comprimir
     * @param out Path de l'arxiu comprimit
     */
    public String show_estadistica(String inp, String out) {
        long ts = time(start,end);

        Ctrl_Browser input = new Ctrl_Browser(inp);
        Ctrl_Browser output = new Ctrl_Browser(out);

        String ret = "<html> <h1> Estadisticas de " + info.R + "</h1> <br />";

        if (input.exists() && output.exists()) {
            if (ts == 0) ts = 1;
            long spd = input.length() / ts;
            double gc;
            if (info.L) 
                gc = (double)input.length() / (double)output.length();
            else 
                gc = (double)output.length() / (double)input.length();
            if(gc == 0.0) 
                gc = 1.0;
            
            ret += "La velocidad de " + info.R + " ha sido de " + spd + " bytes/ms. <br />";
            ret += "El grado de " + info.R + " es de " + String.format("%.6f", gc) + "<br />";
            if(ts/1000 < 1)
                ret += "Tiempo de " + info.R + " : " + ts + " ms. <br />";
            else
                ret += "Tiempo de " + info.R + " : " + (ts/1000) + " segundos. <br />";
        
        }
        else {
            ret += "Generación de estadísticas fallida: <br /> ";
            if (! input.exists())
                ret += "-Input no existe <br />";
            if (! output.exists())
                ret += "-Output no existe";
        }

        return ret;
    }




}
