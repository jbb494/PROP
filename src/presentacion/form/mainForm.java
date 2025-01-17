package presentacion.form;

import global.global;
import presentacion.Ctrl_Presentacio.Ctrl_Presentacio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class mainForm extends JFrame {

    /**
     * @param panel1 Panel principal del form.
     */
    private JPanel panel1;

    /**
     * @param Exit Botón para salir del form.
     */
    private JButton Exit;

    /**
     * @param descomprimirButton Botón de descomprimir.
     */
    private JButton descomprimirButton;

    /**
     * @param ChoosePathButton Botón para elegir el Path de compresión/descompresión.
     */
    private JButton ChoosePathButton;

    /**
     * @param Panel2 Panel que contiene los botones de descomprimir, comprimir y su botón de opciones.
     */
    private JPanel Panel2;

    /**
     * @param LabelPath Label para que se muestre el Path que has seleccionado.
     */
    private JLabel LabelPath;

    /**
     * @param PanelPath Panel que contiene la Label que te muestra el Path y el botón para buscar un Path.
     */
    private JPanel PanelPath;

    /**
     * @param previewCheckBox CheckBox para elegir si quieres ver una previsualización o no.
     */
    private JCheckBox previewCheckBox;

    /**
     * @param AreaPre EditorPane para poder mostrar texto de previsualización.
     */
    private JEditorPane AreaPre;

    /**
     * @param PanelPre ScrollPane que contiene dos objetos diferentes, dependiendo de si es imagen o texto, para la previsualización.
     */
    private JScrollPane PanelPre;

    /**
     * @param ComprimirBut Botón para comprimir un fichero/imagen/carpeta.
     */
    private JButton ComprimirBut;

    /**
     * @param ComprimirBut Botón que abre un menú para comprimir con opciones avanzadas.
     */
    private JButton ButMenu;

    /**
     * @param PanelCompr Panel que contiene el botón de comprimir y el menú con opciones avanzadas.
     */
    private JPanel PanelCompr;

    /**
     * @param PaneBarText Panel que contiene el panel que se irá sobreescribiendo para la previsualización.
     */
    private JPanel PaneBarText;
    private JPanel PreImagePane;

    /**
     * @param CP Instancia de la clase Ctrl_Presentacio.
     */
    private Ctrl_Presentacio CP;

    /**
     * @param pop_est Instancia de la clase PopUp_Estadistica.
     */
    private PopUp_Estadistica pop_est;

    /**
     * @param t Instancia de la clase Thread.
     */
    private Thread t;

    /**
     * @brief Constructor de la classe
     */
    public mainForm() {
        super("Compresor/Descompresor");

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
                        global.type gt = CP.getType();
                        if (gt == global.type.imageComprimit || gt == global.type.textComprimit
                                || gt == global.type.folderComprimit) {
                            descomprimirButton.setEnabled(true);
                            ComprimirBut.setEnabled(false);
                            ButMenu.setEnabled(false);
                        } else {
                            descomprimirButton.setEnabled(false);
                            ComprimirBut.setEnabled(true);
                            ButMenu.setEnabled(true);
                        }
                        updatePreview();
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
                updatePreview();
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

    private void updatePreview() {
        System.out.println(CP);
        global.type type = CP.getType();
        if (CP == null || !previewCheckBox.isSelected() ||
                (type != global.type.imageComprimit && type != global.type.textComprimit))
            return;

        String pathTemp = CP.DecodeTemp();
        String file = null;
        try {
            Desktop.getDesktop().open(new File(pathTemp));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
*                 Integer last = AreaPre.getDocument().getLength();
                System.out.println("Last: " + last + ", PositionActual: " + AreaPre.getCaretPosition());
                if (AreaPre.getCaretPosition() >= last - 30) {
                    System.out.println("HI");
                }*/

    /**
     * @param s Texto a mostrar.
     * @fn private void show_est(String s)
     * @brief Muestra las estadística de la compresión o de la descompresión.
     */
    private void show_est(String s) {
        pop_est.dispose();
        pop_est = new PopUp_Estadistica(s);
        pop_est.setContentPane(pop_est.panel1);
        pop_est.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pop_est.pack();
        pop_est.setVisible(true);
    }

    /**
     * @param s Texto a mostrar.
     * @fn private void generando_estadistica(String s)
     * @brief Muestra una ventana diciendo que está en proceso de compresión/descompresión.
     */
    private void generando_estadistica(String s) {
        String txt = "<html> <h1> Generando la " + s + "</h1>";
        pop_est = new PopUp_Estadistica(txt);
        pop_est.setContentPane(pop_est.panel1);
        pop_est.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pop_est.pack();
        pop_est.setVisible(true);
    }

    /**
     * @return Devuelve el atributo Thread de la clase.
     * @fn private Thread getThread()
     */
    private Thread getThread() {
        return t;
    }

    /**
     * @return Devuelve un valor de una enumeration diciendo el tipo de fichero que es de un Path específico.
     * @fn public global.type getFolderOrFile()
     */
    public global.type getFolderOrFile() {
        return CP.getType();
    }

    /**
     * @fn public void inicialitza_panel()
     * @brief Inicializa el form principal.
     */
    public void inicialitza_panel() {
        setContentPane(new mainForm().panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(30, 30, 30, 30), -1, -1));
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
        panel1.add(Exit, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Panel2 = new JPanel();
        Panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        Panel2.setBackground(new Color(-14013910));
        Panel2.setForeground(new Color(-12632257));
        panel1.add(Panel2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 30), 0, false));
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
        PanelCompr.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, -1));
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
        ButMenu.setIcon(new ImageIcon(getClass().getResource("/presentacion/imatgesInterficie/points-icon.png")));
        ButMenu.setText("");
        PanelCompr.add(ButMenu, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), null, new Dimension(40, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        Panel2.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        PanelPath = new JPanel();
        PanelPath.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        PanelPath.setBackground(new Color(-11447983));
        panel1.add(PanelPath, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 25), new Dimension(-1, 40), 0, false));
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
        ChoosePathButton.setIcon(new ImageIcon(getClass().getResource("/presentacion/imatgesInterficie/folder-icon.png")));
        ChoosePathButton.setText("");
        PanelPath.add(ChoosePathButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), new Dimension(40, 30), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        PanelPath.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 5), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        previewCheckBox = new JCheckBox();
        previewCheckBox.setBackground(new Color(-14013910));
        previewCheckBox.setEnabled(false);
        previewCheckBox.setFocusPainted(false);
        previewCheckBox.setForeground(new Color(-1));
        previewCheckBox.setLabel("Visualizar");
        previewCheckBox.setSelected(true);
        previewCheckBox.setText("Visualizar");
        previewCheckBox.setVisible(true);
        panel1.add(previewCheckBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
}

/** @class mainForm
 *  @brief Clase del form principal de la interfíçicie gráfica
 * @file

 *
 *  En esta clase se trata el form principal.
 *
 *  @author Manel Aguilar
 *  @author Joan Bellavista
 */