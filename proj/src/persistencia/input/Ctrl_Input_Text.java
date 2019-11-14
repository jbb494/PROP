package persistencia.input;

import domini.utils.utf8;

public class Ctrl_Input_Text extends Ctrl_Input {

    byte seguent;

    public Ctrl_Input_Text(String path) {
        super(path);
        seguent = Input_class.getBits(8);
    }
 
    //Passa tots els caràcters a codi ASCII
    public String get() {
        byte actual = seguent;
        seguent = Input_class.getBits(8);
        String res = "";
        char c = (char)actual;
        int i = (int)actual; 
        if (i < 128) {
            res.concat(c + "");
        } else if (i > 127 && i < 2048) {
            res.concat(c + "");
            actual = seguent;
            seguent = Input_class.getBits(8);
            c = (char)actual;
            res.concat(c + "");
        } else {
            res.concat(c + "");
            actual = seguent;
            seguent = Input_class.getBits(8);
            c = (char)actual;
            res.concat(c + "");
            actual = seguent;
            seguent = Input_class.getBits(8);
            c = (char)actual;
            res.concat(c + "");
        }
        String ret = utf8.uft8Encode(res);
        return ret;
    }


}