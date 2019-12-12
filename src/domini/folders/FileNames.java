package domini.folders;

/**
 * @class FileNames
 * @brief Classe FileNames
 * @author Joan Lapeyra
 */
public class FileNames {
    /**
     * @fn public static String getPrefix(String path)
     * @param path un camí a un fitxer o carpeta
     * @return El camí sense l'extensió
     */
    public static String getPrefix(String path) {
        int i = path.lastIndexOf(".");
        if (i == -1) return path;
        return path.substring(0, i);
    }

    /**
     * @fn public static String getName(String path)
     * @param path un camí a un fitxer o carpeta
     * @return El nom del fitxer (amb extensió) o carpeta
     */
    public static String getName(String path) {
        int i = path.lastIndexOf("/");
        return path.substring(i+1);
    }
}