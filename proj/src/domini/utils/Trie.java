package domini.utils;

import java.util.ArrayList;

public class Trie<T> {
    private TrieNode<T> root;

    public Trie() {
        root = new TrieNode<T>(-1);
    }

    public void insert(ArrayList<T> word, Integer index) {
        TrieNode<T> current = root;

        for (int i = 0; i < word.size(); i++) {
            current = current.getChildren()
                .computeIfAbsent(word.get(i), c -> new TrieNode<T>(index)); 
                // should only compute lastone.
        }
    }

    public Integer indexNode(ArrayList<T> word) {
        TrieNode<T> current = root;
        for (int i = 0; i < word.size(); i++) {
            T ch = word.get(i);
            TrieNode<T> node = current.getChildren().get(ch);
            if (node == null) {
                return -1;
            }
            current = node;
        }
        return current.getIndex();
    }

}