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

/**
 * @class Input
 * @brief Classe Input
 * @author Joan Bellavista
 */
public class Input {
    
    /**
     * @param inputStreamReader
     */
    private DataInputStream inputStreamReader;

    /**
     * @param punter Indica el proper bit que ha de ser llegit del byte en qüestió
     * @note 0 <= punter < 8
     */
    private int punter; 

    /**
     * @param last_byte Ultim byte llegit
     */
    private Byte last_byte; 

    /**
     * @param end Indica si hem arribat al final de l'arxiu 
     */
    private boolean end;

    /**
     * @param illegals Nombre de bits que s'han intentat llegir pero queden fora del fitxer
     */
    private int illegals;
    
    /**
     * @param gotExtension Indica si l'arxiu te extensió definida per treballar
     */
    private Boolean gotExtension = false;

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu d'Input
     */
    public Input(String path) {
        try {   
            end = false;
            illegals = 0;
            punter = 0;
            FileInputStream dataInputStream = new FileInputStream(new File(path));
            inputStreamReader = new DataInputStream(dataInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { //saltaven excepcions fins que he comentat aquestes línies i no entenc per què
                //if (inputStreamReader != null);
                //    inputStreamReader.close();
            } catch (Exception ex2) {
                System.out.println("Msg 2: " + ex2.getMessage());
            }
        }

    }

    /**
     * @fn private void read()
     * @brief Llegeix el proxim byte de l'arxiu
     */
    private void read() {
        Integer c;
        try {
            if ((c = inputStreamReader.read()) != -1) {
                last_byte = c.byteValue();
            }
            else {
                end = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @fn public byte getBits(int num_bits)
     * @param num_bits Nombre de bits que volem que ens retorni ( 1 <= num_bits <= 8)
     * @return Retorna un byte amb num_bits bits valids
     */
    public byte getBits(int num_bits) 
    // pre: 1 <= num_bits <= 8        <-- IMPORTANT!!!
    {
        if(num_bits < 1 || num_bits > 8)throw new IllegalArgumentException("num_bits tiene que ser un valor entre 1 y 8 incluidos");
        if (end) {
            illegals += num_bits;
            return 0;
        }
        if (punter == 0) {
            read();
            if (end) {
                illegals += num_bits;
                return 0;
            }
        }
        
        byte b = byteToConversion.shift_right_logic(last_byte, punter);
        if(punter + num_bits > 8) 
        {
            int mou = 8 - punter;
            int restants = punter + num_bits - 8;
            punter = 0;
            b = (byte) (b | (getBits(restants) << mou));
        }
        else if (punter + num_bits == 8) 
        {
            punter = 0;
        }
        else //punter + num_bits < 8
        { 
            b = (byte) (b & ~(byte) (0xff << num_bits)); // neteja els bits brossa a posicions altes
            punter += num_bits;
        }
        return b;
    }

    /**
     * @fn public ArrayList<Byte> getMoreBits(int num_bits)
     * @param num_bits Nombre de bits que volem que ens retorni
     * @return ArrayList de Bytes amb num_bits bits valids
     */
    public ArrayList<Byte> getMoreBits(int num_bits) 
    // cap limitació en el valor de num_bits
    {
        ArrayList<Byte> al = new ArrayList<Byte>();
        while (num_bits > 0) {
            Byte b = getBits(Math.min(num_bits, 8));
            al.add(b);
            num_bits -= 8;
        }
        return al;
    }

    //Passar la funció a Ctrl_Input
    /**
     * @fn public boolean finished()
     * @return Retorna si hem arribat al final de l'arxiu o no
     */
    public boolean finished() {
        return end;
    }

    /**
     * @fn public int outOfFile()
     * @return Retorna el nombre de bits que s'han intentat llegir però queden fora del fitxer
     */
    public int outOfFile() { //nombre de bits que s'han intentat llegir però queden fora del fitxer
        return illegals;
    }

    /**
     * @fn public String getDecodeAlg()
     * @return Retorna l'algoritme que hem d'emprar per la descompressio
     */
    public String getDecodeAlg() //0 si lz78, 1 si lzw o 2 si lzss
    {
        if(gotExtension) {
            throw new IllegalArgumentException("Ya hemos cogido la metada");
        }
        this.gotExtension = true;
        byte aux = getBits(2);
        if(aux == (byte)0) return "lz78";
        else if(aux == (byte)1) return "lzw";
        else if(aux == (byte)2) return "lzss";
        else return "jpeg";
    }
}
