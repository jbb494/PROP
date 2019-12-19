package domini.utils;

import domini.utils.Node;
import java.util.ArrayList;

/**
 * @class Dict_Encode
 * @brief Diccionari (amb els mètodes corresponents) emprat per a la compressió.
 * @file

 * 
 * Estructura de dades on cada posició representarà una seqüencia de bytes b0,b1,...,bn-1,bn. Per entendre com 
 * es genera i s'obté aquesta seqüencia es recomanable mirar la descripció de la classe Node donat que es l'estructura
 * de dades de cada posició del nostre Array. 
 * @author Miguel Paracuellos Ocaña
 */
public class Dict_Encode {
    
    //Attributes
    /**
     * @param Limit Nombre màxim d'entrades que pot tenir el diccionari
     */
    public static final Integer Limit = Integer.MAX_VALUE;

    /**
     * @param arr_n Diccionari de la classe
     */
    private ArrayList<Node> arr_n;


    /**
     * @brief Constructor de la classe
     * @note Inicialitzem el diccionari
     */
    public Dict_Encode() {
        arr_n = new ArrayList<Node>();
        reset_dictionary();
    }

    /**
     * @fn public void reset_dictionary()
     * @brief Reinicilització del diccionari de la classe
     * @note Només quan el creem o quan estigui ple
     */
    public void reset_dictionary() {
        arr_n.clear();

        for (int i = 0; i < 256; ++i) {
            arr_n.add( new Node((byte)i) );
        }
    }


    //Functions

    /**
     * @fn public Integer Ascii_value(byte c)
     * @brief Retorna el valor númeric d'un byte
     * @param c byte que volem passar a valor numeric
     * @return Valor numeric del byte
    */
    public Integer Ascii_value(byte c) {
        int x = (int) c;
        if (x < 0) x += 256;
        return (Integer)x;
    }

    /**
     * @fn public Integer search_and_insert_BST(Integer i, byte c)
     * @brief Cada cop que es crida es mira si ja tenim la combinació enter-byte al diccionari
     * @param i Enter que representa una cadena de byte
     * @param c byte actual
     * @return Retorna l'enter que farà referència a la nova cadena de byte
     */
    public Integer search_and_insert_BST(Integer i, byte c) {
        
        //Dictionary's maximum size -> where reset it
        if (arr_n.size() == Limit) 
            reset_dictionary();

        if (i == -1) 
            return Ascii_value(c);
        
        Integer pos = arr_n.get(i).First;
        Integer sz = arr_n.size();

        if (pos != -1) {
            while(true) {
                if (c < arr_n.get(pos).c) {
                    
                    if (arr_n.get(pos).Left == -1) {
                        Node aux = arr_n.get(pos);
                        aux.Modify_Left(sz);
                        arr_n.set(pos, aux);
                        //arr_n.get(pos).Modify_Left(sz);
                        break;
                    }    
                    
                    else pos = arr_n.get(pos).Left;
                }

                else if (c > arr_n.get(pos).c) {

                    if (arr_n.get(pos).Right == -1) {
                        Node aux = arr_n.get(pos);
                        aux.Modify_Right(sz);
                        arr_n.set(pos, aux);
                        //arr_n.get(pos).Modify_Right(sz);
                        break;
                    }

                    else pos = arr_n.get(pos).Right;

                }

                else return pos;
            }

        }
        else {
            Node aux = arr_n.get(i);
            aux.Modify_First(sz);
            arr_n.set(i, aux);
            //arr_n.get(i).Modify_First(sz);
        }
        arr_n.add( new Node(c) );
        return -1;
    }

    /**
     * @fn public Integer getLimit()
     * @return Retorna el limit d'entrades que pot tenir el diccionari
     */
    public Integer getLimit() {
        return Limit;
    }

}
