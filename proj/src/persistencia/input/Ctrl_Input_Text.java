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
        char c = (char)seguent;
        int i = (int)seguent; 
        if (i < 128) {
            res.concat((char) i + "");
        } else if (i > 127 && i < 2048) {
            int j = (i >> 6) | 192;
            res += ((char) j);
            j = (i & 63) | 128;
            res += ((char) j);
        } else {
            int j = (i >> 12) | 224;
            res += ((char) j);
            j = ((i >> 6) & 63) | 128;
            res += ((char) j);
            j = (c & 63) | 128;
            res += ((char) j);
        }

        char aux = (char)actual;
        String ret = utf8.uft8Encode(aux + "");
        return ret;
    }


}