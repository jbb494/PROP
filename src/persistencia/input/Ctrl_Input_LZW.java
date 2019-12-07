package persistencia.input;

import java.util.ArrayList;
import java.util.List;
import domini.utils.byteToConversion;

/**
 * @class Ctrl_Input_LZW
 * @brief Acces a un arxiu comprimit amb LZW
 * @author Miguel Paracuellos Ocana
 */
public class Ctrl_Input_LZW extends Ctrl_Input {

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu comprimit
     */
    public Ctrl_Input_LZW(String path) {
        super(path);
        getMetadata();
    }

    /**
       * @brief El constructor on es crida a la classe pare.
       * @note Continua llegint el fitxer que s'estava llegint. Assumeix que la metadata ja ha estat llegida.
    */
    public Ctrl_Input_LZW() {
        super();
    }

    /**
     * @fn public Integer get()
     * @return Primer enter no llegit de l'arxiu comprimit amb LZW
     */
    public Integer get() {
        //ATM los valores codificados son de 4 bytes, en un futuro serán de tamaño variable
        //y por lo tanto tendremos que tratarlos de la manera optima.
        
        List<Byte> arrayByte = new ArrayList<>();

        for (int i = 0; i < 4; ++i) 
            arrayByte.add(Input.getInstance().getBits(8));
        
        return byteToConversion.byteToInteger(arrayByte);
    }
}