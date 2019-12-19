package persistencia.browser;

import java.io.File;
import java.io.IOException;

/**
 * @class Ctrl_Browser
 * @brief Classe Ctrl_Browser
 * @file

 * @author Joan Lapeyra
 */
public class Ctrl_Browser {
    private File file;

    /**
     * @brief Constructora
     * @param path path del fitxer o directori referenciat.
     */
    public Ctrl_Browser(String path) {
        file = new File(path);
    }

    /**
     * @fn public String getAbsolutePath()
     * @return el path absolut
     */
    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    /**
     * @fn public boolean exists()
     * @return true <=> existeix un fitxer o una carpeta al path especificat
     */
    public boolean exists() {
        return file.exists();
    }

    /**
     * @fn public boolean isDirectory()
     * @return true <=> this referencia a un directori
     */
    public boolean isDirectory() {
        return file.isDirectory();
    }

    /**
     * @fn public String mkdir(String name)
     * @brief Crea un directori en el el path referenciat per this
     */
    public void mkdir() {
        file.mkdir();
    }

    /**
     * @fn public String[] list()
     * @return Els noms dels fills del directori referenciat per this
     */
    public String[] list() {
        if (!isDirectory()) throw new IllegalArgumentException(
            "Aquesta instància de Ctrl_Browser no fa referènca a un directori");
        return file.list();
    }

    /**
     * @fn public long length()
     * @return La mida del fitxer o carpeta, entenient la mida de la carpeta com la 
     * suma de les mides dels deus fills més la mida de la seva metadata.
     */
    public long length() {
        if (!file.isDirectory()) return file.length();
        long len = file.length();
        for (File f : file.listFiles()) {
            Ctrl_Browser browser = new Ctrl_Browser(f.getAbsolutePath());
            len += browser.length();
        }
        return len;
    }

    /**
     * @brief Crea un fitxer temporal al directori de sistema per a fitxers temporals.
     * El fitxer serà esborrat al final de l'execució del programa.
     * @param extension extensio del fitxer creat
     * @return path del fitxer creat
     */
    public static String createTempFile(String extension) {
        File f = null;
        try {
            f = File.createTempFile("temporal", "."+extension);
            f.deleteOnExit();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }

}
