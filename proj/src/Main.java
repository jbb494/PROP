/**
 * @class Main
 * @brief Classe Main
 * @author Joan Lapeyra
 */
import presentacion.master.Ctrl_Master;

public class Main {

    /**
     * @brief Main de la classe
     * @param args
     */
    public static void main(String[] args) 
    {   
        while(true)
        {
            Ctrl_Master CM = new Ctrl_Master();
            CM.Context();
            System.out.println(CM.Work());
        }
    }
}
