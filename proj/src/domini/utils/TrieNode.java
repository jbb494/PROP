/**
 * @class TrieNode
 * @brief Classe TrieNode
 * @author Joan Bellavista Bartroli
 */
package domini.utils;

import java.util.HashMap;
import java.util.Map;

class TrieNode<T> {
    /**
     * @param children 
     */
    private final Map<T, TrieNode<T>> children = new HashMap<>();
    /**
     * @param index
     */
    private Integer index;

    /**
     * @brief Constructor de la classe
     * @param index
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