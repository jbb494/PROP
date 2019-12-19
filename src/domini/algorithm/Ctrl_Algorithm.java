package domini.algorithm;


import persistencia.browser.Ctrl_Browser;
import persistencia.input.Ctrl_Input;

/**
 * @class Ctrl_Algorithm
 * @brief Classe de Ctrl_Algorithm
 * @file
 * @author Manel Aguilar
 */
public class Ctrl_Algorithm {

    /**
     * @brief Constructor de la classe
     */
    public Ctrl_Algorithm() {}

    

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
        else{  
            Ctrl_Browser aux = new Ctrl_Browser(Path);
            if (aux.length() < 1*Math.pow(10,6))
                return "lzss";
            return "lzw";
        }
            //else throw new IllegalArgumentException("Format no reconegut");
    }

    /**
     * @fn public void Decode(String path_out)
     * @brief Llegeix els dos bits de la metadata que indiquen amb quin algoritme descomprimir
     * i descomprimeix amb tal algorisme
     * @note Segueix llegint del fitxer d'on s'estava llegint.
     * @param path_out path on es guarda el descomrpimit.
     */
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
    
    /**
     * @brief Comprimeix un fitxer.
     * @note Escriu el comprimit al fitxer on s'estava escrivint.
     * @param path_in Fitxer a comrpimir.
     * @param method Algorisme amb el qual descomprimr.
     * @param img_quality Valor entre 0 i 100 que representa la qualitat de descompressió de
     * l'algorisme JPEG. Aquest valor només es farà servir si method = "jpeg"
     */
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
        
        Ctrl_Input.initialize(path_in);
        alg.Compressor();
    }
}
