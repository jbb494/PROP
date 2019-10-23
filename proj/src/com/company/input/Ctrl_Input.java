package com.company.input;

import java.util.regex.Pattern;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import com.company.input.Input;
import com.company.utils.byteToConversion;
import com.company.utils.IntorChar;
import java.io.*;

public class Ctrl_Input {

    //Attributes
    Input Input_class;

    String Extensio;


    //Constructor
    public Ctrl_Input(String path){
  
        Pattern p = Pattern.compile("\\.");

        String spl[] = p.split(path);

        this.Extensio = spl[1];
        
        if (Extensio.equals("ppm"))
            Input_class = new Input_Img(path);
        else
            Input_class = new Input_Text(path);
            
    }
    

    //Functions
    public String getExtension() {
        return Extensio;
    }


    public String getText()
    {
        ArrayList<Byte> arrayByte =  Input_class.getIn();
       
        //System.out.println("Imprimeixo amb Bytes: \n" + arrayByte);

        String outputString = "";

        for(int i = 0; i<arrayByte.size(); i++)
        {
            char a = (char)arrayByte.get(i).byteValue();
            outputString += a ;
        }
        return outputString;

    }

    public Byte[][][] getImg() {
        int h = Input_class.getHeight();
        int w = Input_class.getWidth();
        ArrayList<Byte> arrayByte =  Input_class.getIn();

        Byte[][][] ret = new Byte[h][w][3];
        
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                for (int k = 0; k < 3; ++k) {
                    ret[i][j][k] = arrayByte.get(k + 3*(j + w*i));
                }
            }
        }

        return ret;
    }

    public int get()
    {
        return 1;
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

    public ArrayList< IntorChar > getLZSS()
    {
        ArrayList<IntorChar> ret = new ArrayList<IntorChar>();
        boolean end = false;
        while(!end)
        {
            byte aux = Input_class.getBits(1);
            if(aux == (byte)0) //char
            {
                aux = Input_class.getBits(8);
                IntorChar ioc = new IntorChar(true);
                ioc.SetChar(byteToConversion.byteToCharacter(aux));
                ret.add(ioc);
            }
            else
            {
                List<Byte> lb = new ArrayList<Byte>();
                aux = Input_class.getBits(5);
                lb.add(aux);
                aux = Input_class.getBits(8);
                lb.add(aux);
                int despl = byteToConversion.byteToInteger(lb);
                IntorChar ioc = new IntorChar(false);
                ioc.SetDespl(despl);
                aux = Input_class.getBits(5);
                lb = new ArrayList<Byte>();
                lb.add(aux);
                int mida = byteToConversion.byteToInteger(lb) + 2;
                if(mida == 2) end = true;
                else
                {
                    ioc.SetMida(mida);
                    ret.add(ioc);
                }
            }
        }
        return ret;
    }
}

















