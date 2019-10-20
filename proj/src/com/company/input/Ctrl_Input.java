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
        ArrayList<Byte> arrayByte = Input_class.getIn();
        int punter = 0, pos = 0;
        boolean end = false;
        while(!end)
        {
            byte b = arrayByte.get(pos);
            byte aux = (byte)(b << (7-punter));
            aux = byteToConversion.shift_right_logic(aux, 7);
            punter++;
            if(punter > 7)
            {
                punter = 0;
                b = arrayByte.get(++pos);
            }
            if(aux == (byte)0) //char
            {
                aux = byteToConversion.shift_right_logic(b, punter);
                if(punter != 0)
                {
                    int mou = 8-punter;
                    byte aux2 = arrayByte.get(++pos);
                    aux2 = (byte)(aux2 << mou);
                    aux = (byte)(aux | aux2);
                }
                else pos++;
                IntorChar ioc = new IntorChar(true);
                ioc.SetChar(byteToConversion.byteToCharacter(aux));
                ret.add(ioc);
                //punter es queda a la mateixa posició
            }
            else //Punter
            {
                List<Byte> lb = new ArrayList<Byte>();
                byte aux2;
                aux = byteToConversion.shift_right_logic(b, punter);
                if(punter <= 3)
                {
                    aux = (byte)(aux << 3);
                    aux = byteToConversion.shift_right_logic(aux, 3);
                    if(punter == 3) b = arrayByte.get(++pos);
                }
                else
                {                    
                    b = arrayByte.get(++pos);
                    int restants = 5 - (8-punter);
                    aux2 = (byte)(b << (8-restants));
                    aux2 = byteToConversion.shift_right_logic(aux, 3);
                    aux = (byte)(aux | aux2);
                }
                lb.add(aux);
                punter += 5;
                if(punter > 7) punter -= 8;
                aux = byteToConversion.shift_right_logic(b, punter);
                b = arrayByte.get(++pos);
                aux2 = (byte)(b << (8-punter));
                aux = (byte)(aux | aux2);
                lb.add(aux);
                int despl = byteToConversion.byteToInteger(lb);
                IntorChar ioc = new IntorChar(false);
                ioc.SetDespl(despl);
                aux = byteToConversion.shift_right_logic(b, punter);
                if(punter <= 3)
                {
                    aux = (byte)(aux << 3);
                    aux = byteToConversion.shift_right_logic(aux, 3);
                    if(punter == 3) pos++;
                }
                else
                {
                    b = arrayByte.get(++pos);
                    int restants = 5 - (8-punter);
                    aux2 = (byte)(b << (8-restants));
                    aux2 = byteToConversion.shift_right_logic(aux, 3);
                    aux = (byte)(aux | aux2);
                }
                punter += 5;
                if(punter > 7) punter -= 8;
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

















