package domini.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @class TrieNode
 * @brief Classe TrieNode
 * 
 * Esta clase representa los nodos del Trie.
 * 
 * @author Joan Bellavista Bartroli
 */
class TrieNode<T> {
    /**
     * @param index Es el indice de la cadena de bytes que va desde root (la root del Trie) hasta este TrieNode.
     */
    private Integer index;

    /**
     * @param children Es un hasmap donde se guardan los hijos del nodo actual. La clave del hasmap es el byte y el valor es donde esta el Nodo (que contiene el indice).
     */
    private final Map<T, TrieNode<T>> children = new HashMap<>();

    /**
     * @brief Constructor de la classe
     * @param index Es el indice de la cadena de bytes que va desde root (la root del Trie) hasta este TrieNode.
     */
    public TrieNode(Integer index){
        this.index = index;
    }

    /**
     * @fn public Map<T, TrieNode<T>> getChildren()
     * @return Devuelve los hijos de este nodo
     */
    public Map<T, TrieNode<T>> getChildren() {
        return children;
    }

    /**
     * @fn public Integer getIndex()
     * @return Devuelve el indice de este nodo
     */
    public Integer getIndex(){
        return index;
    }
}