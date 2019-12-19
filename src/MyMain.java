import domini.estadistica.Estadistica;
import domini.folders.Ctrl_FolderFile;
import domini.utils.*;

public class MyMain {

    /**
     * @brief De moment no l'elimineu
     * @param args
     */
    public static void main(String[] args) 
    {
        
        

        for (int q = 40; q <= 100; q += 30) {
            String prefix = "C:/Users/Joan/Desktop/UNI/Q5/PROP/REPOSITORI/experiments jpeg/" + args[0] + "_" + Integer.toString(q);


            System.out.println();
            System.out.println();
            System.out.println("QUALITAT " + q + ":");

            
            Ctrl_FolderFile cff = new Ctrl_FolderFile(prefix + ".ppm");
            String est = cff.EncodeManualImg((double)q);
            System.out.println(est);
            System.out.println();
            
            
            cff = new Ctrl_FolderFile(prefix + ".jm");
            cff.Decode();
        }
    }
}