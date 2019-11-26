package presentacion.form;

import javax.swing.*;

public class MainForm extends JFrame{
    private JPanel rootPanel;
    private JButton button1;

    public MainForm() {
        super();
        setVisible(true);

        add(rootPanel);

        setTitle("Title App");
        setSize(400, 500);

    }
}
