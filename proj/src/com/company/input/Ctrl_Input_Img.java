package com.company.input;

import com.company.utils.stringConversion;

public class Ctrl_Input_Img extends Ctrl_Input {

    int max_val;

    public Ctrl_Input_Img(String path) {
        super(path);
    }

    private char getChar() {
        return (char)Input_class.getBits(8);
    }

    public String getWord() {
        String str = "";
        char c;

        do c = getChar(); 
        while (Character.isWhitespace(c));

        do {
            str += c;
            c = getChar();
        } while (!Character.isWhitespace(c));

        return str;
        
    }

    public int getIntASCII() {
        return stringConversion.atoi( getWord() );
    }
}