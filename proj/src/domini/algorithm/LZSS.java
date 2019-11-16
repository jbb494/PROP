package domini.algorithm;

import java.util.*;

import persistencia.input.Ctrl_Input_LZSS;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;
import domini.utils.IntorByte;
import domini.utils.Pair;;

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

    private int KMPSearch(ArrayList<Byte> paraula, Map<Integer,Byte> text, int paux) 
    {
        int M = paraula.size(); 
        int N = text.size();   
        int j = 0;
        int lps[] = computeLPSArray(paraula, M);
        int i = 0;
        while (i < N) { 
            if (paraula.get(j).equals(text.get(i+paux))) { 
                j++; 
                i++; 
            } 
            if (j == M) return i-j+paux;
            else if (i < N && !paraula.get(j).equals(text.get(i+paux))) { 
                if (j != 0) j = lps[j - 1]; 
                else i = i + 1; 
            } 
        } 
        return -1;
    } 
  
    int [] computeLPSArray(ArrayList<Byte> paraula, int M) 
    { 
        int len = 0; 
        int i = 1; 
        int lps[] = new int[M]; 
        lps[0] = 0;
        while (i < M) { 
            if (paraula.get(i) == paraula.get(len)) { 
                len++; 
                lps[i] = len; 
                i++; 
            } 
            else
            { 
                if (len != 0) { 
                    len = lps[len - 1];
                } 
                else
                { 
                    lps[i] = len; 
                    i++; 
                } 
            } 
        } 
        return lps;
    }

    private int[] fail(ArrayList<Byte> pat)
    {
        int n = pat.size();
        int failure[] = new int[n];
        failure[0] = -1;
        for (int j = 1; j < n; j++)
        {
            int i = failure[j - 1];
            while ((i >= 0 && pat.get(j) != pat.get(i + 1)))
                i = failure[i];
            if (pat.get(j) == pat.get(i + 1))
                failure[j] = i + 1;
            else
                failure[j] = -1;
        }
        return failure;
    }
    /** Function to find match for a pattern **/
    private int posMatch(ArrayList<Byte> pat, byte[] text, int start, int end, int overflow)
    {
        int i, j = 0;
        int lens;
        if(overflow <= 1023){ lens = end; i = 0;}
        else { lens = end; i = start; }
        int lenp = pat.size();
        int failure[] = fail(pat);
        if(overflow <= 1023)
        {
            while (i < lens && j < lenp)
            {
                if (text[i] == pat.get(j))
                {
                    i++;
                    j++;
                }
                else if (j == 0)
                    i++;
                else
                    j = failure[j - 1] + 1;
            }
        }
        else
        {
            while (i != lens && j < lenp)
            {
                if (text[i] == pat.get(j))
                {
                    i = (++i)%1024;
                    j++;
                }
                else if (j == 0)
                    i = (++i)%1024;
                else
                    j = failure[j - 1] + 1;
            }
        }
        return ((j == lenp) ? (i - lenp) : -1);
    }
       /* 
    private int KMP_Alg(ArrayList<Byte> paraula, Map<Integer, Byte> text, int paux)
    {
        int[] taula = TableKMP(paraula);
        int aux = 0;
        for(int i = 0; i < text.size(); i++)
        {
            while(aux > 0 && !text.get(i+paux).equals(paraula.get(aux)))
                aux = taula[aux-1];
            if(text.get(i+paux).equals(paraula.get(aux)))
            {
                ++aux;
                if(paraula.size() == aux) return i-(aux-1)+paux; //found
            }
        }
        return -1; //not found
    }

    private int[] TableKMP(ArrayList<Byte> paraula)
    {
        int[] taula = new int[paraula.size()];
        if(taula.length > 0) taula[0] = 0;
        for(int i = 1; i < paraula.size(); i++)
        {
            int j = taula[i-1];
            while(j > 0 && paraula.get(j).equals(paraula.get(i)))
                j = taula[j-1];
            if(paraula.get(i).equals(paraula.get(j))) ++j;
            taula[i] = j;
        }
        return taula;
    }*/


    /**
     * @fn public Ctrl_Output print()
     * @brief Mètode print.
     * @return Retorna l'atribut "Output".
     */
    public Ctrl_Output print()
    {
        return Output;
    }

    private boolean conte(byte[] vc, int maxlen, byte value)
    {
        int len;
        if(maxlen <= 1023) len = maxlen;
        else len = 1024;
        for(int i = 0; i < len; i++)
        {
            if(vc[i] == value) return true;
        }
        return false;
    }

    /**
     * @fn public void Compressor(Ctrl_Input_Text in)
     * @brief Mètode principal per a la compressió.
     * @param in És per anar agafant informació de la classe Ctrl_Input_Text.
     */
    public void Compressor(Ctrl_Input_Text in)
    {
        
        /*ArrayList<Byte> aux = new ArrayList<Byte>();
        ArrayList<Integer> keys = new ArrayList<Integer>();
        boolean needkey = true;
        Map<Integer, Byte> vc = new TreeMap<Integer, Byte>();
        byte nextByte;
        boolean first = true;
        boolean found2 = false;
        int paux = 0, punter = 0, pivot = 0, pivnotf = 0, i = 0;
        while(!in.finished())
        {
            nextByte = in.get();
            aux.add(nextByte);
            if(i%100000 == 0) System.out.println(i/100000);
            if(vc.containsValue(nextByte))
            {
                if(first)
                {
                    first = false;
                    punter = i;
                }
                keys = GetKey(vc, aux.get(0));
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
                    Output.add(punter - pivot, 10);
                    Output.add(31, 5);
                    aux = new ArrayList<Byte>();
                    first = true;
                    needkey = true;
                }
                else if(!found && aux.size() >= 4)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, 10);
                    punter += (aux.size() - 1);
                    Output.add(aux.size() - 4, 5);
                    aux = new ArrayList<Byte>();
                    aux.add(nextByte);
                    needkey = true;
                }
                else if(!found && aux.size() < 4)
                {
                    Output.add((byte)0, 1);
                    Output.add(aux.get(0), 8);
                    aux.remove(0);
                    punter++;
                    needkey = true;
                }
                found2 = found;
            }
            else
            {
                if(aux.size() >= 4)
                {
                    Output.add((byte)1, 1);
                    Output.add(punter - pivnotf, 10);
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
                needkey = true;
            }
            vc.put(i, nextByte);
            if(vc.size() > 1023) vc.remove(paux++);
            i++;
        }
        if(found2 && aux.size() >= 3 && aux.size() < 34)
        {
            Output.add((byte)1, 1);
            Output.add(punter - pivot, 10);
            Output.add(aux.size()-3, 5);
        }
        else if(!found2 && aux.size() > 0)
        {
            for(int j = 0; j < aux.size(); j++)
            {
                Output.add((byte)0, 1);
                Output.add(aux.get(j),8);
            }
        }*/
        ArrayList<Byte> aux = new ArrayList<Byte>();
        byte vc[] = new byte[1024];
        byte nextByte;
        boolean first = true;
        boolean found2 = false;
        int start = 0, end = 1023;
        int punter = 0, pivot = 0, pivnotf = 0, i = 0;
        while(!in.finished())
        {
            nextByte = in.get();
            aux.add(nextByte);
            if(i%100000 == 0) System.out.println(i/100000);
            if(conte(vc, i, nextByte))
            {
                if(first)
                {
                    first = false;
                    punter = start;
                }
                pivot = posMatch(aux, vc, start, end, i); // O(n+m)
                boolean found = (pivot != -1);
                if(found && aux.size() == 34)
                {
                    Output.add((byte)1, 1);
                    int pos;
                    if(punter - pivot < 0) pos = (punter - pivot) + 1024;
                    else pos = (punter - pivot);
                    Output.add(pos, 10);
                    Output.add(31, 5);
                    aux = new ArrayList<Byte>();
                    first = true;
                }
                else if(!found && aux.size() >= 4)
                {
                    Output.add((byte)1, 1);
                    int pos;
                    if(punter - pivnotf < 0) pos = (punter - pivnotf) + 1024;
                    else pos = (punter - pivnotf);
                    Output.add(pos, 10);
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
                    int pos;
                    if(punter - pivnotf < 0) pos = (punter - pivnotf) + 1024;
                    else pos = (punter - pivnotf);
                    Output.add(pos, 10);
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
            vc[start] = nextByte;
            end = (++end)%1024;
            start = (++start)%1024;
            i++;
            pivnotf = pivot;
        }
        if(found2 && aux.size() >= 3 && aux.size() < 34)
        {
            Output.add((byte)1, 1);
            int pos;
            if(punter - pivot < 0) pos = (punter - pivot) + 1024;
            else pos = (punter - pivot);
            Output.add(pos, 10);
            Output.add(aux.size()-3, 5);
        }
        else
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
        byte[] vc = new byte[1024];
        int start = 0;
        //boolean end = false;
        IntorByte ioc;
        boolean maxsize = false;
        while(!in.finished())
        {
            ioc = in.getLZSS();
            if (in.finished()) return;
            if(ioc.IsIntorByte())
            {
                byte c = ioc.GetByte();
                Output.add(c, 8);
                vc[start] = c;
                start = (++start)%1024;
                if(start == 0) maxsize = true;
            }
            else
            {
                int despl = ioc.GetDespl();
                int mida = ioc.GetMida();
                for(int j = 0; j < mida; j++)
                {
                    int pos;
                    if(maxsize)
                    {
                        if(start - despl +j < 0) pos = (start - despl + j)+1024;
                        else pos = start - despl + j;
                    }
                    else pos = start - despl + j;
                    if(pos > 1023)
                    {
                        System.out.println(maxsize);
                    System.out.println("start: " + start);
                    System.out.println("despl: " + (despl));
                    System.out.println("j: "+ j);
                    }
                    byte c = vc[pos];
                    Output.add(c,8);
                    int moviment = (start+j)%1024;
                    if(moviment == 0) maxsize = true;
                    vc[moviment] = c;
                }
                start = (start + mida)%1024;
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