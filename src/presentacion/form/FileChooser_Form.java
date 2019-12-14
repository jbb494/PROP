package presentacion.form;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileChooser_Form extends JFrame {

    private JFileChooser jfc;

    public FileChooser_Form() {
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }

    public String getPath() {
        String ret = "";

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            ret = selectedFile.getAbsolutePath();
        }
        return ret;
    }
}
