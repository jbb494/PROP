package presentacion.form;

import presentacion.form.components.ProgressBar;
import presentacion.form.components.browserTree.browserTreeComponent;
import presentacion.form.components.browserTree.modelBrowser;
import presentacion.master.Ctrl_Master;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainForm extends JFrame {
    private JPanel panel1;
    private JButton ComprBut;
    private JPanel panel2;
    private JPanel ArchivoPanel;
    private JLabel ArchSelec;
    private JTextArea TextDescompressio;
    private JScrollPane ScrollDescompressio;
    private JTree RootTree;
    private JScrollPane ScrollTree;
    private ProgressBar Progress;
    private JPanel PanelComprMenu;
    private JButton ManualButMenu;
    private JButton ExitBut;
    private JButton DescompBut;
    private JPanel PanelDescompr;
    private JButton ButFletxa1;
    private JPanel DuoButPanel;
    Ctrl_Master CM;
    private JPanel panelpopup;
    private JButton LZSSBut;
    private JButton LZWButton;
    private JButton LZ78Button;
    private JButton JPEGButton;
    private JPanel PanelJPEG;
    private JButton GradoBajo;
    private JButton GradoMedio;
    private JButton GradoAlto;
    private JLabel GradoJPEG;
    private JPanel MenuJPEGCompr;
    private JButton FletxaDreta;

    public mainForm() {
        super("Compressor/Descompressor");
        $$$setupUI$$$();
        RootTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                ArchSelec.setText(treeSelectionEvent.getPath().
                        toString().replaceAll(", ", "/").substring(1).replaceFirst(".$", ""));
            }
        });
        ExitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        CM = new Ctrl_Master();
        ButFletxa1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (panelpopup.isVisible()) {
                    panelpopup.setVisible(false);
                    panelpopup.setEnabled(false);
                    if (MenuJPEGCompr.isVisible()) {
                        MenuJPEGCompr.setEnabled(false);
                        MenuJPEGCompr.setVisible(false);
                    }
                } else {
                    panelpopup.setVisible(true);
                    panelpopup.setEnabled(true);
                }
            }
        });
        FletxaDreta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (MenuJPEGCompr.isVisible()) {
                    MenuJPEGCompr.setEnabled(false);
                    MenuJPEGCompr.setVisible(false);
                } else {
                    MenuJPEGCompr.setVisible(true);
                    MenuJPEGCompr.setEnabled(true);
                }
            }
        });
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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 6, new Insets(40, 40, 40, 40), -1, -1));
        panel1.setAlignmentX(0.0f);
        panel1.setAlignmentY(0.0f);
        panel1.setBackground(new Color(-14013910));
        Font panel1Font = this.$$$getFont$$$("Ubuntu Mono", -1, 28, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        panel1.setForeground(new Color(-12632257));
        panel1.setMinimumSize(new Dimension(400, 200));
        panel1.setPreferredSize(new Dimension(1000, 600));
        panel1.setRequestFocusEnabled(true);
        panel1.setVisible(true);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Fira Code Medium", Font.BOLD, 16, panel1.getFont()), new Color(-4932680)));
        panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-14013910));
        panel2.setForeground(new Color(-1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        DuoButPanel = new JPanel();
        DuoButPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, -1));
        DuoButPanel.setBackground(new Color(-12632257));
        panel2.add(DuoButPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        ComprBut = new JButton();
        ComprBut.setBackground(new Color(-12632257));
        ComprBut.setBorderPainted(false);
        ComprBut.setContentAreaFilled(true);
        ComprBut.setDefaultCapable(false);
        ComprBut.setFocusPainted(false);
        ComprBut.setForeground(new Color(-1));
        ComprBut.setInheritsPopupMenu(false);
        ComprBut.setText("Comprimir");
        DuoButPanel.add(ComprBut, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButFletxa1 = new JButton();
        ButFletxa1.setBackground(new Color(-12632257));
        ButFletxa1.setBorderPainted(false);
        ButFletxa1.setFocusPainted(false);
        ButFletxa1.setIcon(new ImageIcon(getClass().getResource("/presentacion/imatgesInterficie/fletxa (1).png")));
        ButFletxa1.setText("");
        DuoButPanel.add(ButFletxa1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelDescompr = new JPanel();
        PanelDescompr.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        PanelDescompr.setBackground(new Color(-12632257));
        panel2.add(PanelDescompr, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        DescompBut = new JButton();
        DescompBut.setBackground(new Color(-12632257));
        DescompBut.setBorderPainted(false);
        DescompBut.setFocusPainted(false);
        DescompBut.setForeground(new Color(-1));
        DescompBut.setText("Descomprimir");
        PanelDescompr.add(DescompBut, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, new Dimension(150, -1), 0, false));
        panelpopup = new JPanel();
        panelpopup.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelpopup.setBackground(new Color(-14013910));
        panelpopup.setEnabled(false);
        panelpopup.setVisible(false);
        panel2.add(panelpopup, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LZWButton = new JButton();
        LZWButton.setBackground(new Color(-12632257));
        LZWButton.setBorderPainted(false);
        LZWButton.setFocusPainted(false);
        LZWButton.setForeground(new Color(-1));
        LZWButton.setHorizontalAlignment(2);
        LZWButton.setText("LZW");
        panelpopup.add(LZWButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), null, null, 0, false));
        LZSSBut = new JButton();
        LZSSBut.setBackground(new Color(-12632257));
        LZSSBut.setBorderPainted(false);
        LZSSBut.setContentAreaFilled(true);
        LZSSBut.setFocusPainted(false);
        LZSSBut.setForeground(new Color(-1));
        LZSSBut.setHorizontalAlignment(2);
        LZSSBut.setText("LZSS");
        panelpopup.add(LZSSBut, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panelpopup.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        PanelJPEG = new JPanel();
        PanelJPEG.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, -1));
        PanelJPEG.setBackground(new Color(-12632257));
        panelpopup.add(PanelJPEG, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 30), 0, false));
        JPEGButton = new JButton();
        JPEGButton.setBackground(new Color(-12632257));
        JPEGButton.setBorderPainted(false);
        JPEGButton.setFocusPainted(false);
        JPEGButton.setForeground(new Color(-1));
        JPEGButton.setHorizontalAlignment(2);
        JPEGButton.setText("JPEG");
        PanelJPEG.add(JPEGButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FletxaDreta = new JButton();
        FletxaDreta.setBackground(new Color(-12632257));
        FletxaDreta.setBorderPainted(false);
        FletxaDreta.setFocusPainted(false);
        FletxaDreta.setIcon(new ImageIcon(getClass().getResource("/presentacion/imatgesInterficie/image.png")));
        FletxaDreta.setText("");
        PanelJPEG.add(FletxaDreta, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LZ78Button = new JButton();
        LZ78Button.setBackground(new Color(-12632257));
        LZ78Button.setBorderPainted(false);
        LZ78Button.setFocusPainted(false);
        LZ78Button.setForeground(new Color(-1));
        LZ78Button.setHorizontalAlignment(2);
        LZ78Button.setText("LZ78");
        panelpopup.add(LZ78Button, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), null, null, 0, false));
        MenuJPEGCompr = new JPanel();
        MenuJPEGCompr.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), 0, 0));
        MenuJPEGCompr.setBackground(new Color(-14013910));
        MenuJPEGCompr.setEnabled(false);
        MenuJPEGCompr.setVisible(false);
        panel2.add(MenuJPEGCompr, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        MenuJPEGCompr.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        GradoBajo = new JButton();
        GradoBajo.setBackground(new Color(-12632257));
        GradoBajo.setBorderPainted(false);
        GradoBajo.setContentAreaFilled(true);
        GradoBajo.setFocusPainted(false);
        GradoBajo.setForeground(new Color(-1));
        GradoBajo.setHorizontalAlignment(2);
        GradoBajo.setText("Bajo");
        MenuJPEGCompr.add(GradoBajo, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), null, null, 0, false));
        GradoMedio = new JButton();
        GradoMedio.setBackground(new Color(-12632257));
        GradoMedio.setBorderPainted(false);
        GradoMedio.setContentAreaFilled(true);
        GradoMedio.setFocusPainted(false);
        GradoMedio.setForeground(new Color(-1));
        GradoMedio.setHorizontalAlignment(2);
        GradoMedio.setText("Medio");
        MenuJPEGCompr.add(GradoMedio, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), null, null, 0, false));
        GradoAlto = new JButton();
        GradoAlto.setBackground(new Color(-12632257));
        GradoAlto.setBorderPainted(false);
        GradoAlto.setContentAreaFilled(true);
        GradoAlto.setFocusPainted(false);
        GradoAlto.setForeground(new Color(-1));
        GradoAlto.setHorizontalAlignment(2);
        GradoAlto.setText("Alto");
        MenuJPEGCompr.add(GradoAlto, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), null, null, 0, false));
        GradoJPEG = new JLabel();
        GradoJPEG.setForeground(new Color(-1));
        GradoJPEG.setText("Grado compresión");
        MenuJPEGCompr.add(GradoJPEG, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        ArchivoPanel = new JPanel();
        ArchivoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        ArchivoPanel.setBackground(new Color(-12632257));
        ArchivoPanel.setForeground(new Color(-1));
        panel1.add(ArchivoPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 25), new Dimension(-1, 25), new Dimension(-1, 25), 0, false));
        ArchSelec = new JLabel();
        ArchSelec.setForeground(new Color(-1));
        ArchSelec.setText("Archivo seleccionado");
        ArchivoPanel.add(ArchSelec, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        ScrollDescompressio = new JScrollPane();
        panel1.add(ScrollDescompressio, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        TextDescompressio = new JTextArea();
        TextDescompressio.setAlignmentX(0.0f);
        TextDescompressio.setAlignmentY(0.0f);
        TextDescompressio.setAutoscrolls(true);
        TextDescompressio.setBackground(new Color(-12632257));
        TextDescompressio.setCaretColor(new Color(-12632257));
        TextDescompressio.setDisabledTextColor(new Color(-1));
        TextDescompressio.setDoubleBuffered(false);
        TextDescompressio.setEditable(false);
        Font TextDescompressioFont = this.$$$getFont$$$("Ubuntu Mono", -1, 16, TextDescompressio.getFont());
        if (TextDescompressioFont != null) TextDescompressio.setFont(TextDescompressioFont);
        TextDescompressio.setForeground(new Color(-1));
        TextDescompressio.setLineWrap(true);
        TextDescompressio.setMargin(new Insets(15, 15, 15, 15));
        TextDescompressio.setSelectedTextColor(new Color(-1));
        TextDescompressio.setSelectionColor(new Color(-9671572));
        TextDescompressio.setText("text de prova 1\ntext de prova 1\ntext de prova 1\ntext de prova 1\ntext de prova 1\ntext de prova 1");
        ScrollDescompressio.setViewportView(TextDescompressio);
        ScrollTree = new JScrollPane();
        ScrollTree.setForeground(new Color(-12632257));
        panel1.add(ScrollTree, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        RootTree.setBackground(new Color(-12632257));
        RootTree.setFocusCycleRoot(false);
        RootTree.setForeground(new Color(-14013910));
        RootTree.setMaximumSize(new Dimension(300, 82));
        RootTree.putClientProperty("JTree.lineStyle", "");
        ScrollTree.setViewportView(RootTree);
        Progress.setBackground(new Color(-12632257));
        Progress.setBorderPainted(false);
        Progress.setForeground(new Color(-3092272));
        Progress.setStringPainted(false);
        Progress.setValue(0);
        panel1.add(Progress, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        ExitBut = new JButton();
        ExitBut.setBackground(new Color(-12632257));
        ExitBut.setFocusPainted(false);
        ExitBut.setForeground(new Color(-1));
        ExitBut.setInheritsPopupMenu(false);
        ExitBut.setText("Exit");
        panel1.add(ExitBut, new com.intellij.uiDesigner.core.GridConstraints(7, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, new Dimension(150, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(7, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(6, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
        modelBrowser m = new modelBrowser();
        DefaultMutableTreeNode model = m.getModel();
        RootTree = new browserTreeComponent(model);
        Progress = new ProgressBar(0, 100);
    }
}