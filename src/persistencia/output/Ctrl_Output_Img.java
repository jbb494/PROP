package persistencia.output;

import domini.utils.stringConversion;

/**
 * @class Ctrl_Input_Img
 * @brief Controlador de Output que permet escriure una imatge ppm
 * @author Joan Lapeyra Amat
 */
public class Ctrl_Output_Img extends Ctrl_Output {

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
     * @param rows Nombre de files llegides.
     */
    int rows;

    /**
     * @brief Constructor de la classe.
     * Escriu les dades de la capçalera ppm (magic number, width, height, max_val)
     * @param path Path de l'arxiu output
     */
    public Ctrl_Output_Img(String path, int w, int h, int mv) {
        super(path, "", true);

        if (mv <= 0 || mv >= 65536) {
            throw new IllegalArgumentException("The maximum color value (Maxval) must be less than 65536 and more than zero.");
        }
        if (w < 0 || h < 0) {
            throw new IllegalArgumentException("Width and height must be non-negative.");
        }

        width = (w/8)*8;
        height = (h/8)*8;
        max_val = mv;
        rows = 0;

        String s1 =
            "P6 " +
            stringConversion.intToString(width) + " " + 
            stringConversion.intToString(height) + " " +
            stringConversion.intToString(max_val) + " ";
        add(s1);

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
     * @fn public int get(int size)
     * @bief Escriu l'entrada en format ppm
     * @param mat Les dimensions de mat son [width/8][8][8][3],
     *   on mat[block][x][y][color] 
     *       és el la qantitat de color 'color' (rgb)
     *       al píxel (x+8*n, y+8*block), 
     *       on 8*n son les files escrites abans d'executar add() i n és natural
     *   Les quantitats de color s'expressen en reals entre 0 i max_val
     */
    public void add(double[][][][] mat)
    {
        int n_blocks = width/8; //width de moment és múltiple de 8
        rows += 8;
        for (int x = 0; x < 8; ++x) {
            for (int j = 0; j < 8*n_blocks; ++j) {
                for (int color = 0; color < 3; ++color) {
                    int val = (int)mat[j/8][x][j%8][color];
                    if (bits_per_val == 16) {
                        Byte b = (byte)(val >> 8);
                        add(b,8);
                    }
                    Byte b = (byte)val;
                    add(b,8);
                }
            }
        }
    }

    /**
     * @fn public boolean finished()
     * @brief inica si s'han llegit totes les files de la imatge.
     */
    public boolean finished() {
        return (rows >= height);
    }
}