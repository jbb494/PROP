package presentacion.master;
import domini.algorithm.Ctrl_Algorithm;
import domini.estadistica.Estadistica;

/**
 * @class Ctrl_Master
 * @brief Classe Ctrl_Master
 * @author Miguel Paracuellos
 */
public class Ctrl_Master {

    //Attributes
    /**
     * @param Function Indica el que vol fer l'usuari
     */
    Integer Function;

    /**
     * @param Path Path de l'arxiu que hem de tractar
     */
    String Path;


    /**
     * @brief Constructor de la classe
     */
    public Ctrl_Master() {
        Function = 0;
        Path = "";
    }

    //Functions
    /**
     * @fn public void Context()
     * @brief S'encarrega de preguntar a l'usuari que vol fer
     */
    public void Context() {
        
        //Asking if we wanna encode or decode
        System.out.println("Vol comprimir o descomprimir un fitxer?");
        String input = System.console().readLine().toLowerCase();
        
        if (input.equals("comprimir"))
        {
            System.out.println("Vol fer-ho de manera manual o automàtica?");
            String aux = System.console().readLine().toLowerCase();
            
            if (aux.equals("manual")) Function = 3; // Compressió manual
            else Function = 1; // Compressió automàtica
        }
        else Function = 2; // Descompressió

        //Asking the name of the file we wanna encode / decode
        System.out.println("Quin és el nom de l'arxiu, que està dintre la carpeta src/persistencia/data, amb el que volem treballar?");
        Path = System.console().readLine();
        Path = "../src/persistencia/data/".concat(Path);
    }

    /**
     * @fn public String Work()
     * @brief Realitza l'accio que l'usuari hagi indicar
     * @return Retorna informació sobre l'execucio
     */
    public String Work() {
        Ctrl_Algorithm CAlg = new Ctrl_Algorithm();
        String tornada = "";
        if(Function == 1) //Ho hem de pensar
        {   
            //Estadistica est = new Estadistica();
            tornada = CAlg.Auto_Encoder(Path);
            //est.show_estadistica(Path, out);
        }
        else if(Function == 2)
        {
            String extension = "";
            int i = Path.lastIndexOf(".");
            extension = Path.substring(i+1);
            tornada = CAlg.Auto_Decoder(Path, extension);
        }
        else if(Function == 3)
        {
            String extension = "";
            int i = Path.lastIndexOf(".");
            extension = Path.substring(i+1);
            if(extension == "ppm") tornada = CAlg.Choose_Encoder(Path, "ppm");
            else
            {
                System.out.println("Amb quin algorisme vol comprimir el fitxer: LZ78, LZW, LZSS o JPEG?");
                System.out.println("(escriviu-lo en minúsucules)");
                String mode = System.console().readLine().toLowerCase();
                Estadistica est = new Estadistica();
                tornada = CAlg.Choose_Encoder(Path, mode);
                //Generació de les estadístiques
                String out_p = Path.substring(0, i+1) + mode;
                est.show_estadistica(Path,out_p);
            }            
        }
        return tornada;
    }
}