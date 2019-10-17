package com.company.utils;

import com.company.utils.Node;
import java.util.ArrayList;

public class LZW_Dict_Encode {
    //Attributes
    public static final Integer Limit = Integer.MAX_VALUE;

    private ArrayList<Node> arr_n;


    //Constructor and reseting de Dictionary
    public LZW_Dict_Encode() {
        arr_n.ensureCapacity(Limit);
    }

    public void reset_dictionary() {
        arr_n.clear();

        for (int i = 0; i < 256; ++i) {
            arr_n.add( new Node((char)i) );
        }
    }


    //Functions
    public Integer Ascii_value(char c) {
        return Character.getNumericValue(c);
    }

    public Integer search_and_insert_BST(Integer i, char c) {

        //Dictionary's maximum size -> where reset it
        if (arr_n.size() == Limit) 
            reset_dictionary();

        if (i == Limit) 
            return Ascii_value(c);

        Integer pos = arr_n.get(i).First;
        Integer sz = arr_n.size();

        if (pos != Limit) {
            
            while(true) {
                if (c < arr_n.get(pos).c) {
                    
                    if (arr_n.get(pos).Left == Limit) {
                        arr_n.get(pos).Modify_Left(sz);
                        break;
                    }    
                    
                    else pos = arr_n.get(pos).Left;
                }

                else if (c > arr_n.get(pos).c) {

                    if (arr_n.get(pos).Right == Limit) {
                        arr_n.get(pos).Modify_Right(sz);
                        break;
                    }

                    else pos = arr_n.get(pos).Right;

                }

                else return pos;
            }

        }
        else arr_n.get(i).Modify_First(sz);

        arr_n.add( new Node(c) );
        return Limit;
    }

    public Integer getLimit() {
        return Limit;
    }

}