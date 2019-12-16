package persistencia.input;

/**
* @class Ctrl_Input_JPEG
* @brief Classe Ctrl_Input_JPEG
* @author Joan Lapeyra
*/
public class Ctrl_Input_JPEG extends Ctrl_Input {

    /**
     * @param height Alçada de la imatge
     */
    int height;
    /**
     * @param width Amplada de la imatge
     */
    int width;
    /**
     * @param max_val Valor màxim que tenien els codis de color a la imatge original.
     */
    int max_val;

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu que comença a lelgir.
     */
    public Ctrl_Input_JPEG(String path) {
        super(path);
        getMetadata();
        width = get(32);
        height = get(32);
        max_val = get(32);
    }

    /**
     * @brief Constructor de la classe
     * @note Continua llegint el fitxer que s'estava llegint. Assumeix que la metadata ja ha estat llegida.
     */
    public Ctrl_Input_JPEG() {
        super();
        width = get(32);
        height = get(32);
        max_val = get(32);
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
     * @fn public int getMaxVal()
     * @brief Retorna el valor màxim que poden prendre els enters que representan quantitat de color. 
     * @return Valor màxim que poden prendre els enters que representan quantitat de color.
     */
    public int getMaxVal() {
        return max_val;
    }
 
    /**
     * @fn public int get(int size)
     * @bief llageix 'size' bits
     * @return Retorna els bits llegits en forma de int. 
     * Els primers bits llegits son els menys significatius.
     */
    public int get(int size) {
        if (size == 0) return 0;
        int sz;
        if (size < 8) sz = size;
        else sz = 8;

        int x = ((int)Input.getInstance().getBits(sz)) & ~(0xffffffff << sz);
        if (size > 8) x = x + (get(size-8) << 8);
        return x;
    }
        
}
