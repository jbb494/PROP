package presentacion.Ctrl_Presentacio;

import domini.folders.Ctrl_FolderFile;

public class Ctrl_Presentacio {
    Ctrl_FolderFile CF;
    String type;
    public String FolderOrFile;

    public Ctrl_Presentacio(String path_input) {
        CF = new Ctrl_FolderFile(path_input);
        FolderOrFile = "";
    }

    public void EncodeAuto() {
        CF.EncodeAuto();
    }
    public void Encode(String text_method, double img_quality) {
        if (type == "carpeta"){
            CF.EncodeManualFolder(text_method, img_quality);
        } else if(type == "imatge") {
            CF.EncodeManualImg(img_quality);
        } else if (type == "fitxer") {
            CF.EncodeManualText(text_method);
        }
        CF.EncodeManualText(text_method);
    }
    public void Decode() {
        CF.Decode();
    }

    public void FolderOrFile(String Path)
    {
        FolderOrFile = CF.FolderOrFile(Path);
    }
    public String isFolderOrFile()
    {
        return FolderOrFile;
    }

}
