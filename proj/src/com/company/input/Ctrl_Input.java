package com.company.input;

import java.util.regex.Pattern;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import com.company.input.Input;
import com.company.utils.byteToConversion;


public class Ctrl_Input {

    Input Input_class;

    String Extensio;

    public Ctrl_Input(String path)
    {
        Input_class = new Input(path);
       
        Pattern p = Pattern.compile("\\.");

        String spl[] = p.split(path);

        this.Extensio = spl[1];
    }
    
    public String getText()
    {
        ArrayList<Byte> arrayByte =  Input_class.getIn();
       
        System.out.println("Imprimeixo amb Bytes: \n" + arrayByte);

        String outputString = "";

        for(int i = 0; i<arrayByte.size(); i++)
        {
            char a = (char)arrayByte.get(i).byteValue();
            outputString += a ;
        }
        return outputString;

    }

    public ArrayList< AbstractMap.SimpleEntry <Integer, Character> > getLZ78()
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

    public ArrayList<Integer> getLZW() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ArrayList<Byte> arrayByte = Input_class.getIn();

        for (int i = 0; i < arrayByte.size(); i += 4) {
            
            List<Byte> aux = arrayByte.subList(i, i+4);
            
            ret.add(byteToConversion.byteToInteger(aux));
        
        }

        return ret;
    }

}

















