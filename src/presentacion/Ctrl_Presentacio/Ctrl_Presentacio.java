package presentacion.Ctrl_Presentacio;

import domini.folders.Ctrl_FolderFile;

public class Ctrl_Presentacio implements Runnable {
    Ctrl_FolderFile CF;

    public Ctrl_Presentacio(String path_input) {
        CF = new Ctrl_FolderFile(path_input);
    }

    public void Encode() {

    }
    public void Decode() {

    }

    @Override
    public void run() {
        CF.EncodeManualText("/home/joan");
    }
}
