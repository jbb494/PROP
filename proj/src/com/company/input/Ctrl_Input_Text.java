package com.company.input;

public class Ctrl_Input_Text extends Ctrl_Input {

    public Ctrl_Input_Text(String path) {
        super(path);
    }

    public char get() {
        return (char)Input_class.getBits(8);
    }
}