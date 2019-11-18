package domini.utils;

import java.util.ArrayList;

/**
 * @class Trie
 * @brief Clase de Trie.
 * 
 * Esta clase es una implementación general de la estructura de datos Trie.
 * Cada nodo representa una seqüencias de carácetres. Cada conexión entre nodos (padre-hijo),
 * representa un carácter. Y un nodo es la seqüencia de carácteres desde él hasta la raiz.
 * Además, cada nodo tiene un entero que identifica su seqüencia. (esto será útil para el compresor)
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