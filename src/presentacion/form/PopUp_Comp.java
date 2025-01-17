package presentacion.form;


import global.global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp_Comp extends JDialog {

    /**
     * @param LZWRadioButton RadioButton del algoritmo LZW.
     */
    private JRadioButton LZWRadioButton;

    /**
     * @param LZ78RadioButton RadioButton del algoritmo LZ78.
     */
    private JRadioButton LZ78RadioButton;

    /**
     * @param LZSSRadioButton RadioButton del algoritmo LZSS.
     */
    private JRadioButton LZSSRadioButton;

    /**
     * @param bajoRadioButton RadioButton del grado de compresión del algoritmo JPEG bajo.
     */
    private JRadioButton bajoRadioButton;

    /**
     * @param medioRadioButton RadioButton del grado de compresión del algoritmo JPEG medio.
     */
    private JRadioButton medioRadioButton;

    /**
     * @param altoRadioButton RadioButton del grado de compresión del algoritmo JPEG alto.
     */
    private JRadioButton altoRadioButton;

    /**
     * @param aceptarButton Button para aceptar las decisiones tomadas.
     */
    private JButton aceptarButton;

    /**
     * @param Compresión Panel principal del form.
     */
    public JPanel Compresión;

    /**
     * @param ArchTexto Label con información sobre algoritmos.
     */
    private JLabel ArchTexto;

    /**
     * @param GradoJPEG Label con información sobre el grado de compresión del algoritmo JPEG.
     */
    private JLabel GradoJPEG;

    /**
     * @param ExitBut Button para salir del form.
     */
    private JButton ExitBut;

    /**
     * @param Accepted Boolean que indica si se ha apretado el botón aceptarButton.
     */
    private boolean Accepted;

    /**
     * @param type Enumeration que indica el tipo de archino sobre el que se ejecuta el form.
     */
    private global.type type;


    /**
     * @param type Tipo de fichero sobre el que se ejecuta la compresión manual
     * @brief Constructor de la clase
     */
    public PopUp_Comp(global.type type) {
        super();
        Accepted = false;
        setModal(true);
        setResizable(false);
        this.type = type;
        $$$setupUI$$$();
        if (type == global.type.image) {
            LZWRadioButton.setEnabled(false);
            LZ78RadioButton.setEnabled(false);
            LZSSRadioButton.setEnabled(false);
            ArchTexto.setEnabled(false);
            bajoRadioButton.setSelected(true);
        } else if (type == global.type.text) {
            bajoRadioButton.setEnabled(false);
            medioRadioButton.setEnabled(false);
            altoRadioButton.setEnabled(false);
            GradoJPEG.setEnabled(false);
            LZWRadioButton.setSelected(true);
        } else if (type == global.type.folder) {
            bajoRadioButton.setSelected(true);
            LZWRadioButton.setSelected(true);
        }

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Accepted = true;
                dispose();
            }
        });


        LZWRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LZWRadioButton.setSelected(true);
                LZ78RadioButton.setSelected(false);
                LZSSRadioButton.setSelected(false);
            }
        });

        LZ78RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LZWRadioButton.setSelected(false);
                LZ78RadioButton.setSelected(true);
                LZSSRadioButton.setSelected(false);
            }
        });
        LZSSRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LZWRadioButton.setSelected(false);
                LZ78RadioButton.setSelected(false);
                LZSSRadioButton.setSelected(true);
            }
        });
        ExitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }

    /**
     * @return Devuelve un tipo de la enumeration dependiendo del radioButton seleccionado.
     * @fn global.method getMethod()
     * @brief Método para saber el tipo de algoritmo seleccionado de los radioButton's.
     */
    global.method getMethod() {
        global.method ret = global.method.lzw;
        if (LZWRadioButton.isSelected()) {
            ret = global.method.lzw;
        } else if (LZ78RadioButton.isSelected()) {
            ret = global.method.lz78;
        } else if (LZSSRadioButton.isSelected()) {
            ret = global.method.lzss;
        }
        return ret;
    }

    /**
     * @return Devuelve el atributo Accepted.
     * @fn public boolean getAccepted()
     */
    public boolean getAccepted() {
        return Accepted;
    }

    /**
     * @return Devuelve un tipo de la enumeration dependiendo del radioButton seleccionado.
     * @fn float getGc_jpeg()
     * @brief Método para saber el grado de compresión seleccionado de los radioButton's.
     */
    float getGc_jpeg() {
        float ret = 0;
        if (bajoRadioButton.isSelected()) {
            ret = 100;
        } else if (medioRadioButton.isSelected()) {
            ret = 70;
        } else if (altoRadioButton.isSelected()) {
            ret = 40;
        }
        return ret;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        Compresión = new JPanel();
        Compresión.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 5, new Insets(20, 20, 20, 20), -1, -1));
        Compresión.setBackground(new Color(-14013910));
        Compresión.setEnabled(false);
        ArchTexto = new JLabel();
        ArchTexto.setForeground(new Color(-1));
        ArchTexto.setText("Archivo de texto:");
        Compresión.add(ArchTexto, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GradoJPEG = new JLabel();
        GradoJPEG.setForeground(new Color(-1));
        GradoJPEG.setText("Grado de Compresion .ppm:");
        Compresión.add(GradoJPEG, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LZWRadioButton = new JRadioButton();
        LZWRadioButton.setBackground(new Color(-14013910));
        LZWRadioButton.setFocusPainted(false);
        LZWRadioButton.setForeground(new Color(-1));
        LZWRadioButton.setText("LZW");
        Compresión.add(LZWRadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LZ78RadioButton = new JRadioButton();
        LZ78RadioButton.setBackground(new Color(-14013910));
        LZ78RadioButton.setFocusPainted(false);
        LZ78RadioButton.setForeground(new Color(-1));
        LZ78RadioButton.setText("LZ78");
        Compresión.add(LZ78RadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LZSSRadioButton = new JRadioButton();
        LZSSRadioButton.setBackground(new Color(-14013910));
        LZSSRadioButton.setFocusPainted(false);
        LZSSRadioButton.setForeground(new Color(-1));
        LZSSRadioButton.setText("LZSS");
        Compresión.add(LZSSRadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bajoRadioButton = new JRadioButton();
        bajoRadioButton.setBackground(new Color(-14013910));
        bajoRadioButton.setFocusPainted(false);
        bajoRadioButton.setForeground(new Color(-1));
        bajoRadioButton.setText("Bajo");
        Compresión.add(bajoRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        medioRadioButton = new JRadioButton();
        medioRadioButton.setBackground(new Color(-14013910));
        medioRadioButton.setFocusPainted(false);
        medioRadioButton.setForeground(new Color(-1));
        medioRadioButton.setText("Medio");
        Compresión.add(medioRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        altoRadioButton = new JRadioButton();
        altoRadioButton.setBackground(new Color(-14013910));
        altoRadioButton.setFocusPainted(false);
        altoRadioButton.setForeground(new Color(-1));
        altoRadioButton.setText("Alto");
        Compresión.add(altoRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ExitBut = new JButton();
        ExitBut.setBackground(new Color(-12961222));
        ExitBut.setBorderPainted(false);
        ExitBut.setFocusPainted(false);
        ExitBut.setForeground(new Color(-1));
        ExitBut.setText("Salir");
        Compresión.add(ExitBut, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        aceptarButton = new JButton();
        aceptarButton.setBackground(new Color(-12961222));
        aceptarButton.setBorderPainted(false);
        aceptarButton.setEnabled(true);
        aceptarButton.setFocusPainted(false);
        aceptarButton.setFocusable(true);
        aceptarButton.setForeground(new Color(-1));
        aceptarButton.setText("Aceptar");
        Compresión.add(aceptarButton, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(LZWRadioButton);
        buttonGroup.add(LZ78RadioButton);
        buttonGroup.add(LZSSRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(bajoRadioButton);
        buttonGroup.add(medioRadioButton);
        buttonGroup.add(altoRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Compresión;
    }

}

/** @class PopUp_Comp
 *  @brief Clase del form para seleccionar la compresión manual.
 * @file
 *
 *  @author Miguel Paracuellos
 */