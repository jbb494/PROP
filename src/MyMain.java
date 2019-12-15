import domini.folders.Ctrl_FolderFile;

public class MyMain {

    /**
     * @brief De moment no l'elimineu
     * @param args
     */
    public static void main(String[] args) 
    {
        Ctrl_FolderFile cff = new Ctrl_FolderFile(args[0]);
        String path = cff.EncodeAuto();
        cff = new Ctrl_FolderFile(path);
        path = cff.Decode();
        System.out.println("path: "+path);
    }
}