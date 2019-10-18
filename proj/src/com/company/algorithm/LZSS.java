package com.company.algorithm;

import java.util.*;
import com.company.output.Ctrl_Output;
import com.company.utils.IntorChar;

public class LZSS
{
    Ctrl_Output Output;
    String file;
    ArrayList <IntorChar> InpDesc;

    public ArrayList<Integer> GetKey(Map<Integer, Character> map, char value) {
        
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Map.Entry<Integer, Character> entry : map.entrySet())
             if (entry.getValue().equals(value))
                 aux.add(entry.getKey());
        return aux;
    }
    
    public LZSS(String file)
    {
        Output = new Ctrl_Output("../CompresioLZSS.out");
        this.file = file;
    }

    public LZSS(ArrayList<IntorChar> v)
    {
        InpDesc = v;
        Output = new Ctrl_Output("../DescompresioLZSS.out");
    }

    public Ctrl_Output print()
    {
        return Output;
    }

    public void Compressor()
    {
        String aux = "";
        Map<Integer, Character> vc = new TreeMap<Integer, Character>();
        char nextChar;
        boolean first = true;
        boolean found2 = false;
        int paux = 0, punter = 0, pivot = 0, pivnotf = 0;
        for (int i = 0; i < file.length()-1; i++)
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
                    while(!found  && (pivot+ji) < punter && (pivot + ji) < vc.size() && (ji < aux.length()) && aux.charAt(ji) == vc.get(pivot + ji))
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
                    Output.add(punter - pivot, 13);
                    Output.add(31, 5);
                    aux = "";
                    first = true;
                }
                else if(!found && aux.length() >= 4)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, 13);
                    punter += (aux.length() - 1);
                    Output.add(aux.length() - 3, 5);
                    aux = String.valueOf(nextChar);
                }
                else if(!found && aux.length() < 4)
                {
                    Output.add((byte)0, 1);
                    Output.add(aux.charAt(0));
                    aux = aux.substring(1);
                    punter++;
                }
                found2 = found;
            }
            else
            {
                if(aux.length() >= 4)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, 13);
                    Output.add(aux.length() - 3, 5);
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
            vc.put(i, nextChar);
            if(vc.size() > 8191) vc.remove(paux++);
        }
        if(found2 && aux.length() >= 3 && aux.length() < 33)
        {
            Output.add((byte)1, 1);
            Output.add(punter - pivot, 13);
            Output.add(aux.length()-2, 5);
        }
        else if(!found2 && aux.length() > 0)
        {
            for(int j = 0; j < aux.length(); j++)
            {
                Output.add((byte)0, 1);
                Output.add(aux.charAt(j));
            }
        }
        Output.add((byte)1, 1);
        Output.add(0, 13);
        Output.add(0, 5); //fi del fitxer
    }

    public void Decompressor()
    {
        Map<Integer, Character> vc = new TreeMap<Integer, Character>();
        int pos = 0, posmap = 0;
        for(int i = 0; i < InpDesc.size(); i++)
        {
            if(InpDesc.get(i).IsIntorChar())
            {
                Character c = InpDesc.get(i).GetChar();
                Output.add(c);
                vc.put(posmap++, c);
                if(vc.size() > 8191) vc.remove(pos++);
            }
            else
            {
                int despl = InpDesc.get(i).GetDespl();
                int mida = InpDesc.get(i).GetMida();
                String aux = "";
                for(int j = 0; j < mida; j++)
                {
                    Character c = vc.get(posmap-despl+j);
                    aux = aux.concat(String.valueOf(c));
                    vc.put(posmap+j, c);
                    if(vc.size() > 8191) vc.remove(pos++);
                }
                posmap += mida;
                Output.add(aux);
            }
        }
    }
}
