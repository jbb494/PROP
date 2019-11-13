package com.company.output;

import java.nio.ByteBuffer;

public class Ctrl_Output {
    
    //Attributes
    Output Output_Class;

    //Constructor
    public Ctrl_Output(String path, String method, boolean b) {
        Output_Class = new Output(path);
        
        if (!b) {
            if (method.toLowerCase().equals("lzss"))
                add((byte)2, 2);
            else if(method.toLowerCase().equals("lzw"))
                add((byte)1, 2);
            else if(method.toLowerCase().equals("lz78"))
                add((byte)0, 2);
        }
        /*{
            case "lz78":
                add((byte)0, 2);
                break;
            case "lzw":
                add((byte)1, 2);
                break;
            case "lzss":
                add((byte)2, 2);
                break;
        }*/
    }

    //Functions
    public void add(Byte b, Integer n_bits) {
        //System.out.println(b);
        Output_Class.add(b, n_bits);
    }


    public void add(String s) {
        byte[] bytearray = s.getBytes();
        for (byte b : bytearray) 
            Output_Class.add(b,8);
    }


    public void add(Character c) {
        //System.out.println(c);
        add(Character.toString(c));
    }


    public void add(Integer x) {
        ByteBuffer bb = ByteBuffer.allocate(4); 
        bb.putInt(x.intValue());
        for (byte b : bb.array()) 
            add(b,8);
    }


    public void add(Integer x, Integer mida){
        //System.out.println(x);
        ByteBuffer bb = ByteBuffer.allocate(4); 
        Integer midaAux = mida%8;
        Double daux = new Double(mida)/8;
        Integer nombreBytes = new Double (Math.ceil(daux)).intValue();
        if(midaAux == 0)midaAux = 8;
        bb.putInt(x.intValue());
        for (int i = 4-nombreBytes; i < 4; i++)
        {
            byte b = bb.get(i);
            add(b,midaAux);
            midaAux = 8;
        } 
    }

    
    public void print()
    {   
        Output_Class.print();
    }

    public void printString()
    {
        Output_Class.printString();
    }

}