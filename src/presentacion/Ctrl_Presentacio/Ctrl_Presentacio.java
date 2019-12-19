package presentacion.Ctrl_Presentacio;

import domini.folders.Ctrl_FolderFile;
import global.global;

import java.io.IOException;

public class Ctrl_Presentacio {

    /**
     * @param CF Instancia Ctrl_FolderFile.
     */
    Ctrl_FolderFile CF;

    /**
     * @param type Enumeration para saber el tipo de un fichero determinado.
     */
    private global.type type;

    /**
     * @brief Constructor de la clase.
     * @param path_input Path a comprimir/descomprimir.
     */
    public Ctrl_Presentacio(String path_input) {
        CF = new Ctrl_FolderFile(path_input);
        type = CF.getType(path_input);
        switch (type) {
            case textComprimit:

                break;
            case imageComprimit:

                break;
        }
    }

    /**
     * @fn public String EncodeAuto()
     * @brief Método para la compresión automática.
     * @return Devuelve estadísticas de la compresión.
     */
    public String EncodeAuto() {
        return CF.EncodeAuto();
    }

    /**
     * @fn public String Encode(String text_method, double img_quality)
     * @brief Método para la compresión manual.
     * @param text_method Modo de compresión del posible fichero.
     * @param img_quality Grado de compresión de la posible imagen.
     * @return Devuelve estadísticas de la compresión.
     */
    public String Encode(String text_method, double img_quality) {
        String estadistica = "";
        if (type == global.type.folder){
            estadistica = CF.EncodeManualFolder(text_method, img_quality);
        }
        else if(type == global.type.image) {
            estadistica = CF.EncodeManualImg(img_quality);
        }
        else if (type == global.type.text) {
            estadistica = CF.EncodeManualText(text_method);
        }
        return estadistica;
    }

    /**
     * @fn public String Decode()
     * @brief Método para la descompresión.
     * @return Devuelve estadísticas de la descompresión.
     */
    public String Decode() {
        return CF.Decode();
    }

    /**
     * @fn public String DecodeTemp()
     * @brief Método para la descompresión de un fichero temporal.
     * @return Devuelve estadísticas de la descompresión.
     */
    public String DecodeTemp() {
        return CF.DecodeTemp();
    }

    /**
     * @fn public global.type getType()
     * @return Devuelve el atributo type.
     */
    public global.type getType()
    {
        return type;
    }
}

/** @class Ctrl_Presentacio
 *  @brief Clase Ctrl_Presentacio
 * @file
 *
 *  @author Joan Bellavista
 */