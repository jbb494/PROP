package com.company.algorithm;

import java.util.*;

public class LZSS
{
    static String Output;

    public ArrayList<Integer> GetKey(Map<Integer, Character> map, char value) {
        
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Map.Entry<Integer, Character> entry : map.entrySet())
             if (entry.getValue().equals(value))
                 aux.add(entry.getKey());
        return aux;
    }
    
    public LZSS(String file)
    {
        System.out.println("Rata");
        Output = compresser(file);
    }

    public String print()
    {
        return Output;
    }

    public String compresser(String file)
    {
        String out = "", aux = "";
        Map<Integer, Character> vc = new TreeMap<Integer, Character>();
        char nextChar;
        for (int i = 0; i < file.length(); i++)
        {
            nextChar = file.charAt(i);
            System.out.println(nextChar);
            aux.concat(String.valueOf(nextChar));
            if(vc.containsValue(nextChar))
            {
                ArrayList<Integer> keys = GetKey(vc, aux.charAt(0));
                boolean found = false;
                for(int j = 0; j < keys.size() && !found; j++)
                {
                    int ji = 0;
                    int pivot = keys.get(j);
                    while(!found && aux.charAt(ji) == vc.get(pivot + ji))
                    {
                        ji++;
                        if(ji == aux.length()) found = true;
                    }
                }
                if(!found && aux.length() >= 3)
                {
                    aux = String.valueOf(nextChar);
                    out.concat("COMPRESSED");
                }
            }
            else
            {
                if(aux != "")
                {
                    if(aux.length() >= 3)
                    {
                        out.concat("COMPRESSED");
                        aux = String.valueOf(nextChar);
                    }
                    else
                    {
                        out.concat(aux);
                        aux = "";
                    }
                }
                else out.concat(String.valueOf(nextChar));
            }
            vc.put(i, nextChar);
        }
        return out;
    }
}