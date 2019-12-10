/**
 * @class Main
 * @brief Classe Main
 * @author Joan Lapeyra
 */
import presentacion.form.mainForm;
import presentacion.master.Ctrl_Master;

public class Main {

    /**
     * @brief Main de la classe
     * @param args
     */
    public static void main(String[] args)
    {
//        while(true)
//        {
//            Ctrl_Master CM = new Ctrl_Master();
//            CM.Context();
//            System.out.println(CM.Work());
//        }
        java.awt.EventQueue.invokeLater (
                new Runnable() {
                    public void run() {
                        mainForm frame = new mainForm();
                        frame.inicialitza_panel();
                    }
                }
        );
    }
}
