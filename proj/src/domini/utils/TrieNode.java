package domini.utils;

import java.util.HashMap;
import java.util.Map;

class TrieNode<T> {
    private final Map<T, TrieNode<T>> children = new HashMap<>();
    private Integer index;

    public TrieNode(Integer index){
        this.index = index;
    }

    public Map<T, TrieNode<T>> getChildren() {
        return children;
    }

    public Integer getIndex(){
        return index;
    }
}