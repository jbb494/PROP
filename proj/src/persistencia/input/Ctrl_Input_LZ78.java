/**
 * @class Ctrl_Input_LZ78
 * @brief Acces a un arxiu comprimit amb LZ78
 * @author Joan Bellavista Bartroli
 */

package persistencia.input;


import domini.utils.byteToConversion;
import domini.utils.*;

public class Ctrl_Input_LZ78 extends Ctrl_Input {

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu comprimit
     */
    public Ctrl_Input_LZ78(String path)
    {
        super(path);
        getMetadata();
    }

    /**
     * @fn public Pair<Integer, Character> get()
     * @return Primer enter no llegit de l'arxiu comprimit amb LZ78
     */
    public Pair <Integer, Byte> get()
    {
        Integer intAux = byteToConversion.byteToInteger(Input_class.getMoreBits(32));
        Byte byteAux = Input_class.getBits(8);
        return new Pair <Integer, Byte>(intAux, byteAux);
    }
}