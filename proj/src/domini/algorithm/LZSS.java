package domini.algorithm;

import java.util.*;

import persistencia.input.Ctrl_Input_LZSS;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;
import domini.utils.IntorByte;;

public class LZSS {

    /**
     * @param Output Utilitzat per a la compressió i descompressió de fitxers.
     */
    private Ctrl_Output Output;


     /**
     * @brief El constructor.
     * @param aux inicialitza una instància Ctrl_Output.
     * @param b Si false comprimeix else descomprimeix.
     */
    public LZSS(String aux, boolean b)
    {
        Output = new Ctrl_Output(aux, "lzss", b);
    }
    
    /**
     * @fn private ArrayList<Integer> GetKey(Map<Integer, Byte> map, byte value)
     * @brief Mètode auxiliar del compressor.
     * @param map 
     * @param value
     * @return llista de coincidència de "value" a "map".
     */
    private ArrayList<Integer> GetKey(Map<Integer, Byte> map, byte value) {
        
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Map.Entry<Integer, Byte> entry : map.entrySet())
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
        ArrayList<Byte> aux = new ArrayList<Byte>();
        Map<Integer, Byte> vc = new TreeMap<Integer, Byte>();
        byte nextByte;
        boolean first = true;
        boolean found2 = false;
        int paux = 0, punter = 0, pivot = 0, pivnotf = 0, i = 0;
        while(!in.finished())
        {
            nextByte = in.get();
            aux.add(nextByte);
            if(vc.containsValue(nextByte))
            {
                if(first)
                {
                    first = false;
                    punter = i;
                }
                ArrayList<Integer> keys = GetKey(vc, aux.get(0));
                boolean found = false;
                int max = 0;
                for(int j = 0; j < keys.size() && !found; j++)
                {
                    int ji = 0;
                    pivot = keys.get(j);
                    while(!found  && (pivot+ji) < punter && (pivot + ji) < vc.size() && (ji < aux.size()) && aux.get(ji) == vc.get(pivot + ji))
                    {
                        ji++;
                        if(ji == aux.size()) found = true;
                        if(max < ji)
                        {
                            max = ji;
                            pivnotf = pivot;
                        }
                    }
                }
                if(found && aux.size() == 34)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivot, 13);
                    Output.add(31, 5);
                    aux = new ArrayList<Byte>();
                    first = true;
                }
                else if(!found && aux.size() >= 4)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, 13);
                    punter += (aux.size() - 1);
                    Output.add(aux.size() - 4, 5);
                    aux = new ArrayList<Byte>();
                    aux.add(nextByte);
                }
                else if(!found && aux.size() < 4)
                {
                    Output.add((byte)0, 1);
                    Output.add(aux.get(0), 8);
                    aux.remove(0);
                    punter++;
                }
                found2 = found;
            }
            else
            {
                if(aux.size() >= 4)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, 13);
                    Output.add(aux.size() - 4, 5);
                    Output.add((byte)0, 1);
                    Output.add(nextByte, 8);
                }
                else
                {
                    for(int j = 0; j < aux.size(); j++)
                    {
                        Output.add((byte)0, 1);
                        Output.add(aux.get(j), 8);
                    }
                }
                aux = new ArrayList<Byte>();
                first = true;
            }
            vc.put(i, nextByte);
            if(vc.size() > 8191) vc.remove(paux++);
            i++;
        }
        if(found2 && aux.size() >= 3 && aux.size() < 34)
        {
            Output.add((byte)1, 1);
            Output.add(punter - pivot, 13);
            Output.add(aux.size()-3, 5);
        }
        else if(!found2 && aux.size() > 0)
        {
            for(int j = 0; j < aux.size(); j++)
            {
                Output.add((byte)0, 1);
                Output.add(aux.get(j),8);
            }
        }
    }


    /**
     * @fn public void Decompressor(Ctrl_Input_LZSS in)
     * @brief Mètode principal per a la descompressió.
     * @param in És per anar agafant informació de la classe Ctrl_Input_LZSS.
     */
    public void Decompressor(Ctrl_Input_LZSS in)
    {
        Map<Integer, Byte> vc = new TreeMap<Integer, Byte>();
        int pos = 0, posmap = 0;
        //boolean end = false;
        IntorByte ioc;
        while(!in.finished())
        {
            ioc = in.getLZSS();
            if (in.finished()) return;
            if(ioc.IsIntorByte())
            {
                byte c = ioc.GetByte();
                Output.add(c, 8);
                vc.put(posmap++, c);
                if(vc.size() > 8191) vc.remove(pos++);
            }
            else
            {
                int despl = ioc.GetDespl();
                int mida = ioc.GetMida();
                ArrayList<Byte> aux = new ArrayList<Byte>();
                for(int j = 0; j < mida; j++)
                {
                    byte c = vc.get(posmap-despl+j);
                    aux.add(c);
                    vc.put(posmap+j, c);
                    if(vc.size() > 8191) vc.remove(pos++);
                }
                posmap += mida;
                for(int w = 0; w < aux.size(); w++)
                    Output.add(aux.get(w), 8);
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