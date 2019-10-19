package com.company.input;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import com.company.utils.byteToConversion;


public class Ctrl_InputLZ78 extends Ctrl_Input {

    public Ctrl_InputLZ78(String path)
    {
        super(path);
    }

    public ArrayList< AbstractMap.SimpleEntry <Integer, Character> > get()
    {
        ArrayList< AbstractMap.SimpleEntry <Integer, Character> > ret = 
        new ArrayList< AbstractMap.SimpleEntry <Integer, Character> > (0);

        List<Byte> arrayByte =  Input_class.getIn();

        Integer intAux = 0;

        Character charAux = '0';

        for(int i = 0; i<arrayByte.size();)
        {
            //System.out.println("arrayByte.get(" + i + ") = " + arrayByte.get(i));
            
            List<Byte> arrayAux = arrayByte.subList(i, i+4);
            i+=4;
            intAux = byteToConversion.byteToInteger(arrayAux);
            

            charAux = byteToConversion.byteToCharacter(arrayByte.get(i));
            i++;
            ret.add(new AbstractMap.SimpleEntry<Integer, Character>(intAux, charAux));
        }

        return ret;
    }
}