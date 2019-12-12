package domini.folders;

/**
 * @class Ctrl_FolderFile
 * @brief Classe Ctrl_FolderFile
 * Tota la comunicació entre la capa de presentació i la capa de domini relativa a comprimir i descomprimir 
 * passa perquè les classes de la capa de presentació instanciïn la classe Ctrl_FolderFile.
 * 
 * @author Joan Lapeyra
 */
public class Ctrl_FolderFile {

    /**
     * @brief Constructor de la classe
     * @param path_input es el path al fitxer o directori a comprimir o descomprimir
     */
    public Ctrl_FolderFile(String path_input) {}

    /**
     * @fn public void EncodeManualFolder(String text_method, double img_quality)
     * @brief Comprimeix una carpeta
     * @param text_method és l'algoritme LZ (lz78, lzw, lzss), emprat per comprimir els fitxers txt
     * de la carpeta.
     * @param img_quality és un número del 0 al 100 que representa la qualitat de la compressió JPEG 
     * amb què es compimiran les imatges ppm de la carpeta.
     */
    public void EncodeManualFolder(String text_method, double img_quality) {
        if (!text_method.toLowerCase().equals("lz78") && !text_method.toLowerCase().equals("lzss") && !text_method.toLowerCase().equals("lzw"))
            throw new IllegalArgumentException("text_method ha de ser lz78, lzss o lzw");
        if (img_quality < 0 || img_quality > 100)
            throw new IllegalArgumentException("img_quality ha de ser un real entre 0 i 100 incolsos");
    }
    
    /**
     * @fn public void EncodeManualText(String text_method)
     * @brief Comprimeix un fitxer de text
     * @param text_method és l'algoritme LZ (lz78, lzw, lzss), emprat per comprimir el fitxer.
     */
    public void EncodeManualText(String text_method) {
        if (!text_method.toLowerCase().equals("lz78") && !text_method.toLowerCase().equals("lzss") && !text_method.toLowerCase().equals("lzw"))
            throw new IllegalArgumentException("text_method ha de ser lz78, lzss o lzw");
    }
    
    /**
     * @fn public void EncodeManualImg(double img_quality)
     * @brief Comprimeix una imatge ppm.
     * @param img_quality és un número del 0 al 100 que representa la qualitat de la compressió JPEG 
     * amb què es compimiran la imatge.
     */
    public void EncodeManualImg(double img_quality) {
        if (img_quality < 0 || img_quality > 100)
            throw new IllegalArgumentException("img_quality ha de ser un real entre 0 i 100 incolsos");
    }
    
    /**
     * @fn public void EncodeAuto()
     * @brief Comprimeix un fitxer txt, un fitxer ppm o una carpeta en mode automàtic.
     * @note La responsabilitat de decidir amb quin algoritme comprimir els fitxer txt és de Ctrl_Algorithm
     */
    public void EncodeAuto() {}
    
    /**
     * @fn public void EncodeAuto()
     * @brief Descomprimeix un fitxer txt, un fitxer ppm o una carpeta.
     */
    public void Decode() {}


    /**
     * @fn public boolean isEncoded()
     * @return retorna true <=> el path que s'ha passat al constructor és a un comprimit.
     */
    public boolean isEncoded() {
        return true; //només perquè compili
    }

    /**
     * @fn public boolean isEncoded()
     * @return retorna true <=> el path que s'ha passat al constructor és a una carpeta comprimida.
     */
    public boolean isEncodedFolder() {
        return true; //només perquè compili
    }

    /**
     * @fn public boolean isEncoded()
     * @return retorna true <=> el path que s'ha passat al constructor és a un fitxer comprimit.
     */
    public boolean isEncodedFile() {
        return true; //només perquè compili
    }

}
