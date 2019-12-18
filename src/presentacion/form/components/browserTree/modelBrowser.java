package presentacion.form.components.browserTree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class modelBrowser {
    DefaultMutableTreeNode top;

    public modelBrowser() {
        top = iniTreeNode("/home");
    }

    public DefaultMutableTreeNode iniTreeNode(String t) {
        File f = new File(t);
        DefaultMutableTreeNode ret = new DefaultMutableTreeNode(f.getName());
        // System.out.println(t + ": " + (f.isDirectory() ? "is directory" : "is not directory"));
        if (f.isDirectory()) {
            String[] l = f.list();
            for (int i = 0; i < l.length; i++) {
                ret.add(iniTreeNode(t + "/" + l[i]));
            }
        }
        return ret;
    }

    public DefaultMutableTreeNode getModel() {
        return top;
    }

}
