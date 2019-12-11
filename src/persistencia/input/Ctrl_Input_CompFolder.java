package persistencia.input;

import java.util.ArrayList;
import domini.utils.byteToConversion;
import persistencia.input.Input;

/**
 * @class Ctrl_Input_CompFolder
 * @brief Classe Ctrl_Input_CompFolder
 * S'encarrega de controlar la lectura de carpetes comrpimides.
 */
public class Ctrl_Input_CompFolder extends Ctrl_Input {

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu input
     */
    public Ctrl_Input_CompFolder(String path) {
        super(path);
    }

    /**
     * @brief Constructor de la classe
     * @note Continua llegint l'arxiu que s'estava llegint
     */
    public Ctrl_Input_CompFolder() {
        super();
    }

    

    /**
     * @fn public void startSubfile(int length)
     * @brief comença a llegir un subfitxer
     * @note Un subfitxer és un conjunt de bytes d'una carpeta comprimida que representa un fitxer comprimit.
     * Assumeix que els 4 pròxims bytes son la llargada del subfitxer.
     */
    public void startSubfile() {
        ArrayList<Byte> al = Input.getInstance().getMoreBits(32);
        int length = byteToConversion.byteToInteger(al);
        Input.getInstance().startFragment(length);
    }

    /**
     * @fn public void endSubfile()
     * @brief comença a llegir un subfitxer
     * @note Un subfitxer és un conjunt de bytes d'una carpeta comprimida que representa un fitxer comprimit.
     */
    public void endSubfile() {
        Input.getInstance().ignoreTheRestOfTheByte();
        Input.getInstance().startFragment(Integer.MAX_VALUE);
    }

    public String getWord() {
        String str = "";
        byte b;
        while (true) {
            b = Input.getInstance().getBits(8);
            if (b == 0) break;
            str += (char)b;
        } 
        return str;
    }

}