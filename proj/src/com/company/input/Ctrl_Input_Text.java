package com.company.input;

public class Ctrl_Input_Text extends Ctrl_Input {

    Byte seguent;

    public Ctrl_Input_Text(String path) {
        super(path);
        seguent = Input_class.getBits(8);
    }
 

    public char get() {
        //No hauriem de treballar amb la funcio read?  No tractem final del readbuffer
        byte actual = seguent;
        seguent = Input_class.getBits(8); 
        return (char)actual;
    }

}