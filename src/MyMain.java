import domini.estadistica.Estadistica;
import domini.folders.Ctrl_FolderFile;

public class MyMain {

    /**
     * @brief De moment no l'elimineu
     * @param args
     */
    public static void main(String[] args) 
    {
        
        

        for (int q = 0; q <= 100; q += 20) {
            String in = "persistencia/data/" + args[0] + "_" + Integer.toString(q) + ".ppm";


            System.out.println();
            System.out.println();
            System.out.println("QUALITAT " + q + ":");

            Estadistica est = new Estadistica(true);
            Ctrl_FolderFile cff = new Ctrl_FolderFile(in);
            String comp = cff.EncodeManualImg((double)q);
            est.work_done();
            est.show_estadistica(in, comp);
            
            cff = new Ctrl_FolderFile(comp);
            cff.Decode();
        }
    }
}