package persistencia.output;

import persistencia.output.Output;
import persistencia.output.RandomAccessOutput;;

/**
 * @class Ctrl_Output_Encoded
 * @brief Classe Ctrl_Output_Encoded
 * @author Joan Lapeyra Amat
 */
public class Ctrl_Output_Encoded extends Ctrl_Output {
    private long start;


    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu que comença a escriure
     */
    public Ctrl_Output_Encoded(String path) {
        super(path);
    }

    /**
     * @brief Constructor de la classe
     * @note Continua escrivint al fitxer al qual s'estava escrivint.
     */
    public Ctrl_Output_Encoded() {
        super();
    }

    /**
     * @fn public void add(String s)
     * @brief Afegeix un string precerit per un byte amb la seva mida.
     * @param s String a afegir
     */
    public void add(String s) {
        byte[] bytearray = s.getBytes();
        byte len = (byte)bytearray.length;
        add(len,8);
        for (byte b : bytearray) 
           add(b,8);
    }

    /**
     * @fn public void startSubfile(int length)
     * @brief Comença a escriure un subfitxer
     * Un subfitxer és un conjunt de bytes d'una carpeta comprimida que representa un fitxer comprimit.
     */
    public void startSubfile() {
        for (int i = 0; i < 8; ++i)
            Output.getInstance().add((byte)0, 8);
        start = Output.getInstance().getLength();
    }

    /**
     * @fn public void endSubfile()
     * @brief Acaba d'escriure el subfixer que s'estava escrivint 
     * i n'escriu la llargada als bytes que s'han reservat per la llargada.
     * Un subfitxer és un conjunt de bytes d'una carpeta comprimida que representa un fitxer comprimit.
     */
    public void endSubfile() {
        Output.getInstance().print();
        long len = Output.getInstance().getLength() - start;
        RandomAccessOutput.writeLong(Output.getInstance().getPath(), start - 8, len);
    }

}