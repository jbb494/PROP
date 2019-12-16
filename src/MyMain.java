import domini.folders.Ctrl_FolderFile;

public class MyMain {

    /**
     * @brief De moment no l'elimineu
     * @param args
     */
    public static void main(String[] args) 
    {
        Ctrl_FolderFile cff = new Ctrl_FolderFile(args[0]);
        System.out.println(cff.isEncoded());
        System.out.println(cff.isEncodedFile());
        System.out.println(cff.isEncodedFolder());
        System.out.println(cff.getEncodedExtension());
    }
}