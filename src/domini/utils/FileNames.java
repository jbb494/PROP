package domini.utils;

/**
 * @class FileNames
 * @brief Classe FileNames
 * @file

 * @author Joan Lapeyra
 */
public class FileNames {
    /**
     * @fn public static String getPrefix(String path)
     * @param path un camí a un fitxer o directori
     * @return El camí sense l'extensió
     */
    public static String getPrefix(String path) {
        int i = path.lastIndexOf(".");
        if (i == -1) return path;
        return path.substring(0, i);
    }

    /**
     * @fn public static String getExtension(String path)
     * @param path un camí a un fitxer o directori
     * @return L'extensió, en cas d'haver-n'hi. La paraula buida si no s'hi ha.
     */
    public static String getExtension(String path) {
        int i = path.lastIndexOf(".");
        if (i == -1) return "";
        return path.substring(i+1);
    }

    /**
     * @fn public static String getName(String path)
     * @param path un camí a un fitxer o directori
     * @return El nom del fitxer (amb extensió) o directori
     */
    public static String getName(String path) {
        int i = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"));
        return path.substring(i+1);
    }

    /**
     * @fn public static String getName(String path)
     * @param path un camí a un fitxer o directori
     * @return El path del directori pare (la paraula buida si no està indicat)
     */
    public static String getLocation(String path) {
        int i = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"));
        if (i == -1) return "";
        return path.substring(0, i);
    }

    /**
     * @fn public static String getName(String path)
     * @param path un camí a un fitxer o directori
     * @return El nom del fitxer o directori sense extensió
     */
    public static String getPrefixName(String path) {
        return getName(getPrefix(path));
    }

    public static String getPath(String prefix, String extension) {
        if (extension.isEmpty()) return prefix;
        return prefix + "." + extension;
    }

    
}