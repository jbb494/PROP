package com.company.utils;

import com.company.utils.Node;
import java.util.ArrayList;

public class LZW_Dict_Encode {
    //Attributes
    public static final Integer Limit = Integer.MAX_VALUE;

    private ArrayList<Node> arr_n;


    //Constructor and reseting de Dictionary
    public LZW_Dict_Encode() {
        arr_n = new ArrayList<Node>();
        reset_dictionary();
    }

    public void reset_dictionary() {
        arr_n.clear();

        for (int i = 0; i < 256; ++i) {
            arr_n.add( new Node((char)i) );
        }
    }


    //Functions
    public Integer Ascii_value(char c) {
        int x = (int) c;
        return (Integer)x;
    }

    public Integer search_and_insert_BST(Integer i, char c) {

        //Dictionary's maximum size -> where reset it
        if (arr_n.size() == Limit) 
            reset_dictionary();

        if (i == -1) 
            return Ascii_value(c);

        Integer pos = arr_n.get(i).First;
        Integer sz = arr_n.size();

        if (pos != -1) {
            while(true) {
                if (c < arr_n.get(pos).c) {
                    
                    if (arr_n.get(pos).Left == -1) {
                        Node aux = arr_n.get(pos);
                        aux.Modify_Left(sz);
                        arr_n.set(pos, aux);
                        //arr_n.get(pos).Modify_Left(sz);
                        break;
                    }    
                    
                    else pos = arr_n.get(pos).Left;
                }

                else if (c > arr_n.get(pos).c) {

                    if (arr_n.get(pos).Right == -1) {
                        Node aux = arr_n.get(pos);
                        aux.Modify_Right(sz);
                        arr_n.set(pos, aux);
                        //arr_n.get(pos).Modify_Right(sz);
                        break;
                    }

                    else pos = arr_n.get(pos).Right;

                }

                else return pos;
            }

        }
        else {
            Node aux = arr_n.get(i);
            aux.Modify_First(sz);
            arr_n.set(i, aux);
            //arr_n.get(i).Modify_First(sz);
        }
        arr_n.add( new Node(c) );
        return -1;
    }

    public Integer getLimit() {
        return Limit;
    }

}