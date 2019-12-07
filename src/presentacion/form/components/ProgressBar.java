package presentacion.form.components;

import javax.swing.*;

public class ProgressBar extends JProgressBar {

    int num;

    public ProgressBar ()
    {
        super();
        num = 0;
    }

    public void calcula_barra()
    {
        while(num < 100)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){ }
            num += 2;
        }
    }
}
