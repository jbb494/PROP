package persistencia.output;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @class Ctrl_Output
 * @brief Classe Ctrl_Output
 * @author Miguel Paracuellos
 */
public class Ctrl_Output {
    
    //Attributes
    /**
     * @param Output_Class Classe Output com atribut de la classe
     */
    Output Output_Class;

    //Constructor
    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu resultant de l'escritura
     * @param method Indicara el valor de la metadata segons l'algoritme emprat
     * @param b Indicació per a txt o ppm
     */
    public Ctrl_Output(String path, String method, boolean b) {
        Output_Class = new Output(path);
        
        if (!b) {
            if (method.toLowerCase().equals("lzss"))
                add((byte)2, 2);
            else if(method.toLowerCase().equals("lzw"))
                add((byte)1, 2);
            else if(method.toLowerCase().equals("lz78"))
                add((byte)0, 2);
            else if(method.toLowerCase().equals("jpeg"))
                add((byte)3, 2);
        }
    }

    //Functions
    /**
     * @fn public void add(Byte b, Integer n_bits) 
     * @brief Afegeix un byte amb n_bits valids
     * @param b Byte a afegir
     * @param n_bits Nombre de bits valids
     */
    public void add(Byte b, Integer n_bits) {
        Output_Class.add(b, n_bits);
    }

    /**
     * @fn public void add(String s)
     * @brief Afegeix un String
     * @param s String a afegir
     */
    public void add(String s) {
        byte[] bytearray = s.getBytes();
        for (byte b : bytearray) 
            Output_Class.add(b,8);
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
     * @brief Afegeix un enter de 4 bytes
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
     * @brief Afegeix un enter amb mida. Guarda els bits més significatius primer.
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
     * @brief Afegeix un enter amb mida. Guarda els bits menys significatius primer.
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
     * @brief Escriu el contingut de la classe en un nou arxiu
     */
    public void print() {   
        Output_Class.print();
    }

    /**
     * @fn public void printString()
     * @brief Escriu el contingut de la classe per consola byte a byte (en format binari)
     */
    /* aquesta funció ha quedat inservible perquè ha desaparegut l'array 'Out' de la classe Output
       He vist que només l'ultilitzen els drivers, per tant no crec que sigui un problema eliminar-la.*/
    /*public void printString() {
        Output_Class.printString();
    }*/
    
    /**
     * @fn public Output getOut()
     * @return Retorna l'atribut de tipus Output de la classe
     */
    public Output getOut() {
        return Output_Class;
    }

}
