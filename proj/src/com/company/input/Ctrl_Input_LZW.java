/**
 * @class Ctrl_Input_LZW
 * @brief Accés a un arxiu comprimit amb LZW
 * @author Miguel Paracuellos Ocaña
 */

package com.company.input;

import java.util.ArrayList;
import java.util.List;
import com.company.utils.byteToConversion;

public class Ctrl_Input_LZW extends Ctrl_Input {

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu comprimit
     */
    public Ctrl_Input_LZW(String path) {
        super(path);
    }

    /**
     * @fn public Integer get()
     * @return Primer enter no llegit de l'arxiu comprimit amb LZW
     */
    public Integer get() {
        //ATM los valores codificados son de 4 bytes, en un futuro serÃ¡n de tamaÃ±o variable
        //y por lo tanto tendremos que tratarlos de la manera optima.
        
        List<Byte> arrayByte = new ArrayList<>();

        for (int i = 0; i < 4; ++i) 
            arrayByte.add(Input_class.getBits(8));
        
        return byteToConversion.byteToInteger(arrayByte);
    }
    
    
}