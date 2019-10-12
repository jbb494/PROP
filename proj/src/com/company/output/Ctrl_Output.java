package com.company.output;

import java.nio.ByteBuffer;

public class Ctrl_Output {
    
    //Attributes
    Output Output_Class;

    //Constructor
    public Ctrl_Output() {
        Output_Class = new Output();
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
    public void add(Integer despl, Integer mida){
        int aux = despl & 0xFF;
        Output_Class.add((byte)aux, 8);
        aux = despl >>> 8;
        Output_Class.add((byte)aux, 5);
        aux = mida & 0x1F;
        Output_Class.add((byte)aux, 5);
    }
    public void print()
    {
        Output_Class.print();
    }

}