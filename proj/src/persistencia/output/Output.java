/**
 * @class Output
 * @brief Classe Output
 * @author 
 */
package persistencia.output;

import java.util.*;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import domini.utils.byteToConversion;

public class Output {

    /**
     * @param Out Llista de bytes que escriurem
     */
    ArrayList<Byte> Out;

    /**
     * @param Pos Posicio en la que ens trobem a l'arxiu
     */
    Integer Pos;

    /**
     * @param path Path de sortida de l'arxiu
     */
    String path;

    /**
     * @brief Constructor de la classe
     * @param path Path de sortida
     */
    public Output(String path) {
        Out = new ArrayList<>();
        Pos = 0;
        this.path = path;
    }

    
    /**
     * @fn public void add(byte b, int n_bits)
     * @brief Afegeix un byte amb n_bits valids
     * @param b Byte a afegir
     * @param n_bits Nombre de bits valids
     * @note El byte b ha de poder expressar-se en n_bits
     */
    public void add(byte b, int n_bits) 
    {
        if(n_bits != 0)
        {
            byte aux, aux2;
            aux = (byte)(b << Pos);
            if(Pos != 0)
            {
                aux2 = Out.get(Out.size()-1); 
                Out.remove(Out.size()-1);
                aux = (byte)(aux | aux2);
            }
            Out.add(aux);
            if(n_bits > (8-Pos))
            {
                int restants = n_bits - (8-Pos);
                int despl = 8-Pos;
                aux = byteToConversion.shift_right_logic(b, despl);
                Pos = 0;
                add(aux, restants);
            }
            else
            {
                Pos += n_bits;
                if(Pos > 7) Pos -= 8;
            }
        }
    }
    
    /**
     * @fn public void print()
     * @brief 
     */
    public void print()
    {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] bArray = new byte[Out.size()];
        for (int i = 0; i < bArray.length; i++)
        {
            Byte bAux = Out.get(i);

            bArray[i] = bAux;
        }
        
        try {
            dataOutputStream.write(bArray, 0, bArray.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //for(Byte b: Out) System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));

    }

    /**
     * @fn public void printString()
     * @brief 
     */
    public void printString()
    {
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        char[] cArray = new char[Out.size()];
        for (int i = 0; i < cArray.length; i++)
        {
            char cAux = (char)(byte)Out.get(i);

            cArray[i] = cAux;
        }
        
        try {
            outputStreamWriter.write(cArray, 0, cArray.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(Byte b: Out) System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));

    }

    /**
     * @fn public ArrayList<Byte> getOut() 
     * @return Retorna la llista de bytes de la classe
     */
    public ArrayList<Byte> getOut() {
        return Out;
    }

    /**
     * @fn public Integer getPos()
     * @return Retorna la posició de l'arxiu
     */
    public Integer getPos() {
        return Pos;
    }

    /**
     * @fn public String getPath
     * @return Retorna el path de l'arxiu
     */
    public String getPath() {
        return path;
    }


}