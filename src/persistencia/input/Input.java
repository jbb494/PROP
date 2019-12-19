package persistencia.input;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @param bin
     */
    private BufferedInputStream bin;

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
     * @param frag_length llargada del fragment
     */
    private long frag_length;

    /**
     * @param read_bytes nombre de bytes llegits del fragment
     */
    private long read_bytes;

    /**
     * @param end Indica si hem arribat al final de l'arxiu o el fragment
     */
    private boolean end;

    /**
     * @param illegals Nombre de bits que s'han intentat llegir pero queden fora del fitxer
     */
    private int illegals;

    

    /**
     * @param instance Única instància de Input, siguint el patró singleton
     */
    private static Input instance = null;

    /**
     * @fn public static Input getInstance()
     * @brief Retorna l'única instància de Input, siguint el patró singleton
     */
    public static Input getInstance() {
        if(instance == null) throw new IllegalArgumentException("Input not initialized. You must call initialize(String)");
        return instance;
    }

    /**
     * @fn public static void initialize(String path)
     * @brief Reinicialitza l'úniaca instància de Input, com a ampliació del patró singleton
     * @param path Path de l'arxiu d'Input
     */
    public static void initialize(String path) {
        instance = new Input(path);
    }

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu d'Input
     */
    private Input(String path) {
        try {   
            frag_length = Long.MAX_VALUE;
            read_bytes = 0;
            illegals = 0;
            end = false;

            punter = 0;
            FileInputStream fin = new FileInputStream(path);
            bin = new BufferedInputStream(fin);
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
        read_bytes++;
        if (read_bytes <= frag_length) {
            try {
                if ((c = bin.read()) != -1) {
                    last_byte = c.byteValue();
                }
                else {
                    end = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            end = true;
            last_byte = 0;
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
     * @return Indica si hem arribat al final del fitxer o fragment
     */
    public boolean finished() {
        return end;
    }

    /**
     * @fn public int outOfFile()
     * @return Retorna el nombre de bits que s'han intentat llegir però queden fora del fitxer o fragment
     */
    public int outOfFile() { //nombre de bits que s'han intentat llegir però queden fora del fitxer o fragment
        return illegals;
    }

    /**
     * @fn public String getDecodeAlg()
     * @return Retorna l'algoritme que hem d'emprar per la descompressio
     */
    public String getDecodeAlg() //0 si lz78, 1 si lzw o 2 si lzss
    {
        /*if(gotExtension) {
            throw new IllegalArgumentException("Ya hemos cogido la metada");
        }
        this.gotExtension = true;*/
        byte aux = getBits(2);
        if(aux == (byte)0) return "lz78";
        else if(aux == (byte)1) return "lzw";
        else if(aux == (byte)2) return "lzss";
        else return "jpeg";
    }

    /**
     * @fn void ignoreTheRestOfTheByte()
     * @breaf Si el pròxim bit a llegir no és el primer bit d'un byte,
     *   avança la lectura fins a l'inici del proper byte
     */
    public void ignoreTheRestOfTheByte() {
        punter = 0;
    }

    /**
     * @fn public void startFragment(long length)
     * @brief Comença a llegir un fragment. 
     * Considerarem que el fragment son els length bytes a partir del byte que estem llegint en aquest moment.
     * @param length llargada del fragment
     */
    public void startFragment(long length) {
        frag_length = length;
        read_bytes = 0;
        end = false;
    }
}
