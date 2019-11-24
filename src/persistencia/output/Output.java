package persistencia.output;

import java.util.*;
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
     * @param dataOutputStream
     */
    DataOutputStream dataOutputStream;

    /**
     * @brief Constructor de la classe
     * @param path Path de sortida
     */
    public Output(String path) {
        Pos = 0;
        this.path = path;
        next_byte = 0;

        dataOutputStream = null;
        try {
            System.out.println(path);
            dataOutputStream = new DataOutputStream(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void write() {

        try {
            byte[] aux = {(byte)next_byte};
            dataOutputStream.write(aux, 0, 1);
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
    }

    /*
     * @fn public void printString()
     * @brief Crea un fitxer de sortida i també mostra per consola el contingut d'aquest.
     */

    /* aquesta funció ha quedat inservible perquè ha desaparegut l'array 'Out'
       He vist que només l'ultilitzen els drivesr (mirjançant Ctrl_Output::printString()),
       per tant no crec que sigui un problema eliminar-la */

    /*public void printString()
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
        
    }*/


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
