package persistencia.input;


public class Ctrl_Input_Text extends Ctrl_Input {

    byte seguent;

    public Ctrl_Input_Text(String path) {
        super(path);
        seguent = Input_class.getBits(8);
    }
 
    //Passa tots els caràcters a codi ASCII
    public byte get() {
        byte actual = seguent;
        seguent = Input_class.getBits(8); 
        return actual;
        /*byte actual = seguent;
        seguent = Input_class.getBits(8); 
        char aux = (char)actual;
        String ret = utf8.uft8Encode(aux + "");
        return ret; */
    }
 

}