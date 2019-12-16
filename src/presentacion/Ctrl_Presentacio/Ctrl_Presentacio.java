package presentacion.Ctrl_Presentacio;

import domini.folders.Ctrl_FolderFile;
import global.global;

import java.io.IOException;

public class Ctrl_Presentacio {
    Ctrl_FolderFile CF;
    private global.type type;

    public Ctrl_Presentacio(String path_input) {
        CF = new Ctrl_FolderFile(path_input);
        type = CF.getType(path_input);
    }

    public String EncodeAuto() {
        return CF.EncodeAuto();
    }

    public String Encode(String text_method, double img_quality) {
        String estadistica = "";
        if (type == global.type.folder){
            estadistica = CF.EncodeManualFolder(text_method, img_quality);
        }
        else if(type == global.type.image) {
            estadistica = CF.EncodeManualImg(img_quality);
        }
        else if (type == global.type.text) {
            estadistica = CF.EncodeManualText(text_method);
        }
        return estadistica;
    }

    public String Decode() {
        return CF.Decode();
    }

    public String DecodeTemp() {
        return CF.DecodeTemp();
    }

    public global.type getType()
    {
        return type;
    }

    public String getFile(String pathTemp) throws IOException {
        return CF.getFile(pathTemp);
    }
}
