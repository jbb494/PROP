package domini.utils;

import java.util.ArrayList;

/**
 * @class Trie
 * @brief Clase de Trie.
 * @file

 * Aquesta classe és una implementació general de l'estructura de dades Trie. Cada node representa una seqüencia de Bytes.
 * Cada connexió entre nodes (pare-fill), representa un caràcter. I un node representa la seqüència de caràcters des d'ell fins a l'arrel.
 * A més, cada node té un enter que identifica la seva seqüència. (això serà útil per al compressor). 
 * Tot i que aquesta classe està implementada de forma general (), parlarem com si fos un Trie,
 * ja que és com l'utilitzarem sempre en aquest projecte.
 * 
 * @author Joan Bellavista
 */
public class Trie<T> {

    /**
     * @param root Raiz del árbol
     */
    private TrieNode<T> root;

    /**
     * @brief Constructor de la clase
     */
    public Trie() {
        root = new TrieNode<T>(-1);
    }

    /**
     * @fn public void insert(ArrayList<T> list, Integer index)
     * @brief Inserta al Trie una lista de bytes con un indice.
     * @param list La lista de bytes.
     * @param index El entero que representará el índice de esta cadena de bytes.
     */
    public void insert(ArrayList<T> list, Integer index) {
        TrieNode<T> actual = root;

        for (int i = 0; i < list.size(); i++) {
            actual = actual.getChildren()
                .computeIfAbsent(list.get(i), c -> new TrieNode<T>(index)); 
                // Solo deberia computar el ultimo.
        }
    }

    /**
     * @fn public Integer indexNode(ArrayList<T> word)
     * @brief Devuelve el indice de la seqüencia de carácteres.
     * @param list La lista de bytes.
     * @return Devuelve el indice de la lista "list" si esta. O -1 si no esta.
     */
    public Integer indexNode(ArrayList<T> list) {
        TrieNode<T> actual = this.root;
        for (int i = 0; i < list.size(); i++) {
            T ch = list.get(i);
            TrieNode<T> node = actual.getChildren().get(ch);
            if (node == null) {
                return -1;
            }
            actual = node;
        }
        return actual.getIndex();
    }

}
