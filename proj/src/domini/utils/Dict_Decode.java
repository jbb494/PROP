/**
 * @class Dict_Decode
 * @brief Diccionari (amb els mètodes corresponents) emprat per a la descompressió amb LZW
 * @author Miguel Paracuellos Ocaña
 */
package domini.utils;

import java.util.ArrayList;

public class Dict_Decode {
    //Attributes
    /**
     * @param Limit Màxim nombre d'entrades que pot tenir el diccionari
     */
    final Integer Limit = Integer.MAX_VALUE;
    /**
     * @param v Diccionari de la classe
     * @brief Cada element consta d'un caràcter i un index de la seqüència previa al caràcter
     */
    ArrayList<Pair<Integer,Byte> > v;
    Integer valorNeutre;

    /**
     * @brief Constructor de la classe
     * @note Inicialitzem el vector
     * @param inicializeAscii És true si inicialitzem només començar el diccionari amb la taula ascii.
     * @param valorNeutre Valor {0,1} emprat a getWord
     */
    public Dict_Decode(Boolean inicializeAscii, Integer valorNeutre) {
        v = new ArrayList<Pair<Integer,Byte> >();
        reset_dictionary(inicializeAscii);
        this.valorNeutre = valorNeutre;
    }

    /**
     * @brief Reinicialitza el vector
     * @note Es crida quan sigui ple o sigui creat
     * @param b Es true si volem inicialitzar el diccionari amb la taula ASCII
     */
    public void reset_dictionary(Boolean b) {
        v.clear();
        //v.ensureCapacity(Limit);
        if(!b)return ;
        for (int i = 0; i < 256; ++i) {
            v.add( new Pair<Integer,Byte>(-1, (byte)i) );
        }
    }

    //Functions
    
    /**
     * @fn public String getWord(Integer i)
     * @brief Retorna la paraula que correspon a la seqüència encadenada a partir d'un enter
     * @param i Enter que representa una seqüència de caràcters
     * @return Retorna la paraula corresponent a l'enter
    */
    public ArrayList<Byte> getWord(Integer i) {
        ArrayList<Byte> result = new ArrayList<>();
        result.clear();
        //result.ensureCapacity(Limit);
        Integer diff = this.valorNeutre +1;
        while (i != this.valorNeutre) {
            result.add(0, v.get(i-diff).getRight());
            i = v.get(i-diff).getLeft();
        }

        return result;
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
    public void add(Integer i, byte c) {
        v.add( new Pair<Integer,Byte>(i,c) );
    }
}