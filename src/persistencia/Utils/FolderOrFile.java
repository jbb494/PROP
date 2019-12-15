package persistencia.Utils;

import java.io.File;

public abstract class FolderOrFile {

    public static String FolderOrFile(String Path)
    {
        File f = new File(Path);
        boolean aux = f.isDirectory();
        if(aux) return "Carpeta";
        else
        {
            if(Path.endsWith(".ppm")) return "Imatge";
            else return "Fitxer";
        }
    }
}
