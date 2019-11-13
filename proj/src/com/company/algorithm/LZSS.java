package com.company.algorithm;

import java.util.*;

import com.company.input.Ctrl_Input_LZSS;
import com.company.input.Ctrl_Input_Text;
import com.company.output.Ctrl_Output;
import com.company.utils.IntorChar;

public class LZSS implements Algorithm
{

    /**
     * @param Output Utilitzat per a la compressió i descompressió de fitxers.
     */
    private Ctrl_Output Output;


     /**
     * @brief El constructor.
     * @param aux inicialitza una instància Ctrl_Output.
     */
    public LZSS(String aux, boolean b)
    {
        Output = new Ctrl_Output(aux, "lzss", b);
    }
    
    /**
     * @fn private ArrayList<Integer> GetKey(Map<Integer, Character> map, char value)
     * @brief Mètode auxiliar del compressor.
     * @param map 
     * @param value
     * @return llista de coincidència de "value" a "map".
     */
    private ArrayList<Integer> GetKey(Map<Integer, Character> map, char value) {
        
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Map.Entry<Integer, Character> entry : map.entrySet())
             if (entry.getValue().equals(value))
                 aux.add(entry.getKey());
        return aux;
    }
        

    /**
     * @fn public Ctrl_Output print()
     * @brief Mètode print.
     * @return Retorna l'atribut "Output".
     */
    public Ctrl_Output print()
    {
        return Output;
    }

    /**
     * @fn public void Compressor(Ctrl_Input_Text in)
     * @brief Mètode principal per a la compressió.
     * @param in És per anar agafant informació de la classe Ctrl_Input_Text.
     */
    public void Compressor(Ctrl_Input_Text in)
    {
        String aux = "";
        Map<Integer, Character> vc = new TreeMap<Integer, Character>();
        char nextChar;
        boolean first = true;
        boolean found2 = false;
        int paux = 0, punter = 0, pivot = 0, pivnotf = 0, i = 0;
        while(!in.finished())
        {
            nextChar = in.get();
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
                if(found && aux.length() == 34)
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
                    Output.add(aux.length() - 4, 5);
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
                    Output.add(aux.length() - 4, 5);
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
            i++;
        }
        if(found2 && aux.length() >= 3 && aux.length() < 34)
        {
            Output.add((byte)1, 1);
            Output.add(punter - pivot, 13);
            Output.add(aux.length()-3, 5);
        }
        else if(!found2 && aux.length() > 0)
        {
            for(int j = 0; j < aux.length(); j++)
            {
                Output.add((byte)0, 1);
                Output.add(aux.charAt(j));
            }
        }
        /*Output.add((byte)1, 1);
        Output.add(0, 13);
        Output.add(0, 5); //fi del fitxer*/
    }


    /**
     * @fn public void Decompressor(Ctrl_Input_LZSS in)
     * @brief Mètode principal per a la descompressió.
     * @param in És per anar agafant informació de la classe Ctrl_Input_LZSS.
     */
    public void Decompressor(Ctrl_Input_LZSS in)
    {
        Map<Integer, Character> vc = new TreeMap<Integer, Character>();
        int pos = 0, posmap = 0;
        //boolean end = false;
        IntorChar ioc;
        while(!in.finished())
        {
            ioc = in.getLZSS();
            if (in.finished())return;
            if(ioc.IsIntorChar())
            {
                Character c = ioc.GetChar();
                Output.add(c);
                vc.put(posmap++, c);
                if(vc.size() > 8191) vc.remove(pos++);
            }
            else
            {
                int despl = ioc.GetDespl();
                int mida = ioc.GetMida();
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

/** @class LZSS 
 *  @brief Aquesta és la classe del algoritme LZSS.
 *   
 *  En aquesta classe es tracta la compressió mitjançant l'algorisme
 *  LZSS i la descompressió d'un input el qual ha estat comprimit amb
 *  aquest mateix algoritme.
 * 
 *  @author Manel Aguilar
 */