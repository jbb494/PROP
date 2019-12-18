package persistencia.Utils;

import global.global;

import java.io.File;

public abstract class FolderOrFile {

    /**
     * @fn public static global.type getType(String Path)
     * @param Path Path a buscar
     * @return Devuelve el tipo de fichero/carpeta que contiene el Path seleccionado.
     */
    public static global.type getType(String Path)
    {
        File f = new File(Path);
        if(f.isDirectory()) return global.type.folder;
        else
        {
            if(Path.endsWith(".ppm")) return global.type.image;
            else if (Path.endsWith(".jm")) return global.type.comprimit;
            else return global.type.text;
        }
    }
}
/** @class FolderOrFile
 *  @brief Clase abstracta que consulta al SO el tipo de Path que nos han pasado (carpeta, fichero, ...).
 *
 *  @author
 */
