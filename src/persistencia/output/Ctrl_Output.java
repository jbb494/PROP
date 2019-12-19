package persistencia.output;

import java.nio.ByteBuffer;
import java.util.ArrayList;


/**
 * @class Ctrl_Output
 * @brief Classe Ctrl_Output
 * @file

 * 
 * Cal tenir en compte que no es poden escriure dos fitxers simultàniament.
 * El fitxer que escriuen les instàncies de Ctrl_Output i els seus fills s'inicialitza 
 * cada cop que es crida a initialize(String path) o Ctrl_Output*(String path), 
 * on Ctrl_Output* és Ctrl_Output o un dels seus fills.
 * 
 * @author Miguel Paracuellos
 */
public class Ctrl_Output {
    
    //Constructor
    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu que comença a escriure
     */
    public Ctrl_Output(String path) {
        Output.initialize(path);
    }

    /**
     * @brief Constructor de la classe
     * @note Continua escrivint al fitxer al qual s'estava escrivint.
     */
    public Ctrl_Output() {}


    //Functions

    /**
     * @fn public static void initialize(String path)
     * @brief Assigna un nou fitxer per esciure.
     * @param path referencia el fitxer en qüestió.
     * @note Més info a la descripció de la classe.
     */
    public static void initialize(String path) {
        Output.initialize(path);
    }

    /**
     * @brief Afageix la metadata corresponent a un determinat algorisme.
     * @param method És l'algorisme, del qual depèn la metadata.
     */
    public void addMetadata(String method) {
        if (method.toLowerCase().equals("lzss"))
            add((byte)2, 2);
        else if(method.toLowerCase().equals("lzw"))
            add((byte)1, 2);
        else if(method.toLowerCase().equals("lz78"))
            add((byte)0, 2);
        else if(method.toLowerCase().equals("jpeg"))
            add((byte)3, 2);
    }

    /**
     * @fn public void add(Byte b, Integer n_bits) 
     * @brief Afegeix un byte amb n_bits valids
     * @param b Byte a afegir
     * @param n_bits Nombre de bits valids
     */
    public void add(Byte b, Integer n_bits) {
        Output.getInstance().add(b, n_bits);
    }

    /**
     * @fn public void add(String s)
     * @brief Afegeix un String
     * @param s String a afegir
     */
    public void add(String s) {
        byte[] bytearray = s.getBytes();
        for (byte b : bytearray) 
            Output.getInstance().add(b,8);
    }

    /**
     * @fn public void add(Character c)
     * @brief Afegeix un Character
     * @param c Character a afegir
     */
    public void add(Character c) {
        //System.out.println(c);
        add(Character.toString(c));
    }

    /**
     * @fn public void add(Integer x)
     * @brief Afegeix un enter de 4 bytes. Big endian (bytes més significatius primer).
     * @param x Enter a afegir
    */
    public void add(Integer x) {
        ByteBuffer bb = ByteBuffer.allocate(4); 
        bb.putInt(x.intValue());
        for (byte b : bb.array()) 
            add(b,8);
    }

    /**
     * @fn public void add(Integer x, Integer mida)
     * @brief Afegeix un enter amb mida. Big endian (bytes més significatius primer).
     * @param x Enter a afegir
     * @param mida Mida del enter que volem afegir
     */
    public void add(Integer x, Integer mida){

        ByteBuffer bb = ByteBuffer.allocate(4); 
        bb.clear();
        Integer midaAux = mida%8;
        Double daux = new Double(mida)/8;
        Integer nombreBytes = new Double (Math.ceil(daux)).intValue();
        if(midaAux == 0)midaAux = 8;
        bb.putInt(x.intValue());
        for (int i = 4-nombreBytes; i < 4; i++)
        {
            byte b = bb.get(i);
            add(b,midaAux);
            midaAux = 8;
        }
    }

    /**
     * @fn public void add(ArrayList<Byte> arr)
     * @brief Afegeix una llista de Bytes
     * @param arr Llista a afegir
     */
    public void add(ArrayList<Byte> arr){
        for(Byte b : arr){
            add(b,8);
        }
    }

    /**
     * @fn public void add2(int x, int mida)
     * @brief Afegeix un enter amb mida. Little endian (bytes menys significatius primer).
     * @param x Int a afegir.
     * @param mida Mida de "x".
     */
    public void add2(int x, int mida) 
    //least significant bytes first
    {
        while(mida > 0) {
            int m;
            if (mida < 8) m = mida;
            else m = 8;
            byte aux = (byte)(x & ~(-1 << m));
            add(aux, m);
            x = x >> 8;
            mida -= 8;
        }
    }

    /**
     * @fn public void print()
     * @brief Escriu el buffer a la sortida i buida el buffer.
     */
    public void print() {   
        Output.getInstance().print();
    }


}
