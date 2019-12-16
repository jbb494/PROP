package domini.algorithm;

import domini.utils.Pair;
import persistencia.input.Ctrl_Input;
import persistencia.output.Ctrl_Output;
import domini.utils.FileNames;

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
     * @brief 
     * CAL DEIXAR DE CRIDAR AQUESTA FUNCIÓ.
     * S'HAURÀ DE BORRAR QUAN NINGÚ LA CRIDI.
     * 
     * Realitzar la compressió d'un fitxer segons un algoritme concret
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
        if (ext.toLowerCase().equals("ppm")) 
            return "jpeg";
        else  /* (ext.toLowerCase().equals("txt"))  */
            return "lzw";
	    //else throw new IllegalArgumentException("Format no reconegut");
    }

    /**
     * @fn public String Auto_Decoder(String Path, String method)
     * @brief 
     * CAL DEIXAR DE CRIDAR AQUESTA FUNCIÓ.
     * S'HAURÀ DE BORRAR QUAN NINGÚ LA CRIDI.
     * 
     * Escull de manera automàtica quin descompressor emprar
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



    public void Decode(String path_out) {

        Ctrl_Input inP2 = new Ctrl_Input();
        int metadata_alg = inP2.getMetadata();

        Algorithm alg;
        if      (metadata_alg == 3)   alg = new JPEG(path_out, true);
        else if (metadata_alg == 2)   alg = new LZSS(path_out, true);
        else if (metadata_alg == 1)   alg = new LZW(path_out, true);
        else /* (metadata_alg == 0)*/ alg = new LZ78(path_out, true);
        
        alg.Decompressor();      
        alg.print();
    }

    public void Encode(String path_in, String method, Double img_quality) {
        
        if (method == null)
            throw new IllegalArgumentException("no s'ha especificat el metode de compressió");

        //System.out.println(method);

        Algorithm alg;
        if(method.toLowerCase().equals("lzss")) {
            alg = new LZSS(false);
        }
        else if(method.toLowerCase().equals("lzw")) {
            alg = new LZW(false);
        }
        else if(method.toLowerCase().equals("lz78")) {
            alg = new LZ78(false);
        }
        else if(method.toLowerCase().equals("jpeg")) {
            alg = new JPEG(false);
            if (img_quality == null) 
                throw new IllegalArgumentException("no s'ha especificat la qualitat de compressió de la imatge");
            ((JPEG)alg).resetQuality((int)(double)img_quality);
        }
        else {
            throw new IllegalArgumentException(method+" no es correspon a cap dels algorismes.");
        }

        /*
        Ctrl_Output out = new Ctrl_Output();
        out.add(0, 6);
        for (byte i = 0; i < 10; ++i) {
            out.add(0x30 + i, 8);
        }
        if (0<1) return;
        */

        
        Ctrl_Input.initialize(path_in);
        alg.Compressor();
    }
}
