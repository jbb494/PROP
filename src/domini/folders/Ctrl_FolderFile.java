package domini.folders;

import domini.estadistica.Estadistica;
import domini.utils.FileNames;
import domini.algorithm.Ctrl_Algorithm;
import global.global;
import persistencia.Utils.FolderOrFile;
import persistencia.browser.Ctrl_Browser;
import persistencia.input.Ctrl_Input;
import persistencia.input.Ctrl_Input_Encoded;
import persistencia.output.Ctrl_Output_Encoded;

import java.io.IOException;

/**
 * @class Ctrl_FolderFile
 * @brief Classe Ctrl_FolderFile
 * Tota la comunicació entre la capa de presentació i la capa de domini relativa a comprimir i descomprimir 
 * passa perquè les classes de la capa de presentació instanciïn la classe Ctrl_FolderFile.
 * 
 * @author Joan Lapeyra
 */
public class Ctrl_FolderFile {
    /*
        Una carpeta comprimida (de n fills) té la seguent estructura:

         1B 4B                                             
        +--+---+------------+--------+---------+------------+--------+
        |F0| n | nom fill 1 | fill 1 |   ...   | nom fill n | fill n |
        +--+---+------------+--------+---------+------------+--------+

        "nom fill *" és el nom del fill * sense extensió
        "fill *" pot ser una carpeta comprimida o un fitxer comprimit.

        Un fitxer comprimit te la següent estructura.

         1B              8B         size B
        +--+----------+------+-------------------+
        |F1| extensio | size |       DATA        |
        +--+----------+------+-------------------+

        La mida de tot element de l'estrura descrita (nom fill 1, fill 1, DATA, etc)  
        és un nombre enter de bytes.

        "n" i "size" estan escrits en big endian.

        La responsabilitat de llegir i escriure DATA és delegada a Ctrl_Algorithm.

        La responsabilitat de llegir i escriure "nom fill *", "extensio" i "mida" és 
        delegada a Ctrl_Input_Encoded i Ctrl_Output_Encoded, respectivament

    */


    /**
     * @brief Constructor de la classe
     * @param path_input es el path al fitxer o directori a comprimir o descomprimir
     */
    public Ctrl_FolderFile(String path_input) {
        Ctrl_Browser brow = new Ctrl_Browser(path_input);
        if (!brow.exists()) throw new IllegalArgumentException(path_input+" no existeix");


        this.path_in = brow.getAbsolutePath();
    }

    private String path_in;

    private String get_outPathPrefix() {
        return FileNames.getPrefix(path_in);
    }
    

    /**
     * @fn public String EncodeManualFolder(String text_method, double img_quality)
     * @brief Comprimeix una carpeta
     * @param text_method és l'algoritme LZ (lz78, lzw, lzss), emprat per comprimir els fitxers txt
     * de la carpeta.
     * @param img_quality és un número del 0 al 100 que representa la qualitat de la compressió JPEG 
     * amb què es compimiran les imatges ppm de la carpeta.
     * Com més gran sigui menys informació es perdrà però més ocuparà el fitxer.
     * @return el path de sortida
     */
    public String EncodeManualFolder(String text_method, double img_quality) {
        if (!text_method.toLowerCase().equals("lz78") && !text_method.toLowerCase().equals("lzss") && !text_method.toLowerCase().equals("lzw"))
            throw new IllegalArgumentException("text_method ha de ser lz78, lzss o lzw");
        if (img_quality < 0 || img_quality > 100)
            throw new IllegalArgumentException("img_quality ha de ser un real entre 0 i 100 incolsos");

        if (img_quality > 0 && img_quality < 1)
            throw new IllegalArgumentException("N'estas segur? Llegeix l'especificació, rata.");

        this.auto = false;
        this.text_method = text_method.toLowerCase();
        this.img_quality = img_quality;
        

        return Encode();          
        
    }
    
    /**
     * @fn public String EncodeManualText(String text_method)
     * @brief Comprimeix un fitxer de text
     * @param text_method és l'algoritme LZ (lz78, lzw, lzss), emprat per comprimir el fitxer.
     * @return el path de sortida
     */
    public String EncodeManualText(String text_method) {
        if (!text_method.toLowerCase().equals("lz78") && !text_method.toLowerCase().equals("lzss") && !text_method.toLowerCase().equals("lzw"))
            throw new IllegalArgumentException("text_method ha de ser lz78, lzss o lzw");

        this.auto = false;
        this.text_method = text_method.toLowerCase();
        this.img_quality = null; //valor invàlid

        return Encode();
    }
    
