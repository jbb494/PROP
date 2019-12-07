package domini.algorithm;

import domini.utils.Pair;
import persistencia.input.Ctrl_Input;
import persistencia.input.Ctrl_Input_Img;
import persistencia.input.Ctrl_Input_LZSS;
import persistencia.input.Ctrl_Input_LZW;
import persistencia.input.Ctrl_Input_LZ78;
import persistencia.input.Ctrl_Input_Text;
import persistencia.input.Ctrl_Input_JPEG;
import persistencia.output.Ctrl_Output;

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
        int i = Path.lastIndexOf(".");
        String new_path = Path.substring(0, i+1) + "jm";

        if(method.toLowerCase().equals("lzss"))
        {
            LZSS alg = new LZSS(new_path, false);
            Ctrl_Input_Text in = new Ctrl_Input_Text(Path);
            alg.Compressor(in);
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else if(method.toLowerCase().equals("lzw"))
        {
            LZW alg = new LZW(new_path, false);
            Ctrl_Input_Text in = new Ctrl_Input_Text(Path);
            alg.compression(in);
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else if(method.toLowerCase().equals("lz78"))
        {
            LZ78 alg = new LZ78(new_path, false);
            Ctrl_Input_Text in = new Ctrl_Input_Text(Path);
            alg.Compressor(in);
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else if(method.toLowerCase().equals("jpeg"))
        {
            JPEG alg = new JPEG(new_path, false);
            Ctrl_Input_Img in = new Ctrl_Input_Img(Path);
            alg.Compressor(in);
            Ctrl_Output outp = alg.print();
            outp.print();

        }
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
     * @param method Descompressor a emprar
     * @return Informació sobre la descompressió
     */
    public Pair<String,String> Auto_Decoder(String Path)
    {
        String type;
        int i = Path.lastIndexOf(".");
        String prefix = Path.substring(0, i+1);

        Ctrl_Input inP2 = new Ctrl_Input(Path);
        int metadata = inP2.getMetadata();

        if(metadata == 3)
        {
            JPEG alg = new JPEG(prefix + "ppm", true);  
            inP2 = new Ctrl_Input_JPEG();
            alg.Decompressor((Ctrl_Input_JPEG)inP2);      
            Ctrl_Output outp = alg.print();
            outp.print();
        
            type = "ppm";
        }
        else {
            type = "txt";
            if(metadata == 2) 
            {               
                LZSS alg = new LZSS(prefix + "txt", true);  
                inP2 = new Ctrl_Input_LZSS();
                alg.Decompressor((Ctrl_Input_LZSS)inP2);    
                Ctrl_Output outp = alg.print();
                outp.print();
            }
            else if(metadata == 1)
            {
                LZW alg = new LZW(prefix + "txt", true);  
                inP2 = new Ctrl_Input_LZW();
                alg.decompression((Ctrl_Input_LZW)inP2);
                Ctrl_Output outp = alg.print();
                outp.print();
            }
            else if(metadata == 0) 
            {
                LZ78 alg = new LZ78(prefix + "txt", true);  
                inP2 = new Ctrl_Input_LZ78();
                alg.Decompressor((Ctrl_Input_LZ78)inP2);      
                Ctrl_Output outp = alg.print();
                outp.print();
            }
        }
        return new Pair<String,String>(type, "Descompressió de " + Path);
    }
}
