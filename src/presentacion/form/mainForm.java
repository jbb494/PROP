package presentacion.form;

import presentacion.form.components.browserTree.browserTreeComponent;
import presentacion.form.components.browserTree.modelBrowser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class mainForm extends JFrame {
    private JPanel panel1;
    private JButton DescompBut;
    private JButton ComprBut;
    private JPanel panel2;
    private JPanel ArchivoPanel;
    private JLabel ArchSelec;
    private JTextArea TextDescompressio;
    private JScrollPane ScrollDescompressio;
    private JTree RootTree;
    private JScrollPane ScrollTree;
    private ProgressBar pbar;


    public mainForm() {
        $$$setupUI$$$();
        RootTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                ArchSelec.setText(treeSelectionEvent.getPath().
                        toString().replaceAll(", ", "/").substring(1).replaceFirst(".$", ""));
            }
        });
    }

    public void inicialitza_panel() {
        JFrame frame = new JFrame("Compressor/Descompressor");
        frame.setContentPane(new mainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        pbar.setValue(45);

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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 5, new Insets(40, 40, 40, 40), -1, -1));
        panel1.setAlignmentX(0.0f);
        panel1.setAlignmentY(0.0f);
        panel1.setBackground(new Color(-14013910));
        Font panel1Font = this.$$$getFont$$$("Ubuntu Mono", -1, 28, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        panel1.setForeground(new Color(-12632257));
        panel1.setMinimumSize(new Dimension(400, 200));
        panel1.setPreferredSize(new Dimension(1000, 600));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Fira Code Medium", Font.BOLD, 16, panel1.getFont()), new Color(-4932680)));
        panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-14013910));
        panel2.setForeground(new Color(-1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ComprBut = new JButton();
        ComprBut.setBackground(new Color(-12632257));
        ComprBut.setForeground(new Color(-1));
        ComprBut.setInheritsPopupMenu(false);
        ComprBut.setText("Comprimir");
        panel2.add(ComprBut, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, new Dimension(150, -1), 0, false));
        DescompBut = new JButton();
        DescompBut.setBackground(new Color(-12632257));
        DescompBut.setForeground(new Color(-1));
        DescompBut.setText("Descomprimir");
        panel2.add(DescompBut, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, new Dimension(150, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
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
        panel1.add(ScrollDescompressio, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
        RootTree.putClientProperty("JTree.lineStyle", "");
        ScrollTree.setViewportView(RootTree);
        pbar = new JProgressBar();
        pbar.setBackground(new Color(-12632257));
        pbar.setBorderPainted(false);
        pbar.setForeground(new Color(-12632257));
        pbar.setString("0%");
        pbar.setStringPainted(false);
        panel1.add(pbar, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
    }
}