    /**
     * @fn public String EncodeManualImg(double img_quality)
     * @brief Comprimeix una imatge ppm.
     * @param img_quality és un número del 0 al 100 que representa la qualitat de la compressió JPEG 
     * amb què es compimiran la imatge.
     * Com més gran sigui menys informació es perdrà però més ocuparà el fitxer.
     * @return el path de sortida
     */
    public String EncodeManualImg(double img_quality) {
        if (img_quality < 0 || img_quality > 100)
            throw new IllegalArgumentException("img_quality ha de ser un real entre 0 i 100 incolsos");

        if (img_quality > 0 && img_quality < 1)
            throw new IllegalArgumentException("N'estas segur? Llegeix l'especificació, rata.");

        this.auto = false;
        this.text_method = null;
        this.img_quality = img_quality;

        return Encode();
    }

    
    /**
     * @fn public String EncodeAuto()
     * @brief Comprimeix un fitxer o una carpeta en mode automàtic.
     * @note La responsabilitat de decidir amb quin algoritme comprimir els fitxers de text és de Ctrl_Algorithm
     * @return el path de sortida
     */
    public String EncodeAuto() {
        this.auto = true;
        this.img_quality = 50.0;

        return Encode();
    }

    private String text_method;
    private Double img_quality;
    private boolean auto;

    /**
     * @fn public String Encode()
     * @brief Comprimeix un fitxer o carpeta
     * @return el path de sortida
     */
    private String Encode() {
        String path_out = get_outPathPrefix() + ".jm";
        new Ctrl_Output_Encoded(path_out);

        Estadistica est = new Estadistica(true);
        Encode(path_in);
        est.work_done();

        return est.show_estadistica(path_in,path_out);
    }

    /**
     * @fn private void Encode(String path)
     * @brief Comprimeix un fitxer o carpeta
     * @param path Path al fitxer o carpeta
     */
    private void Encode(String path) { //path in
        Ctrl_Output_Encoded out = new Ctrl_Output_Encoded();
        Ctrl_Browser in = new Ctrl_Browser(path);
        if (in.isDirectory()) {
            out.add((byte)0xF0, 8);
            EncodeFolder(path);
        }
        else {
            out.add((byte)0xF1, 8);
            EncodeFile(path);
        }
    }

    /**
     * @fn private void EncodeFolder(String path)
     * @brief Comprimeix una carpeta
     * @param path Path a la carpeta
     */
    private void EncodeFolder(String path) {
        Ctrl_Output_Encoded out = new Ctrl_Output_Encoded();
        Ctrl_Browser in = new Ctrl_Browser(path);

        String[] children = in.list();
        int n = children.length;
        out.add(n);
        for (String name : children) {
            String name_prefix = FileNames.getPrefix(name);
            out.add(name_prefix);
            Encode(path + "/" + name);
        }
    }

    /**
     * @fn private void EncodeFile(String path)
     * @brief Comprimeix un fitxer
     * @param path Path a fitxer
     */
    private void EncodeFile(String path) {
        Ctrl_Output_Encoded out = new Ctrl_Output_Encoded();

        String extension = FileNames.getExtension(path);
        out.add(extension);
        out.startSubfile();
        Ctrl_Algorithm CAlg = new Ctrl_Algorithm();
        String method;
        if (auto) {
            method = CAlg.Auto_Encoder(path);
        }
        else if (extension.toLowerCase().equals("ppm")) {
            method = "jpeg";
        }
        else {
            method = text_method;
        }
        CAlg.Encode(path, method, (int)(double)img_quality);
        out.endSubfile();
    }

    /**
     * @fn public global.type getType(String Path)
     * @param Path Path al fixer o carpeta
     * @return Retorna una enumeration indicant el tipus de Path
     */
    public global.type getType(String Path){

        global.type file = FolderOrFile.getType(Path);
        if (file == global.type.comprimit) {
            if (isEncodedFile()) {
                if (getEncodedExtension().equals("ppm")) {
                    return global.type.imageComprimit;
                }else {
                    return global.type.textComprimit;
                }
            }else if(isEncodedFolder()) {
                return global.type.folderComprimit;
            }
        }
        return file;
    }
    
    

    
    
    /**
     * @fn public String Decode()
     * @brief Descomprimeix un fitxer o una carpeta.
     * @return el path de sortida
     */
    public String Decode() {
        new Ctrl_Input_Encoded(path_in);
        temporal_output = false;

        Estadistica est = new Estadistica(false);
        String path_out = Decode(null);
        est.work_done();

        return est.show_estadistica(path_in, path_out);
    }

    /**
     * @fn public String Decode()
     * @brief Descomprimeix un fitxer. Guarda el descomprimit en un fitxer temporal, 
     * que serà eliminat un cop el programa hagi acabat.
     * @return el path de sortida
     */
    public String DecodeTemp() {
        new Ctrl_Input_Encoded(path_in);
        temporal_output = true;
        return Decode(null);
    }


