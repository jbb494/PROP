package com.company.input;

import java.util.ArrayList;
import java.util.List;

import com.company.utils.byteToConversion;

public class Ctrl_Input_LZW extends Ctrl_Input {

    public Ctrl_Input_LZW(String path) {
        super(path);
    }

    public Integer getLZW() {
        //ATM los valores codificados son de 4 bytes, en un futuro serán de tamaño variable
        //y por lo tanto tendremos que tratarlos de la manera optima.
        
        List<Byte> arrayByte = new ArrayList<>();

        for (int i = 0; i < 4; ++i) 
            arrayByte.add(Input_class.getBits(8));
        
        return byteToConversion.byteToInteger(arrayByte);
    }
    
    
}