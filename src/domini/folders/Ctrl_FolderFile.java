package domini.folders;


import java.io.File;

import domini.folders.FileNames;

/**
 * @class Ctrl_Folders
 * @brief Classe Ctrl_Folders
 * @author Joan Lapeyra
 */
public class Ctrl_FolderFile {

    private String original_path;

    /**
     * @brief Constructor de la classe
     */
    public Ctrl_FolderFile(String path) {}

    
    public void Encode(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            EncodeDirectory(path);
        }
        else {
            EncodeFile(path);
        }
    }

    private void EncodeFile(String path) {

    }

    private void EncodeDirectory(String path) {
        
    }

    public void Decode(String Path) {
        
    }

}