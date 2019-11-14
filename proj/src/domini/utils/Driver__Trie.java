package domini.utils;

import java.util.ArrayList;

class Driver_Trie {

    public static void main(String[] args){
        Trie<Character> t = new Trie<Character>();
        ArrayList<Character> a = new ArrayList<Character>();
        a.add('b');
        ArrayList<Character> b = new ArrayList<Character>();
        b.add('a');
        b.add('b');
        ArrayList<Character> c = new ArrayList<Character>();
        c.add('c');
        
        System.out.println("Inserted: " + a );
        t.insert(a);   
        System.out.println("Inserted: " + b );     
        t.insert(b);

        System.out.println((t.containsNode(a) ? "it does contain" : "it doesn't contain") + ": " + a );
        System.out.println((t.containsNode(b) ? "it does contain" : "it doesn't contain") + ": " + b );
        System.out.println((t.containsNode(c) ? "it does contain" : "it doesn't contain") + ": " + c );

    }
}