package com.company.input;


import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Map;

import com.company.input.Input;


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
       
        String outputString = "";
        if (Extensio.equals("txt"))
        {
            for(int i = 0; i<arrayByte.size(); i++)
            {
                char a = (char)arrayByte.get(i).byteValue();
                outputString += a ;
            }
            return outputString;
        }
        return "";
    }

    public ArrayList< Map.Entry <Integer, Character> > getLZ78()
    {
        ArrayList< Map.Entry <Integer, Character> > ret = 
        new ArrayList< Map.Entry <Integer, Character> > (0);

        ArrayList<Byte> arrayByte =  Input_class.getIn();

        Map.Entry <Integer, Character> entryAux;

        for(int i = 0; i<arrayByte.size(); i++)
        {
            if (i % 2 == 0){
                //getInt from ArrayList<Byte>
                //entryAux.setKey();
            }else{
                char a = (char)arrayByte.get(i).byteValue();
                //ret.add() ;
            }
        }

        return ret;
    }

}
