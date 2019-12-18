package persistencia.input;

import java.util.ArrayList;
import domini.utils.byteToConversion;
import persistencia.input.Input;

/**
 * @class Ctrl_Input_Encoded
 * @brief Classe Ctrl_Input_Encoded
 */
public class Ctrl_Input_Encoded extends Ctrl_Input {

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu input
     */
    public Ctrl_Input_Encoded(String path) {
        super(path);
    }

    /**
     * @brief Constructor de la classe
     * @note Continua llegint l'arxiu que s'estava llegint
     */
    public Ctrl_Input_Encoded() {
        super();
    }

    

    /**
     * @fn public void startSubfile()
     * @brief Comença a llegir un subfitxer
     * Un subfitxer és un conjunt de bytes d'una carpeta comprimida que representa un fitxer comprimit.
     * Assumeix que els 8 pròxims bytes son la llargada del subfitxer.
     */
    public void startSubfile() {
        ArrayList<Byte> al = Input.getInstance().getMoreBits(64);
        long length = byteToConversion.byteToLong(al);
        Input.getInstance().startFragment(length);
    }

    /**
     * @fn public void endSubfile()
     * @brief Acaba de llegir el subfixer que s'estava llegint.
     * Un subfitxer és un conjunt de bytes d'una carpeta comprimida que representa un fitxer comprimit.
     */
    public void endSubfile() {
        while (!finished()) getByte();
        Input.getInstance().ignoreTheRestOfTheByte();
        Input.getInstance().startFragment(Long.MAX_VALUE);
    }

    /**
     * @fn public String getWord()
     * @return Retorna l'String cridant al mètode getByte()
     */
    public String getWord() {
        
        int mida = (int)getByte();
        if (mida < 0) mida += 256;
        byte[] arr = new byte[mida];

        for (int i = 0; i < mida; ++i) {
            arr[i] = getByte();
        }

        return new String(arr);
    }

    /**
     * @fn public Byte getByte()
     * @return Retorna un byte del Input.
     */
    public Byte getByte() {
        return Input.getInstance().getBits(8);
    }

    /**
     * @fn public int getInt()
     * @return Retorna un int del Input.
     */
    public int getInt() { //big endian
        ArrayList<Byte> al = Input.getInstance().getMoreBits(32);
        return byteToConversion.byteToInteger(al);
    }

}