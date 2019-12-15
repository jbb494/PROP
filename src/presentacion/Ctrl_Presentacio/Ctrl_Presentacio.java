package presentacion.Ctrl_Presentacio;

import domini.folders.Ctrl_FolderFile;

public class Ctrl_Presentacio {
    Ctrl_FolderFile CF;

    public Ctrl_Presentacio(String path_input) {
        CF = new Ctrl_FolderFile(path_input);
    }

    public void EncodeAuto() {
        CF.EncodeAuto();
    }
    public void Decode() {
        CF.Decode();
    }

}
