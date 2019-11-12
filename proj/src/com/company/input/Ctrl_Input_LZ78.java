package com.company.input;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import com.company.utils.byteToConversion;


public class Ctrl_Input_LZ78 extends Ctrl_Input {

    public Ctrl_Input_LZ78(String path)
    {
        super(path);
    }

    public AbstractMap.SimpleEntry <Integer, Character> get()
    {
        Integer intAux = byteToConversion.byteToInteger(Input_class.getMoreBits(32));

        Character charAux = (char)Input_class.getBits(8);

        return new AbstractMap.SimpleEntry <Integer, Character>(intAux, charAux);
    }
}