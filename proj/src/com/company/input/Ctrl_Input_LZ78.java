/**
 * @class Ctrl_Input_LZ78
 * @brief Acces a un arxiu comprimit amb LZ78
 * @author Joan Bellavista Bartroli
 */

package com.company.input;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import com.company.utils.byteToConversion;
import com.company.utils.*;

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
    public Pair <Integer, Character> get()
    {
        Integer intAux = byteToConversion.byteToInteger(Input_class.getMoreBits(32));

        Character charAux = (char)Input_class.getBits(8);

        return new Pair <Integer, Character>(intAux, charAux);
    }
}