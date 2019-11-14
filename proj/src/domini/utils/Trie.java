package domini.utils;

import java.util.ArrayList;

class Trie<T> {
    private TrieNode<T> root;

    Trie() {
        root = new TrieNode<T>();
    }

    void insert(ArrayList<T> word) {
        TrieNode<T> current = root;

        for (int i = 0; i < word.size(); i++) {
            current = current.getChildren().computeIfAbsent(word.get(i), c -> new TrieNode<T>());
        }
        current.setEndOfWord(true);
    }

    boolean containsNode(ArrayList<T> word) {
        TrieNode<T> current = root;

        for (int i = 0; i < word.size(); i++) {
            T ch = word.get(i);
            TrieNode<T> node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    boolean isEmpty() {
        return root == null;
    }

}