package com.company.utils;

import java.util.HashMap;
import java.util.Map;

public class utf8 {
    private static int DICT_SIZE = 256;

    /** Compress a string to a list of output symbols. */
    public static String compress(String uncompressed) {
        uncompressed = uft8Encode(uncompressed);
        // Build the dictionary.
        int index = DICT_SIZE;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < DICT_SIZE; i++)
            dictionary.put("" + (char)i, i);

        String w = "";
        StringBuilder res = new StringBuilder();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                int i = dictionary.get(w);
                res.append((char) i);
                // Add wc to the dictionary.
                dictionary.put(wc, index++);
                w = "" + c;
            }
        }

        // Output the code for w.
        if (!w.equals("")) {
            int i = dictionary.get(w);
            res.append((char) i);
        }
        return res.toString();
    }

    /** Decompress a list of output ks to a string. */
    public static String decompress(String compressed) {
        // Build the dictionary.
        int index = DICT_SIZE;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < DICT_SIZE; i++)
            dictionary.put(i, "" + (char)i);

        char[] chars = compressed.toCharArray();
        String w = "" + chars[0];
        StringBuffer result = new StringBuffer(w);
        for (int j = 1; j < chars.length; j++) {
            int k = (int) chars[j];
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == index)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(entry);

            // Add w+entry[0] to the dictionary.
            dictionary.put(index++, w + entry.charAt(0));

            w = entry;
        }
        return utf8Decode(result.toString());
    }

    /**
     * encode utf8 string into char 0 ~ 127
     * @param text
     * @return
     */
    public static String uft8Encode(String text) {   
        StringBuilder res = new StringBuilder();                
        for (char c : text.toCharArray()) {
            int i = (int) c;
            if (i < 128) {
                res.append((char) i);
            } else if (i > 127 && i < 2048) {
                int j = (i >> 6) | 192;
                res.append((char) j);
                j = (i & 63) | 128;
                res.append((char) j);
            } else {
                int j = (i >> 12) | 224;
                res.append((char) j);
                j = ((i >> 6) & 63) | 128;
                res.append((char) j);
                j = (c & 63) | 128;
                res.append((char) j);
            }
        }
        return res.toString();
    }

    /**
     * decode char 0 ~ 127 to utf8 string
     * @param text
     * @return
     */
    public static String utf8Decode(String text) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        char[] chars = text.toCharArray();
        while (i < chars.length) {
            int c = chars[i++];
            if (c > 191 && c < 224) {
                int c1 = chars[i++];
                c = ((c & 31) << 6) | (c1 & 63);
            } else if (c > 127) {
                int c1 = chars[i++];
                int c2 = chars[i++];
                c = ((c & 15) << 12) | ((c1 & 63) << 6) | (c2 & 63);
            }
            res.append((char) c);
        }
        return res.toString();
    }

    public static void main(String[] args) {
//        List<Integer> compressed = compress("TOBEORNOTTOBEORTOBEORNOT");
//        System.out.println(compressed);
//        String decompressed = decompress(compressed);
//        System.out.println(decompressed);
        String text = "的弄饭呢哦圣达菲欧大海a";
        String t1 = uft8Encode(text);
        String t2 = utf8Decode(t1);
        System.out.println(compress(t1));
        System.out.println("there we go");
        System.out.println(utf8Decode(decompress(compress(t1))));
        //System.out.println(t1);
        //System.out.println(t2);
    }
}
