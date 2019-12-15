package presentacion.form;

import domini.algorithm.LZW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import global.global;

public class PopUp_Comp extends JDialog {
    private JRadioButton LZWRadioButton;
    private JRadioButton LZ78RadioButton;
    private JRadioButton LZSSRadioButton;
    private JRadioButton bajoRadioButton;
    private JRadioButton medioRadioButton;
    private JRadioButton altoRadioButton;
    private JButton aceptarButton;
    public JPanel Compresión;
    private JLabel ArchTexto;
    private JLabel GradoJPEG;
    private boolean enableAccept1;
    private boolean enableAccept2;
    private global.type type;


    public PopUp_Comp(global.type type) {
        super();
        setModal(true);
        setResizable(false);
        this.type = type;
        enableAccept1 = enableAccept2 = false;
        $$$setupUI$$$();
        if (type == global.type.image) {
            LZWRadioButton.setEnabled(false);
            LZ78RadioButton.setEnabled(false);
            LZSSRadioButton.setEnabled(false);
            ArchTexto.setEnabled(false);
        } else if (type == global.type.text) {
            bajoRadioButton.setEnabled(false);
            medioRadioButton.setEnabled(false);
            altoRadioButton.setEnabled(false);
            GradoJPEG.setEnabled(false);
        }
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((LZWRadioButton.isSelected() || LZ78RadioButton.isSelected()
                        || LZSSRadioButton.isSelected()) && (bajoRadioButton.isSelected() ||
                        medioRadioButton.isSelected() || altoRadioButton.isSelected())) {
                    dispose();
                } else {
                    System.out.println("ERROR SELECCIONA ALMENYS UNA OPCIO");
                }
            }
        });


        LZWRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableAccept1 = true;
                if (type == global.type.text) aceptarButton.setEnabled(true);
                else if (type == global.type.folder && enableAccept2) aceptarButton.setEnabled(true);
            }
        });
        LZ78RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableAccept1 = true;
                if (type == global.type.text) aceptarButton.setEnabled(true);
                else if (type == global.type.folder && enableAccept2) aceptarButton.setEnabled(true);
            }
        });
        LZSSRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableAccept1 = true;
                if (type == global.type.text) aceptarButton.setEnabled(true);
                else if (type == global.type.folder && enableAccept2) aceptarButton.setEnabled(true);
            }
        });
        bajoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableAccept2 = true;
                if (type == global.type.image) aceptarButton.setEnabled(true);
                else if (type == global.type.folder && enableAccept1) aceptarButton.setEnabled(true);
            }
        });
        medioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableAccept2 = true;
                if (type == global.type.image) aceptarButton.setEnabled(true);
                else if (type == global.type.folder && enableAccept1) aceptarButton.setEnabled(true);
            }
        });
        altoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableAccept2 = true;
                if (type == global.type.image) aceptarButton.setEnabled(true);
                else if (type == global.type.folder && enableAccept1) aceptarButton.setEnabled(true);
            }
        });
    }

    String getMethod() {
        String ret = "";
        if (LZWRadioButton.isSelected()) {
            ret = "lzw";
        } else if (LZ78RadioButton.isSelected()) {
            ret = "lz78";
        } else if (LZ78RadioButton.isSelected()) {
            ret = "lzss";
        }
        return ret;
    }

    float getGc_jpeg() {
        float ret = 0.5f;
        if (bajoRadioButton.isSelected()) {
            ret = 0.f;
        } else if (medioRadioButton.isSelected()) {
            ret = 0.5f;
        } else if (altoRadioButton.isSelected()) {
            ret = 1.f;
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
        Compresión.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 6, new Insets(20, 20, 20, 20), -1, -1));
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
        Compresión.add(LZWRadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LZ78RadioButton = new JRadioButton();
        LZ78RadioButton.setBackground(new Color(-14013910));
        LZ78RadioButton.setFocusPainted(false);
        LZ78RadioButton.setForeground(new Color(-1));
        LZ78RadioButton.setText("LZ78");
        Compresión.add(LZ78RadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LZSSRadioButton = new JRadioButton();
        LZSSRadioButton.setBackground(new Color(-14013910));
        LZSSRadioButton.setFocusPainted(false);
        LZSSRadioButton.setForeground(new Color(-1));
        LZSSRadioButton.setText("LZSS");
        Compresión.add(LZSSRadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bajoRadioButton = new JRadioButton();
        bajoRadioButton.setBackground(new Color(-14013910));
        bajoRadioButton.setFocusPainted(false);
        bajoRadioButton.setForeground(new Color(-1));
        bajoRadioButton.setText("Bajo");
        Compresión.add(bajoRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        medioRadioButton = new JRadioButton();
        medioRadioButton.setBackground(new Color(-14013910));
        medioRadioButton.setFocusPainted(false);
        medioRadioButton.setForeground(new Color(-1));
        medioRadioButton.setText("Medio");
        Compresión.add(medioRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        altoRadioButton = new JRadioButton();
        altoRadioButton.setBackground(new Color(-14013910));
        altoRadioButton.setFocusPainted(false);
        altoRadioButton.setForeground(new Color(-1));
        altoRadioButton.setText("Alto");
        Compresión.add(altoRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        aceptarButton = new JButton();
        aceptarButton.setBackground(new Color(-12961222));
        aceptarButton.setBorderPainted(false);
        aceptarButton.setEnabled(false);
        aceptarButton.setFocusPainted(false);
        aceptarButton.setFocusable(true);
        aceptarButton.setForeground(new Color(-1));
        aceptarButton.setText("Aceptar");
        Compresión.add(aceptarButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
