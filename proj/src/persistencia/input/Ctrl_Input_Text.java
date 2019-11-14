package persistencia.input;

import domini.utils.utf8;

public class Ctrl_Input_Text extends Ctrl_Input {

    byte seguent;

    public Ctrl_Input_Text(String path) {
        super(path);
        seguent = Input_class.getBits(8);
    }
 
    //Passa tots els caràcters a codi ASCII
    public char get() {
        byte actual = seguent;
        seguent = Input_class.getBits(8); 
        return (char)actual;
        /*byte actual = seguent;
        seguent = Input_class.getBits(8); 
        char aux = (char)actual;
        String ret = utf8.uft8Encode(aux + "");
        return ret; */
    }
 

}