    private boolean temporal_output;

    

    /**
     * @brief Descomprimeix un fitxer o una carpeta. Continua llegint el comprimit que estava llegint.
     * @param path
     * si (path != null) El descomprimit es guardarà a path[.extensio]
     * si (path == null) El path de sortida es genera a partir del path d'entrada.
     *                   o serà un path a un fitxer temporal, si es dona el cas
     * @return el path de sortida
     */
    private String Decode(String path) { //path out
        Ctrl_Input_Encoded in = new Ctrl_Input_Encoded();

        byte magic_word = in.getByte();
        if (magic_word == (byte)0xF0)
            return DecodeFolder(path);
        else if (magic_word == (byte)0xF1) 
            return DecodeFile(path);
        else
            throw new IllegalArgumentException(path_in+" no es un comprimit.");
    }

    /**
     * @brief Descomprimeix una carpeta. Continua llegint el comprimit que estava llegint.
     * @param path
     * si (path != null) El descomprimit es guardarà a path
     * si (path == null) El path de sortida es genera a partir del path d'entrada
     * @return el path de sortida
     */
    private String DecodeFolder(String path) { //path out 
        Ctrl_Input_Encoded in = new Ctrl_Input_Encoded();
        if (path == null) {
            if (temporal_output)
                throw new IllegalArgumentException("No podem desar directoris temporals.");
            else
                path = get_outPathPrefix();
        }
        Ctrl_Browser out = new Ctrl_Browser(path);
        out.mkdir();
        int n = in.getInt();
        for (int i = 0; i < n; ++i) {
            String name = in.getWord();
            Decode(path + "/" + name);
        }
        return path;
    }

    /**
     * @brief Descomprimeix un fitxer o una carpeta. Continua llegint el comprimit que estava llegint.
     * @param prefix
     * si (prefix != null) El descomprimit es guardarà a prefix[.extensio]
     * si (prefix == null) El path de sortida es genera a partir del path d'entrada 
     *                     o serà un path a un fitxer temporal, si es dona el cas
     * @return el path de sortida
     */
    private String DecodeFile(String prefix) { //path out
        Ctrl_Input_Encoded in = new Ctrl_Input_Encoded();
        String extension = in.getWord();
        if (prefix == null) {
            if (temporal_output) {
                String aux = Ctrl_Browser.createTempFile(extension);
                prefix = FileNames.getPrefix(aux);
            }
            else
                prefix = get_outPathPrefix();
        }
        String path = FileNames.getPath(prefix, extension);

        in.startSubfile();
        Ctrl_Algorithm CAlg = new Ctrl_Algorithm();
        CAlg.Decode(path);
        in.endSubfile();

        return path;
    }
    











    /**
     * @fn public boolean isEncoded()
     * @return retorna true <=> el path que s'ha passat al constructor és a un comprimit.
     */
    public boolean isEncoded() {
        if (!FileNames.getExtension(path_in).equals("jm")) return false;
        Ctrl_Input_Encoded in = new Ctrl_Input_Encoded(path_in);
        byte magic_word = in.getByte();
        return (magic_word == (byte)0xF0) || (magic_word == (byte)0xF1);
    }

    /**
     * @fn public boolean isEncodedFolder()
     * @return retorna true <=> el path que s'ha passat al constructor és a una carpeta comprimida.
     */
    public boolean isEncodedFolder() {
        if (!FileNames.getExtension(path_in).equals("jm")) return false;
        Ctrl_Input_Encoded in = new Ctrl_Input_Encoded(path_in);
        byte magic_word = in.getByte();
        return (magic_word == (byte)0xF0);
    }

    /**
     * @fn public boolean isEncodedFolder()
     * @return retorna true <=> el path que s'ha passat al constructor és a un fitxer comprimit.
     */
    public boolean isEncodedFile() {
        if (!FileNames.getExtension(path_in).equals("jm")) return false;
        Ctrl_Input_Encoded in = new Ctrl_Input_Encoded(path_in);
        byte magic_word = in.getByte();
        return (magic_word == (byte)0xF1);
    }

    /**
     * @fn public String getEncodedExtension()
     * @return L'extensió que tenia un comprimit abans de ser comprimit.
     */
    public String getEncodedExtension() {
        if (!FileNames.getExtension(path_in).equals("jm")) throw new IllegalArgumentException("No es un comprimit.");
        Ctrl_Input_Encoded in = new Ctrl_Input_Encoded(path_in);
        byte magic_word = in.getByte();
        if (magic_word == (byte)0xF1) return in.getWord();
        if (magic_word == (byte)0xF0) return "";
        throw new IllegalArgumentException("No es un comprimit.");
    }

    public String getFile(String pathTemp) throws IOException {
        return Ctrl_Input.getFile(pathTemp);
    }
}
