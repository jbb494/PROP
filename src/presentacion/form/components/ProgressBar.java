package presentacion.form.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBar extends JProgressBar {

    private int MAX;
    private ActionListener updateBar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int val = getValor();
            System.out.println(val);
            if(val >= MAX)
            {
                timer.stop();
                return;
            }
            setValor(++val);
        }
    };
    private Timer timer = new Timer(50, updateBar);

    public ProgressBar (int min, int max)
    {
        super(min, max);
        this.MAX = max;
        timer.start();
    }

    private int  getValor()
    {
        return this.getValue();
    }

    private void  setValor(int val)
    {
        this.setValue(val);
    }
}
