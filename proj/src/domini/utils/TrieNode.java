package domini.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @class TrieNode
 * @brief Classe TrieNode
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
     * @return Retorna el fill de la classe
     */
    public Map<T, TrieNode<T>> getChildren() {
        return children;
    }

    /**
     * @fn public Integer getIndex()
     * @return Retorna l'index de la classe
     */
    public Integer getIndex(){
        return index;
    }
}