package com.company.utils;

import java.util.ArrayList;
import com.company.utils.Pair;

public class LZW_Dict_Decode {
    //Attributes
    final Integer Limit = Integer.MAX_VALUE;
    ArrayList<Pair<Integer,Character> > v;


    //Constructor and reseting it
    public LZW_Dict_Decode() {
        reset_dictionary();
    }

    public void reset_dictionary() {
        v.clear();
        v.ensureCapacity(Limit);

        for (int i = 0; i < 256; ++i) {
            v.add( new Pair<Integer,Character>(Limit, (char)i) );
        }
    }


    //Functions
    public String getWord(Integer i) {
        ArrayList<Character> result = new ArrayList<>();
        result.clear();
        result.ensureCapacity(Limit);

        while (i != Limit) {
            result.add(0, v.get(i).getRight());
            i = v.get(i).getLeft();
        }

        String s = "";
        for (char c : result) {
            s += c;
        }

        return s;
    }


    public Integer getSize() {
        return v.size();
    }

    public void add(Integer i, char c) {
        v.add( new Pair<Integer,Character>(i,c) );
    }
}