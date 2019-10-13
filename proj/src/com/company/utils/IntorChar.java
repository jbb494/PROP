package com.company.utils;

public class IntorChar {

    private boolean intochar;
    private char caracter;
    private int despl;
    private int mida;
    
    public IntorChar(boolean b)
    {
        intochar = b;
    }
    
    public void SetDespl(int despl)
    {
        this.despl = despl;
    }

    public void SetMida(int mida)
    {
        this.mida = mida;
    }

    public void SetChar(char c)
    {
        caracter = c;
    }
    
    public int GetDespl()
    {
        return despl;
    }

    public int GetMida()
    {
        return mida;
    }

    public char GetChar()
    {
        return caracter;
    }

}
