package domini.utils;

import java.util.HashMap;
import java.util.Map;

class TrieNode<T> {
    private final Map<T, TrieNode<T>> children = new HashMap<>();
    private boolean endOfWord;
    // private Integer index;

    /*public TrieNode(Integer arg){
        this.index = arg;
    }
*/
    Map<T, TrieNode<T>> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
    /*Integer get_index(){
        return index;
    }
    */
}