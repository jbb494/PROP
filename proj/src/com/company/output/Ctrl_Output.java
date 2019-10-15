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
            add(b,8);
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
        //Initialize a buffer where each position corresponds to a byte of the Integer
        ByteBuffer bb = ByteBuffer.allocate(4); 
        bb.putInt(x.intValue());

        //Corresponds to the extra bits of the first byte
        int bit_restants = mida % 8;

        //Corresponds to the byte where we start
        int byte_inic = 4 - (int)(mida / 8);
        if (bit_restants != 0) --byte_inic;

        for (int i = byte_inic; i < 4; ++i) {
            if (i == byte_inic) {
                add(bb.get(i), bit_restants);
                continue;
            }
            add(bb.get(i),8);
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