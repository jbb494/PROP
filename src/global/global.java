/**
 * @file
 * */

package global;

public abstract class global {

    public enum type{
        folder, ///< Tipo Carpeta
        text, ///< Tipo Texto
        image, ///< Tipo Imagen
        comprimit, ///< Tipo Comprimido
        imageComprimit, ///< Tipo Imagen Comprimida
        textComprimit, ///< Tipo Texto Comprimido
        folderComprimit ///<Tipo Carpeta Comprimida
    }

    public enum method {
        lz78, ///< Tipo de algoritmo LZ78
        lzw,///< Tipo de algoritmo LZW
        lzss, ///< Tipo de algoritmo LZSS
        jpeg ///< Tipo de algoritmo JPEG
    }
    public enum jpegCompression {
        low, ///< Grado de Compresión JPEG bajo
        medium, ///< Grado de Compresión JPEG medio
        high ///< Grado de Compresión JPEG alto
    }
}

/** @class global
 *  @brief Clase que contiene diferentes tipos de enumeration.
 * @file
 *
 *  @author Joan Bellavista
 */
