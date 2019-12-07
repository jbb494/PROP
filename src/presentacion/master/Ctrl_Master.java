package presentacion.master;
import domini.algorithm.Ctrl_Algorithm;
import domini.estadistica.Estadistica;
import presentacion.form.mainForm;

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
        mainForm frame = new mainForm();
        frame.inicialitza_panel();

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
            System.out.println("Vol fer-ho de manera manual o automàtica (en aquest cas escrigui auto)?");
            String aux = System.console().readLine().toLowerCase();
            
            if (aux.equals("manual")) Function = 3; // Compressió manual
            else if (aux.equals("auto")) Function = 1; // Compressió automàtica
	        else throw new IllegalArgumentException("Entrada incorrecte, escrigui \"manual\" o \"auto\"");
        }
        else if (input.equals("descomprimir")) Function = 2; // Descompressió
	    else throw new IllegalArgumentException("Entrada incorrecte, escrigui \"comprimir\" o \"descomprimir\"");

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
        if(Function == 2)
        {
            String extension = "";
            int i = Path.lastIndexOf(".");
            extension = Path.substring(i+1);
            tornada = CAlg.Auto_Decoder(Path, extension);
        }
        else
        {
            String extension = "";
            int i = Path.lastIndexOf(".");
            extension = Path.substring(i+1);
            if(extension == "ppm") tornada = CAlg.Choose_Encoder(Path, "ppm");
            else
            {	
		        String mode = "";
		        if (Function == 3)
		        {
                    System.out.println("Amb quin algorisme vol comprimir el fitxer: LZ78, LZW, LZSS o JPEG?");
                    System.out.println("(escriviu-lo en minúsucules)");
                    mode = System.console().readLine().toLowerCase();
		        }
		        else {
			        mode = CAlg.Auto_Encoder(Path);
		        }
                Estadistica est = new Estadistica();
                tornada = CAlg.Choose_Encoder(Path, mode);
                //Generació de les estadístiques
                String out_p = Path.substring(0, i+1) + mode;
                est.show_estadistica(Path,out_p);
            }            
        }
        return tornada;
    }

    public void CompilaManual()
    {

    }
}
