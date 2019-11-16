/**
 * @class Main
 * @brief Classe Main
 * @author
 */
import presentacion.master.Ctrl_Master;

public class Main {


    public static void print_binary(Byte b) {
        System.out.println(Integer.toBinaryString(b));
    }

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
