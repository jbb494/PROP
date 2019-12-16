package presentacion.form;

import global.global;
import presentacion.Ctrl_Presentacio.Ctrl_Presentacio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Function;

import presentacion.form.components.ProgressBar;

public class mainForm extends JFrame {
    private JPanel panel1;
    private JButton Exit;
    private JButton descomprimirButton;
    private JButton ChoosePathButton;
    private JPanel Panel2;
    private JLabel LabelPath;
    private JPanel PanelPath;
    private JCheckBox previewCheckBox;
    private JEditorPane AreaPre;
    private JScrollPane PanelPre;
    private JButton ComprimirBut;
    private JButton ButMenu;
    private JPanel PanelCompr;
    private ProgressBar ProgressPre;
    private JPanel PaneBarText;

    private Ctrl_Presentacio CP;
    private PopUp_Estadistica pop_est;
    private Thread t;

    public mainForm() {
        super("Compresor/Descompresor");
        $$$setupUI$$$();

        ChoosePathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileChooser_Form fc = new FileChooser_Form();
                String path = fc.getPath();
                String s = path.length() >= 30 ? "...".concat(path.substring(path.length() - 30)) : path;
                if (s != "") {
                    LabelPath.setText(s);
                    if (s != "Seleccionar Path") {
                        previewCheckBox.setEnabled(true);
                        ComprimirBut.setEnabled(true);
                        ButMenu.setEnabled(true);
                        descomprimirButton.setEnabled(true);
                        CP = new Ctrl_Presentacio(path);

                        if (previewCheckBox.isSelected()) {

                            System.out.println(CP.getType().toString());
                            switch (CP.getType()) {
                                case imageComprimit:
                                    System.out.println(PanelPre.getParent());
                                    if (PanelPre.getParent() != null) {
                                        PaneBarText.remove(PanelPre);
                                        PaneBarText.validate();
                                        PaneBarText.repaint();
                                    }
                                    break;
                                case textComprimit:
                                    if (PanelPre.getParent() == null) {
                                        PaneBarText.add(PanelPre, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
                                        PaneBarText.validate();
                                        PaneBarText.repaint();
                                    }
                                    break;
                            }

                            String pathTemp = CP.DecodeTemp();
                            String file = null;
                            System.out.println("decoded");
                            try {
                                System.out.println("FINISHED");
                                file = CP.getFile(pathTemp);
                                AreaPre.setText(file);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        ButMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PopUp_Comp pop = new PopUp_Comp(getFolderOrFile());
                pop.setContentPane(pop.Compresión);
                pop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                pop.pack();
                pop.setVisible(true);

                if (pop.getAccepted()) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            t = new Thread(new Runnable() {
                                @Override
                                public synchronized void run() {
                                    String est = CP.Encode(pop.getMethod().toString(), pop.getGc_jpeg());
                                    show_est(est);
                                }
                            });

                            Thread t2 = new Thread(new Runnable() {
                                public Thread aux = getThread();
                                @Override
                                public synchronized void run() {
                                    generando_estadistica("compresion");
                                    aux.start();
                                }
                            });
                            t2.start();
                        }
                    });
                }
            }
        });

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        previewCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ProgressPre.setVisible(previewCheckBox.isSelected());
                PanelPre.setVisible(previewCheckBox.isSelected());
            }
        });


        ComprimirBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                t = new Thread(new Runnable() {
                                    @Override
                                    public synchronized void run() {
                                        String est = CP.EncodeAuto();
                                        show_est(est);
                                    }
                                });

                                Thread t2 = new Thread(new Runnable() {
                                    public Thread aux = getThread();
                                    @Override
                                    public synchronized void run() {
                                        generando_estadistica("compresion");
                                        aux.start();
                                    }
                                });
                                t2.start();
                            }
                        });
                    }
                });
            }
        });

        descomprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        t = new Thread(new Runnable() {
                            @Override
                            public synchronized void run() {
                                String est = CP.Decode();
                                show_est(est);
                            }
                        });

                        Thread t2 = new Thread(new Runnable() {
                            public Thread aux = getThread();
                            @Override
                            public synchronized void run() {
                                generando_estadistica("descompresión");
                                aux.start();
                            }
                        });
                        t2.start();
                    }
                });
            }
        });
    }

    private void show_est(String s) {
        pop_est.dispose();
        pop_est = new PopUp_Estadistica(s);
        pop_est.setContentPane(pop_est.panel1);
        pop_est.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pop_est.pack();
        pop_est.setVisible(true);
    }

    private void generando_estadistica(String s) {
        String txt = "<html> <h1> Generando la " + s + "</h1>";
        pop_est = new PopUp_Estadistica(txt);
        pop_est.setContentPane(pop_est.panel1);
        pop_est.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pop_est.pack();
        pop_est.setVisible(true);
    }

    private Thread getThread() {
        return t;
    }

    public global.type getFolderOrFile() {
        return CP.getType();
    }

    public void inicialitza_panel() {
        setContentPane(new mainForm().panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 2, new Insets(20, 20, 20, 20), -1, -1));
        panel1.setAlignmentX(0.0f);
        panel1.setAlignmentY(0.0f);
        panel1.setBackground(new Color(-14013910));
        Font panel1Font = this.$$$getFont$$$("Ubuntu Mono", -1, 28, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        panel1.setForeground(new Color(-12632257));
        panel1.setMinimumSize(new Dimension(400, 200));
        panel1.setPreferredSize(new Dimension(500, 300));
        panel1.setRequestFocusEnabled(true);
        panel1.setVisible(true);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Fira Code Medium", Font.BOLD, 16, panel1.getFont()), new Color(-4932680)));
        Exit = new JButton();
        Exit.setBackground(new Color(-12632257));
        Exit.setBorderPainted(false);
        Exit.setFocusPainted(false);
        Exit.setForeground(new Color(-1));
        Exit.setText("Salir");
        panel1.add(Exit, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Panel2 = new JPanel();
        Panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        Panel2.setBackground(new Color(-14013910));
        Panel2.setForeground(new Color(-12632257));
        panel1.add(Panel2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        descomprimirButton = new JButton();
        descomprimirButton.setBackground(new Color(-12632257));
        descomprimirButton.setBorderPainted(false);
        descomprimirButton.setEnabled(false);
        descomprimirButton.setFocusPainted(false);
        descomprimirButton.setForeground(new Color(-1));
        descomprimirButton.setText("Descomprimir");
        Panel2.add(descomprimirButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        Panel2.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        Panel2.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        PanelCompr = new JPanel();
        PanelCompr.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, -1));
        PanelCompr.setBackground(new Color(-12632257));
        Panel2.add(PanelCompr, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ComprimirBut = new JButton();
        ComprimirBut.setBackground(new Color(-12632257));
        ComprimirBut.setBorderPainted(false);
        ComprimirBut.setEnabled(false);
        ComprimirBut.setFocusPainted(false);
        ComprimirBut.setForeground(new Color(-1));
        ComprimirBut.setText("Comprimir");
        PanelCompr.add(ComprimirBut, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButMenu = new JButton();
        ButMenu.setBackground(new Color(-12632257));
        ButMenu.setBorderPainted(false);
        ButMenu.setEnabled(false);
        ButMenu.setFocusPainted(false);
        ButMenu.setIcon(new ImageIcon(getClass().getResource("/presentacion/imatgesInterficie/1 (1).png")));
        ButMenu.setText("");
        PanelCompr.add(ButMenu, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), null, new Dimension(40, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        PanelPath = new JPanel();
        PanelPath.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        PanelPath.setBackground(new Color(-11447983));
        panel1.add(PanelPath, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 25), null, 0, false));
        LabelPath = new JLabel();
        LabelPath.setBackground(new Color(-1));
        LabelPath.setForeground(new Color(-1));
        LabelPath.setHorizontalAlignment(11);
        LabelPath.setText("Seleccionar Path");
        PanelPath.add(LabelPath, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(300, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        PanelPath.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ChoosePathButton = new JButton();
        ChoosePathButton.setBackground(new Color(-11447983));
        ChoosePathButton.setBorderPainted(false);
        ChoosePathButton.setFocusPainted(false);
        ChoosePathButton.setIcon(new ImageIcon(getClass().getResource("/presentacion/imatgesInterficie/dsnjkfghjkdfg (1).png")));
        ChoosePathButton.setText("");
        PanelPath.add(ChoosePathButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), new Dimension(40, 30), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        PanelPath.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 5), new Dimension(-1, 15), null, 0, false));
        PaneBarText = new JPanel();
        PaneBarText.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        PaneBarText.setBackground(new Color(-14013910));
        panel1.add(PaneBarText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelPre = new JScrollPane();
        PanelPre.setBackground(new Color(-14013910));
        PanelPre.setVisible(true);
        PaneBarText.add(PanelPre, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AreaPre = new JEditorPane();
        AreaPre.setBackground(new Color(-12632257));
        AreaPre.setEditable(false);
        AreaPre.setForeground(new Color(-1));
        AreaPre.setPreferredSize(new Dimension(872, 402));
        AreaPre.setSelectedTextColor(new Color(-13290187));
        AreaPre.setSelectionColor(new Color(-5723992));
        AreaPre.setText("Texto de previsualización");
        AreaPre.setVisible(true);
        PanelPre.setViewportView(AreaPre);
        ProgressPre.setBackground(new Color(-11447983));
        ProgressPre.setBorderPainted(false);
        ProgressPre.setForeground(new Color(-7434610));
        PaneBarText.add(ProgressPre, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 140), new Dimension(-1, 140), new Dimension(-1, 140), 0, false));
        previewCheckBox = new JCheckBox();
        previewCheckBox.setBackground(new Color(-14013910));
        previewCheckBox.setEnabled(false);
        previewCheckBox.setFocusPainted(false);
        previewCheckBox.setForeground(new Color(-1));
        previewCheckBox.setLabel("Visualizar");
        previewCheckBox.setSelected(true);
        previewCheckBox.setText("Visualizar");
        previewCheckBox.setVisible(true);
        panel1.add(previewCheckBox, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    private void createUIComponents() {
        ProgressPre = new ProgressBar(0, 100);
    }


}