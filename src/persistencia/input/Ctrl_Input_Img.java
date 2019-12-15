package persistencia.input;

import domini.utils.byteToConversion;

/**
 * @class Ctrl_Input_Img
 * @brief Controlador de Input que permet llegir una imatge ppm
 * @author Joan Lapeyra Amat
 */
public class Ctrl_Input_Img extends Ctrl_Input {

    /**
     * @param max_val Valor màxim que poden prendre els enters que representan quantitat de color.
     */
    int max_val;
    /**
     * @param height Alçada de la imatge
     */
    int height;
    /**
     * @param width Amplada de la imatge
     */
    int width;
    /**
     * @param bits_per_val Quantitat de bits per valor.
     */
    int bits_per_val;

    /**
     * @brief Constructor de la classe.
     * Llegeix les dades de la capçalera ppm (magic number, width, height, max_val). 
     * @param path Path de l'arxiu que comença a llegir.
     */
    public Ctrl_Input_Img(String path) {
        super(path);
        String str = getWord();
        if (! str.equals("P6")) {
            throw new IllegalArgumentException("Magic number of "+path+" is "+str+". It should be P6. It may not be in ppm format.");
        }
        width = getIntASCII();
        height = getIntASCII();
        max_val = getIntASCII();
        if (max_val < 256) bits_per_val = 8;
        else               bits_per_val = 16;
    }

    /**
     * @brief Constructor de la classe.
     * Llegeix les dades de la capçalera ppm (magic number, width, height, max_val)
     * @note Continua llegint el fitxer que s'estava llegint.
     */
    public Ctrl_Input_Img() {
        super();
        String str = getWord();
        if (! str.equals("P6")) {
            throw new IllegalArgumentException("Magic number is "+str+". It should be P6. It may not be in ppm format.");
        }
        width = getIntASCII();
        height = getIntASCII();
        max_val = getIntASCII();
        if (max_val < 256) bits_per_val = 8;
        else               bits_per_val = 16;
    }

    /**
     * @fn public int getHeight()
     * @brief Retorna l'alçada de la imatge
     * @return Alçada
     */
    public int getHeight() {
        return height;
    }
    /**
     * @fn public int getWidth()
     * @brief Retorna l'amplada de la imatge
     * @return Amplada
     */
    public int getWidth() {
        return width;
    }
    
    /** 
     * @fn public double[][][][] get()
     * @brief Retorna una matriu ret[width/8][8][8][3],
     * on ret[block][x][y][color] 
     *      és el la qantitat de color 'color' (rgb)
     *      al píxel (x+8*n, y+8*block), 
     *      on 8*n son les files llegides abans d'executar get() i n és natural
     * Les quantitats de color s'expressen en reals entre 0 i 255
     * @return Retorna la matriu ret
     */
    public double[][][][] get()
    {
        int n_blocks = width/8;
        double[][][][] ret = new double[n_blocks][8][8][3];
        for (int x = 0; x < 8; ++x) {
            for (int j = 0; j < 8*n_blocks; ++j) {
                for (int color = 0; color < 3; ++color) {
                    int val = byteToConversion.byteToInteger(Input.getInstance().getMoreBits(bits_per_val));
                    ret[j/8][x][j%8][color] = (double)val / (double)max_val * 255.0;
                }
            }
            Input.getInstance().getMoreBits((width - 8*n_blocks)*3*bits_per_val);
        }
        return ret;
    }

    ////////////

    /**
     * @fn private char getChar()
     * @brief Llegeix un byte
     * @return Retorna el byte llegit en forma de ceràcter
     */
    private char getChar() {
        return (char)Input.getInstance().getBits(8);
    }

    /**
     * @fn private String getWord()
     * @brief Llegeix caràcters fins al pròxim whitespace
     * @return Retorna la paraula llegida
     */
    private String getWord() {
        String str = "";
        char c;

        do c = getChar(); 
        while (Character.isWhitespace(c));

        do {
            str += c;
            c = getChar();
        } while (!Character.isWhitespace(c));

        return str;
        
    }

    /**
     * @fn private char getChar()
     * @brief Llegeix caràcters fins al pròxim whitespace.S'espera que representi un número
     * @return Retorna el número llegit
     */
    private int getIntASCII() {
        return Integer.parseInt( getWord() );
    }
}