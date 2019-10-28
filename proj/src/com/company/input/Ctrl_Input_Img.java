package com.company.input;

import com.company.utils.byteToConversion;
import com.company.utils.stringConversion;

public class Ctrl_Input_Img extends Ctrl_Input {

    int max_val;
    int height;
    int width;
    int bits_per_val;

    public Ctrl_Input_Img(String path) {
        super(path);
        String str = getWord();
        if (! str.equals("P6")) {
            System.err.println("Magic number of "+path+" is "+str+". It should be P6.");
            System.err.println(path+" may not be in ppm format.");
        }
        width = getIntASCII();
        height = getIntASCII();
        max_val = getIntASCII();
        if (max_val < 256) bits_per_val = 8;
        else               bits_per_val = 16;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    //FALTA COMPROVAR
    public double[][][][] get()
    /*  Retorna una matriu ret[width/8][8][8][3],
        on ret[block][x][y][color] 
            és el la qantitat de color 'color' (rgb)
            al píxel (x+8*n, y+8*block), 
            on 8*n son les files llegides abans d'executar get() i n és natural
        Les quantitats de color s'expressen en reals entre 0 i 255
    */
    {
        int n_blocks = width/8;
        double[][][][] ret = new double[n_blocks][8][8][3];
        for (int x = 0; x < 8; ++x) {
            for (int j = 0; j < 8*n_blocks; ++j) {
                for (int color = 0; color < 3; ++color) {
                    int val = byteToConversion.byteToInteger(Input_class.getMoreBits(bits_per_val));
                    ret[j/8][x][j%8][color] = (double)val / (double)max_val * 255.0;
                }
            }
        }
        return ret;
    }





    ////////////

    private char getChar() {
        return (char)Input_class.getBits(8);
    }

    private String getWord() {
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

    private int getIntASCII() {
        return stringConversion.atoi( getWord() );
    }
}