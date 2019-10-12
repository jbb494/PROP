package com.company.algorithm;

import java.util.*;
import com.company.output.Ctrl_Output;

public class LZSS
{
    Ctrl_Output Output;

    public ArrayList<Integer> GetKey(Map<Integer, Character> map, char value) {
        
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Map.Entry<Integer, Character> entry : map.entrySet())
             if (entry.getValue().equals(value))
                 aux.add(entry.getKey());
        return aux;
    }
    
    public LZSS(String file)
    {
        Output = new Ctrl_Output(file);
        Compresser(file);
    }

    public Ctrl_Output print()
    {
        return Output;
    }

    public void Compresser(String file)
    {
        String aux = "";
        Map<Integer, Character> vc = new TreeMap<Integer, Character>();
        char nextChar;
        boolean first = true;
        int paux = 0, punter = 0, pivot = 0, pivnotf = 0;
        for (int i = 0; i < file.length(); i++)
        {
            nextChar = file.charAt(i);
            aux = aux.concat(String.valueOf(nextChar));
            if(vc.containsValue(nextChar))
            {
                if(first)
                {
                    first = false;
                    punter = i;
                }
                ArrayList<Integer> keys;
                if(aux != "") keys = GetKey(vc, aux.charAt(0));
                else keys = GetKey(vc, nextChar);
                boolean found = false;
                if(aux == "") aux = String.valueOf(nextChar);
                int max = 0;
                for(int j = 0; j < keys.size() && !found; j++)
                {
                    int ji = 0;
                    pivot = keys.get(j);
                    while(!found && (pivot + ji) < vc.size() && (ji < aux.length()) && aux.charAt(ji) == vc.get(pivot + ji))
                    {
                        ji++;
                        if(ji == aux.length()) found = true;
                        if(max < ji)
                        {
                            max = ji;
                            pivnotf = pivot;
                        }
                    }
                }
                if(found && aux.length() == 33)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivot, 31);
                    aux = "";
                    first = true;
                }
                else if(!found && aux.length() >= 4)
                {
                    punter += (aux.length() - 1);
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, aux.length() - 3);
                    aux = String.valueOf(nextChar);
                }
                else if(!found && aux.length() < 4)
                {
                    Output.add((byte)0, 1);
                    Output.add(aux.charAt(0));
                    aux = aux.substring(1);
                    punter++;
                }
            }
            else
            {
                if(aux.length() >= 4)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, aux.length() - 3);
                    Output.add((byte)0, 1);
                    Output.add(nextChar);
                }
                else
                {
                    for(int j = 0; j < aux.length(); j++)
                    {
                        Output.add((byte)0, 1);
                        Output.add(aux.charAt(j));
                    }
                }
                aux = "";
                first = true;
            }
            if(vc.size() > 8191) vc.remove(paux++);
            vc.put(i, nextChar);
        }
    }
}
