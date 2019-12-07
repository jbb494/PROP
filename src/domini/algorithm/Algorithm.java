package domini.algorithm;

import persistencia.output.Ctrl_Output;

/**
 * @class Algorithm
 * @brief Classe de Algorithm
 * @author Joan Lapeyra Amat
 */
public abstract class Algorithm {

    /**
	 * @param Output on escriu la compressió o descompressió
	 */
    protected Ctrl_Output Output;

    /**
	 *  @param decode_or_encode indica si és compressió (true) o descompressió (false)
	*/
	private boolean decode_or_encode; // if true, decode; otherwise, encode

    /**
	 * @brief Constructor de la clase
	 * @param path Path del fitxer de sortida que es començarà a escriure
	 * @param b False si estas comprimint, True si estas descomprimint
	 */
    public Algorithm(String path, boolean b) {
        Output = new Ctrl_Output(path);
        decode_or_encode = b;
    }

    /**
	 * @brief Constructor de la classe
	 * @param b False si estas comprimint, True si estas descomprimint
     * @note Es continuarà escivint al fitxer on s'estava escrivint.
	 */
    public Algorithm(boolean b) {
        Output = new Ctrl_Output();
        decode_or_encode = b;
    }

    /**
	 * @brief Constructor de la classe
     * @note Es continuarà escivint al fitxer on s'estava escrivint.
	 */
    public Algorithm() {
        Output = new Ctrl_Output();
        decode_or_encode = true;
    }

}