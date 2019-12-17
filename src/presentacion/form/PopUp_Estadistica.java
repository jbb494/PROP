package presentacion.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp_Estadistica extends JDialog {

    /**
     * @param panel1 Panel principal del form.
     */
    public JPanel panel1;

    /**
     * @param lab Label mostrando las estadísticas.
     */
    private JLabel lab;

    /**
     * @param cerrarButton Botón para cerrar el form.
     */
    private JButton cerrarButton;

    /**
     * @brief Constructor de la classe
     * @param s Indica el texto a mostrar en el form
     */
    public PopUp_Estadistica(String s) {
        super();
        setModal(false);
        setResizable(false);
        $$$setupUI$$$();
        lab.setText(s);

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }


    /**
     * @fn private void $$$setupUI$$$()
     * @brief Método generado por IntelliJ IDEA.
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(20, 20, 20, 20), -1, -1));
        panel1.setBackground(new Color(-14013910));
        panel1.setEnabled(false);
        panel1.setPreferredSize(new Dimension(500, 200));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cerrarButton = new JButton();
        cerrarButton.setBackground(new Color(-12961222));
        cerrarButton.setBorderPainted(false);
        cerrarButton.setFocusPainted(false);
        cerrarButton.setForeground(new Color(-1));
        cerrarButton.setText("Cerrar");
        panel1.add(cerrarButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(10, 20), null, 0, false));
        lab = new JLabel();
        lab.setBackground(new Color(-14013910));
        lab.setForeground(new Color(-1));
        lab.setText("Label");
        panel1.add(lab, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @fn public JComponent $$$getRootComponent$$$()
     * @brief Método generado por IntelliJ IDEA.
     * 
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}

/** @class PopUp_Estadistica
 *  @brief Esta es la clase del form que abre las estadísticas.
 *
 *  @author
 */