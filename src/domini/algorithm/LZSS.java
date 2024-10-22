package domini.algorithm;

import java.util.*;

import persistencia.input.Ctrl_Input_LZSS;
import persistencia.input.Ctrl_Input_Text;
import domini.utils.ArrayCircular;
import domini.utils.IntorByte;

public class LZSS extends Algorithm {



    /**
     * @param window Valor de la finestra per al compressor.
     */
    private final int window = 2048;

    /**
	 * @brief Constructor de la classe
	 * @param path Path de sortida
	 * @param b False si estas comprimint, True si estas descomprimint
	 */
	public LZSS(String path, boolean b) {
		super(path, b);
		if (!b) {
			Output.addMetadata("lzss");
		}
	}

	/**
	 * @brief Constructor de la classe
	 * @param b False si estas comprimint, True si estas descomprimint
	 * @note Es continua escrivint al fitxer que s'estava escrivint
	 */
	public LZSS(boolean b) {
		super(b);
		if (!b) {
			Output.addMetadata("lzss");
		}
	}

    /**
     * @fn public int KMPSearch(ArrayList<Byte> paraula, ArrayCircular text)
     * @brief Mètode auxiliar del compressor utilitzant l'algorisme de Knuth-Morris-Pratt.
     * @param paraula 
     * @param text
     * @return Retorna l'index de la primera posició a text si troba paraula else -1.
     */
    public int KMPSearch(ArrayList<Byte> paraula, ArrayCircular text) 
    {
        int M = paraula.size(); 
        int N, i;
        int aux = text.getAfegits();
        if(aux < window){ N = aux; i = 0; }
        else { N = text.getEnd(); i = text.getStart(); }
        int j = 0;
        int lps[] = FailArray(paraula, M);
        while (i != N) { 
            if (paraula.get(j).equals(text.getValue(i))) { 
                ++j; 
                i = (++i)%window; 
            } 
            if (j == M)
            {
                int ret = i-j;
                if(ret < 0) ret += window;
                return ret;
            }
            else if (i != N && !paraula.get(j).equals(text.getValue(i))) { 
                if (j != 0) j = lps[j - 1]; 
                else i = (++i)%window; 
            } 
        } 
        return -1;
    } 
  
    /**
     * @fn public int [] FailArray(ArrayList<Byte> paraula, int M)
     * @brief Mètode auxiliar de l'algorisme de Knuth-Morris-Pratt.
     * @param paraula 
     * @param M
     * @return Retorna un vector de M posicions que és la taula de fallades de paraula.
     */
    public int [] FailArray(ArrayList<Byte> paraula, int M) 
    { 
        
        int lps[] = new int[M]; 
        lps[0] = 0;
        int len = 0; 
        int i = 1; 
        while (i < M)
        { 
            if (paraula.get(i) == paraula.get(len))
            { 
                ++len; 
                lps[i] = len; 
                ++i; 
            } 
            else
            { 
                if (len != 0)
                { 
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
    

    /**
     * @fn public void Compressor()
     * @brief Mètode principal per a la compressió.
     */
    public void Compressor()
    {
        checkCompressor();
        Ctrl_Input_Text in = new Ctrl_Input_Text();

        ArrayList<Byte> aux = new ArrayList<Byte>();
        ArrayCircular vc = new ArrayCircular(window);
        byte nextByte;
        boolean first = true;
        boolean found2 = false;
        int punter = 0, pivot = 0, pivnotf = 0, i = 0;
        while(!in.finished())
        {
            nextByte = in.get();
            aux.add(nextByte);
            if(vc.isIn(nextByte))
            {
                if(first)
                {
                    first = false;
                    punter = i;
                }
                pivot = KMPSearch(aux, vc);
                boolean found = (pivot != -1);
                if(found && aux.size() == 34)
                {
                    Output.add((byte)1, 1);
                    int pos = punter - pivot;
                    if(pos < 0) pos += window;
                    Output.add(pos, 11);
                    Output.add(31, 5);
                    aux = new ArrayList<Byte>();
                    first = true;
                }
                else if(!found && aux.size() >= 4)
                {
                    Output.add((byte)1, 1);
                    int pos = punter - pivnotf;
                    if(pos < 0) pos += window;
                    Output.add(pos, 11);
                    punter = ((aux.size() - 1)+punter)%window;
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
                    if(punter == window) punter = 0;
                }
                found2 = found;
            }
            else
            {
                if(aux.size() >= 4)
                {
                    Output.add((byte)1, 1);
                    int pos = punter - pivnotf;
                    if(pos < 0) pos += window;
                    Output.add(pos, 11);
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
            vc.setValue(nextByte);
            pivnotf = pivot;
            i = (++i)%window;
        }
        if(found2 && aux.size() >= 3 && aux.size() < 34)
        {
            Output.add((byte)1, 1);
            int pos = punter - pivot;
            if(pos < 0) pos += window;
            Output.add(pos, 11);
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
     * @fn public void Decompressor()
     * @brief Mètode principal per a la descompressió.
     */
    public void Decompressor()
    {
        checkDecompressor();
        Ctrl_Input_LZSS in = new Ctrl_Input_LZSS();

        ArrayCircular vc = new ArrayCircular(window);
        IntorByte ioc;
        while(!in.finished())
        {
            ioc = in.getLZSS();
            if (in.finished()) return;
            if(ioc.IsIntorByte())
            {
                byte c = ioc.GetByte();
                Output.add(c, 8);
                vc.setValue(c);
            }
            else
            {
                int despl = ioc.GetDespl();
                int mida = ioc.GetMida();
                for(int j = 0; j < mida; j++)
                {
                    byte c = vc.getValueAmbDespl(despl);
                    Output.add(c,8);
                    vc.setValue(c);
                }
            }
        }
    }
}

/** @class LZSS
 *  @brief Aquesta és la classe del algoritme LZSS.
 * @file
 *  En aquesta classe es tracta la compressió mitjançant l'algorisme
 *  LZSS i la descompressió d'un input el qual ha estat comprimit amb
 *  aquest mateix algoritme.
 * 
 *  Per la part de la compressió tenim una finestra corredissa de "window" posicions. On aquest valor tindrà
 *  uns bits per aquest valor i després la mida de 5 bits. Per tant, buscarem una compressió mínima de 3B.
 *  
 *  Per la part de la descompressió, agafarem la unitat mínima representada per InorByte, on, com bé especifica
 *  a aquella classe, seran dos Int's o un Byte.
 *  
 *  @author Manel Aguilar
 */
