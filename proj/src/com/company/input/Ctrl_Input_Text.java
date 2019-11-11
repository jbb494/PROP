package com.company.input;

public class Ctrl_Input_Text extends Ctrl_Input {

    public Ctrl_Input_Text(String path) {
        super(path);
    }

    public char get() {
        //No hauriem de treballar amb la funció read?  No tractem final del readbuffer
        return (char)Input_class.getBits(8);
    }
}