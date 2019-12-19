/**
 * @class Main
 * @brief Classe Main
 * @file
 * @author Joan Lapeyra
 */
import presentacion.form.mainForm;

public class Main {

    /**
     * @brief Main del projecte
     * @param args
     */
    public static void main(String[] args)
    {
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
