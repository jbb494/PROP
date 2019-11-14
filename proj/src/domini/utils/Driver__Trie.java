package domini.utils;

import java.util.ArrayList;

class Driver_Trie {

    public static void main(String[] args){
        Trie<Byte> t = new Trie<Byte>();
        ArrayList<Byte> a = new ArrayList<Byte>();
        a.add((byte)76);
        ArrayList<Byte> b = new ArrayList<Byte>();
        b.add((byte)76);
        b.add((byte)77);
        ArrayList<Byte> c = new ArrayList<Byte>();
        c.add((byte)79);
        
        System.out.println("Inserted: " + a );
        t.insert(a,0);   
        System.out.println("Inserted: " + b );     
        t.insert(b,1);

        Integer contains1 = t.indexNode(a);
        Integer contains2 = t.indexNode(b);
        Integer contains3 = t.indexNode(c);

        System.out.println((contains1 != -1 ? "It does contain with index " + contains1 : "it doesn't contain") + ": " + a );
        System.out.println((contains2 != -1 ? "It does contain with index " + contains2 : "it doesn't contain") + ": " + b );
        System.out.println((contains3 != -1 ? "It does contain with index " + contains3 : "it doesn't contain") + ": " + c );

    }
}