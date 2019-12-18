package persistencia.Utils;

import global.global;

import java.io.File;

public abstract class FolderOrFile {

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
