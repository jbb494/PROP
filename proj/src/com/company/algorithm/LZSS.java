package com.company.algorithm;

import java.util.*;

public class LZSS
{
    static String Output;
    public ArrayList<Integer> getKey(Map<K, V> map, V value) {
        
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Entry<K, V> entry : map.entrySet())
             if (entry.getValue().equals(value))
                 aux.add(entry.getKey());
        return aux;
    }
    
    public static void compresser(String file)
    {
        String out = "", aux = "";
        int punter, paux;
        Map<Integer, Character> vc = new TreeMap<Integer, Character>();
        char nextChar;
        for(int i = 0; i < file.size(); i++)
        {
            punter = i;
            nextChar = file.charAt(i);
            
            if(vc.containsValue(nextChar))
            {
                aux.concat(String.valueOf(nextChar));
                if(aux != "")
                {
                    ArrayList<Integer> keys = vc.getKey(aux.substring(0, 1));
                    boolean found = false;
                    for(int j = 0; j < keys.size() && !found; j++)
                    {
                        int ji = 0;
                        int pivot = keys.get(j);
                        while(!found && aux.charAt(ji) == vc.get(pivot + ji))
                        {
                            ji++;
                            if(ji == aux.size()) found = true;
                        }
                    }
                    if(!found && aux.size() >= 3)
                    {
                        aux = String.valueOf(nextChar);
                        out.concat("COMPRESSED");
                    }                    
                }
                vc.put(i, nextChar);
            }
            else
            {
                if(aux != "")
                {
                    if(aux.size() >= 3) out.concat("COMPRESSED");
                    else out.concat(aux);
                    aux = "";
                }
                else out.concat(String.valueOf(nextChar));
                vc.put(i, nextChar);
            }
        }
        Output = out;
    }
    public String print()
    {
        return Output;
    }

}