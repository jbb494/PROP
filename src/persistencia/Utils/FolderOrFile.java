package persistencia.Utils;

import global.global;

import java.io.File;

public abstract class FolderOrFile {

    public static global.type FolderOrFile(String Path)
    {
        File f = new File(Path);
        if(f.isDirectory()) return global.type.folder;
        else
        {
            if(Path.endsWith(".ppm")) return global.type.image;
            else return global.type.text;
        }
    }
}
