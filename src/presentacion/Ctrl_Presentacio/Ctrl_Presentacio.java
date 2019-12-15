package presentacion.Ctrl_Presentacio;

import domini.folders.Ctrl_FolderFile;
import global.global;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Ctrl_Presentacio {
    Ctrl_FolderFile CF;
    private global.type FolderOrFile;

    public Ctrl_Presentacio(String path_input) {
        CF = new Ctrl_FolderFile(path_input);
        FolderOrFile = CF.FolderOrFile(path_input);
    }

    public void EncodeAuto() {
        CF.EncodeAuto();
    }
    public void Encode(String text_method, double img_quality) {
        if (FolderOrFile == global.type.folder){
            CF.EncodeManualFolder(text_method, img_quality);
        } else if(FolderOrFile == global.type.image) {
            CF.EncodeManualImg(img_quality);
        } else if (FolderOrFile == global.type.text) {
            CF.EncodeManualText(text_method);
        }
    }
    public void Decode() {
        CF.Decode();
    }
    public String DecodeTemp() {
        return CF.DecodeTemp();
    }
    public global.type isFolderOrFile()
    {
        return FolderOrFile;
    }

    public String getFile(String pathTemp) throws IOException {
        return CF.getFile(pathTemp);
    }
}
