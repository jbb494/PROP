package com.company.utils;

public class IntorChar {

    /**
       * Atribut intochar.
       * 
       * On false són 2 int i true és un char.
       */
    private boolean intochar; 

    /**
       * Atribut caracter.
       * 
       * On té el valor d'un char si intochar és true.
       */
    private char caracter;

    /**
       * Atribut despl.
       * 
       * On té el valor d'un int si intochar és false.
       */
    private int despl;

    /**
       * Atribut mida.
       * 
       * On té el valor d'un int si intochar és false.
       */
    private int mida;
    
    /**
       * El constructor.
       * On es diu si es un Character (true) o dos 
       * Integers (false).
       */
    public IntorChar(boolean b)
    {
        intochar = b;
    }
    
    /**
       * Mètode per establir un possible despl.
       */
    public void SetDespl(int despl)
    {
        this.despl = despl;
    }

    /**
       * Mètode per establir una possible mida.
       */
    public void SetMida(int mida)
    {
        this.mida = mida;
    }

    /**
       * Mètode per establir un possible char.
       */
    public void SetChar(char c)
    {
        caracter = c;
    }
    
    /**
       * Mètode per obtenir un possible despl.
       */
    public int GetDespl()
    {
        return despl;
    }

    /**
       * Mètode per obtenir una possible mida.
       */
    public int GetMida()
    {
        return mida;
    }

    /**
       * Mètode per obtenir un possible char.
       */
    public char GetChar()
    {
        return caracter;
    }

    /**
       * Mètode per saber si és un char o 2 int's.
       */
    public boolean IsIntorChar()
    {
        return intochar;
    }

}
/** @class IntorChar 
 *  @brief Classe auxiliar per l'algorisme LZSS.
 *   
 *  En aquesta classe es tracta de construir un objecte
 *  per obtenir una unitat mínima per al descompressor.
 * 
 *  @author Manel Aguilar
 */
