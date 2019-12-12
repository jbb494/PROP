import persistencia.input.*;
import persistencia.output.*;

public class MyMain {

    /**
     * @brief Main de la classe
     * @param args
     */
    public static void main(String[] args) 
    {
        Ctrl_Output out = new Ctrl_Output("out1.txt");
        out.add(6, 32);
        for (byte i = 0; i < 5; ++i) {
            out.add(i, 8);
        }
        out.add((byte)11, 6);
        out.print();

        out.add(6, 32);
        for (byte i = 0; i < 5; ++i) {
            out.add(i, 8);
        }
        out.add((byte)11, 6);
        out.print();
        
        Ctrl_Input_CompFolder in = new Ctrl_Input_CompFolder("out1.txt");
        in.startSubfile();
        while(!in.finished()) {
            System.out.println(Input.getInstance().getBits(8));
        }
        in.endSubfile();

        System.out.println();
        
        in.startSubfile();
        while(!in.finished()) {
            System.out.println(Input.getInstance().getBits(8));
        }
        in.endSubfile();
    }
}