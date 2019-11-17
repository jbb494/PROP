/**
 * @class Trie
 * @brief Classe de Trie
 * @author Joan Bellavista
 */
package domini.utils;

import java.util.ArrayList;

public class Trie<T> {

    /**
     * @param root Arrel de l'arbre
     */
    private TrieNode<T> root;

    /**
     * @brief Constructor de la classe
     */
    public Trie() {
        root = new TrieNode<T>(-1);
    }

    /**
     * @fn public void insert(ArrayList<T> list, Integer index)
     * @brief Inserta al Trie una lista de bytes con un indice.
     * @param list 
     * @param index
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
     * @brief 
     * @param list
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