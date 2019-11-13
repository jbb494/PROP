package persistencia.input;

public class Ctrl_Input_Text extends Ctrl_Input {

    Byte seguent;

    public Ctrl_Input_Text(String path) {
        super(path);
        seguent = Input_class.getBits(8);
    }
 

    public char get() {
        byte actual = seguent;
        seguent = Input_class.getBits(8); 
        return (char)actual;
    }

}