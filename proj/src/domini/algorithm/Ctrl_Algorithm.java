package domini.algorithm;

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
     * @brief Realitzar? la compressi? d'un fitxer segons un algoritme concret
     * @param Path Path de l'arxiu a comprimir
     * @param method Algoritme a emprar
     * @return Informacio sobre la compressio
     */
    public String Choose_Encoder(String Path, String method) {
        int i = Path.lastIndexOf(".");
        String prefix = Path.substring(0, i+1);
        if(method.toLowerCase().equals("lzss"))
        {
            LZSS alg = new LZSS(prefix + method, false);
            Ctrl_Input_Text in = new Ctrl_Input_Text(Path);
            alg.Compressor(in);
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else if(method.toLowerCase().equals("lzw"))
        {
            LZW alg = new LZW(prefix + method, false);
            Ctrl_Input_Text in = new Ctrl_Input_Text(Path);
            alg.compression(in);
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else if(method.toLowerCase().equals("lz78"))
        {
            LZ78 alg = new LZ78(prefix + method, false);
            Ctrl_Input_Text in = new Ctrl_Input_Text(Path);
            alg.Compressor(in);
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else if(method.toLowerCase().equals("jpeg"))
        {
            JPEG alg = new JPEG(prefix + method, false);
            Ctrl_Input_Img in = new Ctrl_Input_Img(Path);
            alg.Compressor(in);
            Ctrl_Output outp = alg.print();
            outp.print();

        }
        return "Compressió de " + Path;
    }

    /**
     * @fn public String Auto_Encoder(String Path)
     * @brief Determina de manera autom?tica quin compressor utilitzar
     * @param Path Path de l'arxiu a comprimir
     * @return Nom del m?tode a emprar
     */
    public String Auto_Encoder(String Path)
    {

        return "";
    }

    /**
     * @fn public String Auto_Decoder(String Path, String method)
     * @brief Escull de manera autom?tica quin descompressor emprar
     * @param Path Path de l'arxiu a descomprimir
     * @param method Descompressor a emprar
     * @return Informaci? sobre la descompressio
     */
    public String Auto_Decoder(String Path, String method)
    {
        int i = Path.lastIndexOf(".");
        String prefix = Path.substring(0, i+1);
        if(method.toLowerCase().equals("jpeg"))
        {
            JPEG alg = new JPEG(prefix + "ppm", true);  
            Ctrl_Input_JPEG inP2 = new Ctrl_Input_JPEG(Path);
            alg.Decompressor(inP2);      
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else
        {
            Ctrl_Input inP = new Ctrl_Input(Path);
            String decide = inP.getAlg();
            if(decide.toLowerCase().equals("lzss"))
            {               
                LZSS alg = new LZSS(prefix + "txt", true);  
                Ctrl_Input_LZSS inP2 = new Ctrl_Input_LZSS(Path);
                alg.Decompressor(inP2);      
                //alg.Decompressor((Ctrl_Input_LZSS)inP);
                Ctrl_Output outp = alg.print();
                outp.print();
            }
            else if(decide.toLowerCase().equals("lzw"))
            {
                LZW alg = new LZW(prefix + "txt", true);  
                Ctrl_Input_LZW inP2 = new Ctrl_Input_LZW(Path);
                alg.decompression(inP2);
                Ctrl_Output outp = alg.print();
                outp.print();
            }
            else if(decide == "lz78")
            {
                LZ78 alg = new LZ78(prefix + "txt", true);  
                Ctrl_Input_LZ78 inP2 = new Ctrl_Input_LZ78(Path);
                alg.Decompressor(inP2);      
                Ctrl_Output outp = alg.print();
                outp.print();
            }
            else return decide;
        }
        return "Descompressió de " + Path;
    }
}
