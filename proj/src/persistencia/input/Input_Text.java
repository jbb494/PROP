/**
 * @class Input_Text
 * @brief Classe Input_Text
 * @author 
 */
package persistencia.input;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import domini.utils.byteToConversion;

// Necesitaremos inputreader y bufferreader
// https://funnelgarden.com/java_read_file/ --> FileInputStream

public class Input_Text  {

    /**
     * @param arrayByte
     */
    ArrayList<Byte> arrayByte;
    
    /**
     * @param inputStreamReader
     */
    private DataInputStream inputStreamReader;
    
    /**
     * @param punter
     */
    private int punter;
    
    /**
     * @param pos
     */
    private int pos;


    /* write your code here */
    // Scanner obtenerNumero = new Scanner(System.in);
    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu a tractar
     */
    public Input_Text(String path) {
        try {
            punter = 0;
            pos = 0;
            FileInputStream dataInputStream = new FileInputStream(new File(/*"../" NO*/path));
            arrayByte = new ArrayList<Byte>(0);
            inputStreamReader = new DataInputStream(dataInputStream);
            read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null)
                    inputStreamReader.close();
            } catch (Exception ex2) {
                System.out.println("Msg 2: " + ex2.getMessage());
            }
        }

    }

    /**
     * @fn private void read()
     * @brief Llegeix bytes de l'arxiu 
     */
    private void read() {
        Integer c;
        try {
            while ((c = inputStreamReader.read()) != -1) {
                Byte aux = c.byteValue();
                arrayByte.add(aux);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   
    }
    
    /**
     * @fn public byte getBits(int num_bits)
     * @param num_bits Especifica el nombre de bits que volem de l'input (num_bits -> {0,8})
     * @return Retorna un byte amb el nombre valid de bits que demanem
     */
    public byte getBits(int num_bits) {
        byte b = arrayByte.get(pos);
        b = byteToConversion.shift_right_logic(b, punter);
        if(punter + num_bits > 8)
        {
            pos++;
            int mou = 8 - punter;
            int restants = punter + num_bits - 8;
            punter = 0;
            b = (byte) (b | (getBits(restants) << mou));
        }
        else
        {
            b = (byte) (b & ~(byte) (0xff << num_bits)); // neteja els bits brossa a posicions altes
            punter += num_bits;
            if(punter > 7)
            {
                pos++;
                punter -= 8;
            }
        }
        return b;
    }

    /*public byte getBits(int pos_bit, int num_bits) {
        return getBits(pos_bit/8, pos_bit%8, num_bits);
    }*/

    /**
     * @fn public ArrayList<Byte> getIn()
     * @return Retorna l'atribut de la classe
     */
    public ArrayList<Byte> getIn()
    {
        return arrayByte;

    }
}

