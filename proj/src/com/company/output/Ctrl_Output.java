package com.company.output;


import java.nio.ByteBuffer;


public class Ctrl_Output {
    
    //Attributes
    Output Output_Class;

    //Constructor
    public Ctrl_Output(String path) {
        Output_Class = new Output(path);
    }

    //Functions
    public void add(Byte b, Integer n_bits) {
        Output_Class.add(b, n_bits);
    }


    public void add(String s) {
        byte[] bytearray = s.getBytes();
        for (byte b : bytearray) 
            Output_Class.add(b,8);
    }


    public void add(Character c) {
        add(Character.toString(c));
    }


    public void add(Integer x) {
        ByteBuffer bb = ByteBuffer.allocate(4); 
        bb.putInt(x.intValue());
        for (byte b : bb.array()) 
            add(b,8);
    }


    public void add(Integer x, Integer mida){
        /*int aux = despl & 0xFF;
        Output_Class.add((byte)aux, 8);
        aux = despl >>> 8;
        Output_Class.add((byte)aux, 5);
        aux = mida & 0x1F;
        Output_Class.add((byte)aux, 5);
        */
        ByteBuffer bb = ByteBuffer.allocate(4); 
        Integer midaAux = mida%8;
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