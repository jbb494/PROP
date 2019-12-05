package persistencia.output;

import java.util.*;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import domini.utils.byteToConversion;

/**
 * @class Output
 * @brief Classe Output
 * @author Manel Aguilar
 */
public class Output {

    

    /**
     * @param Pos Posició en la que ens trobem al byte actual.
     */
    Integer Pos;

    /**
     * @param path Path de sortida de l'arxiu
     */
    String path;

    /**
     * @param next_byte Ultim byte llegit
     */
    private Byte next_byte; 

    /**
     * @param buff
     */
    BufferedOutputStream buff;

    /**
     * @brief Constructor de la classe
     * @param path Path de sortida
     */
    public Output(String path) {
        Pos = 0;
        this.path = path;
        next_byte = 0;

        buff = null;
        try {
            System.out.println(path);
            FileOutputStream fout = new FileOutputStream(path);
            buff = new BufferedOutputStream(fout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void write() {

        try {
            byte[] aux = {(byte)next_byte};
            buff.write(aux, 0, 1);
            //buff.flush();
            next_byte = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * @fn public void add(byte b, int n_bits)
     * @brief Afegeix els n_bits de manys pes d'un byte
     * @param b Byte a afegir
     * @param n_bits Nombre de bits vàlids
     * @note Pre: 0 <= n_bits <= 8
     */
    public void add(byte b, int n_bits) 
    {
        if(n_bits != 0)
        {
            byte aux;
            b = (byte)(b & ~(0xff << n_bits)); //neteja dels els bits no vàlids a posicions altes
            aux = (byte)(b << Pos);
            next_byte = (byte)(aux | next_byte);
            
            if (Pos + n_bits > 8) 
            {
                write();
                int restants = n_bits - (8-Pos);
                int despl = 8-Pos;
                aux = byteToConversion.shift_right_logic(b, despl);
                Pos = 0;
                add(aux, restants);
            }
            else if (Pos + n_bits == 8) 
            {
                write();
                Pos = 0;
            }
            else // Pos + n_bits < 8
            {
                Pos += n_bits;
            }
        }
    }
    
    /**
     * @fn public void print()
     * @brief Crea el fitxer de sortida a path
     */
    public void print()
    {
        if (Pos > 0) write();
        try {
            buff.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @fn public Integer getPos()
     * @return Retorna la posició del pròxim bit a ecriure dins del byte a escriure
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
