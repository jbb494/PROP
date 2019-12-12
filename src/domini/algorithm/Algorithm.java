package domini.algorithm;

import persistencia.output.Ctrl_Output;

/**
 * @class Algorithm
 * @brief Classe de Algorithm
 * És la superclasse de tots els algoritmes: JPEG, LZ78, LZW i LZSS
 * @author Joan Lapeyra Amat
 */
public abstract class Algorithm {

    /**
	 * @param Output on escriu la compressió o descompressió
	 */
    protected Ctrl_Output Output;

    /**
	 *  @param decompressing indica si és descompressió (true) o descompressió (false)
	*/
	private boolean decompressing; // if true, decode; otherwise, encode

    /**
	 * @brief Constructor de la clase
	 * @param path Path del fitxer de sortida que es començarà a escriure
	 * @param b False si estas comprimint, True si estas descomprimint
	 */
    public Algorithm(String path, boolean b) {
        Output = new Ctrl_Output(path);
        decompressing = b;
    }

    /**
	 * @brief Constructor de la classe
	 * @param b False si estas comprimint, True si estas descomprimint
     * @note Es continuarà escivint al fitxer on s'estava escrivint.
	 */
    public Algorithm(boolean b) {
        Output = new Ctrl_Output();
        decompressing = b;
    }

    /**
	 * @brief Constructor de la classe
     * @note Es continuarà escivint al fitxer on s'estava escrivint.
	 */
    public Algorithm() {
        Output = new Ctrl_Output();
        decompressing = true;
    }


    /**
     * @fn public abstract void Compressor()
     * @brief Comprimeix el fitxer que s'està llegint
     * @note L'algorisme emprat depèn de la subclasse
     */
    public abstract void Compressor();
    /**
     * @fn public abstract void Decompressor()
     * @brief Descomprimeix el fitxer que s'està llegint
     * @note L'algorisme emprat depèn de la subclasse
     */
    public abstract void Decompressor();


    /**
     * @fn protected void checkCompressor()
     * @brief Llança una excepció si la instància no és compressora
     */
    protected void checkCompressor() {
        if (decompressing) {
            throw new IllegalArgumentException(
                "S'ha intentat comprimir des d'una instància inicialitzada com a descompressora."
            );
        }
    }
    /**
     * @fn protected void checkDecompressor()
     * @brief Llança una excepció si la instància no és descompressora
     */
    protected void checkDecompressor() {
        if (!decompressing) {
            throw new IllegalArgumentException(
                "S'ha intentat descomprimir des d'una instància inicialitzada com a compressora."
            );
        }
    }

    /**
	 * @fn public Ctrl_Output print()
	 * @brief Escriu a l'output el que quedava per escriure.
	 */
	public void print() {
        Output.print();
	}
}