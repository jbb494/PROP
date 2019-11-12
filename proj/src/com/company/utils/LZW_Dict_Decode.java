/**
 * @class LZW_Dict_Decode
 * @brief Diccionari (amb els mètodes corresponents) emprat per a la descompressió amb LZW
 * @author Miguel Paracuellos Ocaña
 */
package com.company.utils;

import java.util.ArrayList;
import com.company.utils.Pair;

public class LZW_Dict_Decode {
    //Attributes
    /**
     * @param Limit Màxim nombre d'entrades que pot tenir el diccionari
     */
    final Integer Limit = Integer.MAX_VALUE;
    /**
     * @param v Diccionari de la classe
     * @brief Cada element consta d'un caràcter i un index de la seqüència previa al caràcter
     */
    ArrayList<Pair<Integer,Character> > v;

    /**
     * @brief Constructor de la classe
     * @note Inicialitzem el vector
     */
    public LZW_Dict_Decode() {
        v = new ArrayList<Pair<Integer,Character> >();
        reset_dictionary();
    }

    /**
     * @brief Reinicialitza el vector
     * @note Es crida quan sigui ple o sigui creat
     */
    public void reset_dictionary() {
        v.clear();
        //v.ensureCapacity(Limit);

        for (int i = 0; i < 256; ++i) {
            v.add( new Pair<Integer,Character>(Limit, (char)i) );
        }
    }

    //Functions
    
    /**
     * @fn public String getWord(Integer i)
     * @brief Retorna la paraula que correspon a la seqüència encadenada a partir d'un enter
     * @param i Enter que representa una seqüència de caràcters
     * @return Retorna la paraula corresponent a l'enter
    */
    public String getWord(Integer i) {
        ArrayList<Character> result = new ArrayList<>();
        result.clear();
        //result.ensureCapacity(Limit);

        while (i != Limit) {
            result.add(0, v.get(i).getRight());
            i = v.get(i).getLeft();
        }

        String s = "";
        for (char c : result) {
            s += c;
        }

        return s;
    }

    /**
     * @fn public Integer getSize()
     * @return Size del vector
     */
    public Integer getSize() {
        return v.size();
    }

    /**
     * @fn public void add(Integer i, char c)
     * @brief Afegeix un element al vector de la classe
     * @param i Enter que representa la cadena previa
     * @param c Caràcter actual
     */
    public void add(Integer i, char c) {
        v.add( new Pair<Integer,Character>(i,c) );
    }
}