package domini.algorithm;

import domini.utils.Pair;
import persistencia.input.Ctrl_Input;

import domini.folders.FileNames;

/**
 * @class Ctrl_Algorithm
 * @brief Classe de Ctrl_Algorithm
 * @author Manel Aguilar
 */
public class Ctrl_Algorithm {

    /**
     * @brief Constructor de la classe
     */
    public Ctrl_Algorithm() {}

    /**
     * @fn public String Choose_Encoder(String Path, String method)
     * @brief Realitzar la compressió d'un fitxer segons un algoritme concret
     * @param Path Path de l'arxiu a comprimir
     * @param method Algoritme a emprar
     * @return Informacio sobre la compressio
     */
    public String Choose_Encoder(String Path, String method) {
        String new_path = FileNames.getPrefix(Path) + ".jm";

        Algorithm alg;
        if(method.toLowerCase().equals("lzss")) {
            alg = new LZSS(new_path, false);
        }
        else if(method.toLowerCase().equals("lzw")) {
            alg = new LZW(new_path, false);
        }
        else if(method.toLowerCase().equals("lz78")) {
            alg = new LZ78(new_path, false);
        }
        else if(method.toLowerCase().equals("jpeg")) {
            alg = new JPEG(new_path, false);
        }
        else {
            throw new IllegalArgumentException(method+" no es correspon a cap dels algorismes.");
        }
        Ctrl_Input.initialize(Path);
        alg.Compressor();
        alg.print();

        return "Compressió de " + Path;
    }

    /**
     * @fn public String Auto_Encoder(String Path)
     * @brief Determina de manera automàtica quin compressor utilitzar
     * @param Path Path de l'arxiu a comprimir
     * @return Nom del mètode a emprar
     */
    public String Auto_Encoder(String Path)
    {
	    int i = Path.lastIndexOf(".");
        String ext = Path.substring(i+1);
        if (ext.toLowerCase().equals("txt")) 
            return "lzw";
        else if (ext.toLowerCase().equals("ppm")) 
            return "jpeg";
	    else throw new IllegalArgumentException("Format no reconegut");
    }

    /**
     * @fn public String Auto_Decoder(String Path, String method)
     * @brief Escull de manera automàtica quin descompressor emprar
     * @param Path Path de l'arxiu a descomprimir
     * @return Informació sobre la descompressió
     */
    public Pair<String,String> Auto_Decoder(String Path)
    {
        String type;
        int i = Path.lastIndexOf(".");
        String prefix = Path.substring(0, i+1);

        Ctrl_Input inP2 = new Ctrl_Input(Path);
        int metadata = inP2.getMetadata();

        Algorithm alg;
        if(metadata == 3) {
            alg = new JPEG(prefix + "ppm", true);
        
            type = "ppm";
        }
        else {
            type = "txt";
            if(metadata == 2)  {               
                alg = new LZSS(prefix + "txt", true);
            }
            else if(metadata == 1) {
                alg = new LZW(prefix + "txt", true);  
            }
            else /*if(metadata == 0)*/ {
                alg = new LZ78(prefix + "txt", true);  
            }
        }

        alg.Decompressor();      
        alg.print();

        return new Pair<String,String>(type, "Descompressió de " + Path);
    }
}
