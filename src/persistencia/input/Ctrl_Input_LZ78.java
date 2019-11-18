package persistencia.input;


import domini.utils.byteToConversion;
import domini.utils.*;

/**
 * @class Ctrl_Input_LZ78
 * @brief Acces a un arxiu comprimit amb LZ78
 * 
 * Aquesta classe és un fill de Ctrl_Input. Té una funció get que retorna l'entrada en 
 * el format que el algoritme LZ78 necessita. (parells de integer i byte).
 * 
 * @author Joan Bellavista Bartroli
 */
public class Ctrl_Input_LZ78 extends Ctrl_Input {

    /**
     * @brief Constructor de la classe. Crida al constructor pare Ctrl_Input. a més interepreta la metadata.
     * @param path Path de l'arxiu comprimit
     */
    public Ctrl_Input_LZ78(String path)
    {
        super(path);
        getMetadata();
    }

    /**
     * @fn public Pair<Integer, Character> get()
     * @return Primer parell enter-byte no llegit de l'arxiu comprimit amb LZ78
     */
    public Pair <Integer, Byte> get() {
        Integer intAux = byteToConversion.byteToInteger(Input_class.getMoreBits(32));
        Byte byteAux = Input_class.getBits(8);
        return new Pair <Integer, Byte>(intAux, byteAux);
    }
}