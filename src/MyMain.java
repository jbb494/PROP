import java.util.ArrayList;

import domini.utils.byteToConversion;
import persistencia.input.*;
import persistencia.output.*;

public class MyMain {

    /**
     * @brief De moment no l'elimineu
     * @param args
     */
    public static void main(String[] args) 
    {

        for (long x = 0; x < (long)Integer.MAX_VALUE + 3; ++x) {
            ArrayList<Byte> al = byteToConversion.longToByte(x);
            long y = byteToConversion.byteToLong(al);
            if (x != y) {
                System.out.println(Long.toHexString(x));
                System.out.println(al);
                System.out.println(Long.toHexString(y));
            }
        }
        System.out.println("ok");

        //if (0<1)return;
        

        Ctrl_Output out = new Ctrl_Output("out1.txt");

        long l1 = 12;
        long l2 = 300;

        long addr = out.getLength();
        out.add(0, 32);
        out.add(0, 32);
        for (byte i = 0; i < l1; ++i) {
            out.add(i, 8);
        }
        out.print();
        RandomAccessOutput.writeLong("out1.txt", addr, l1);

        addr = out.getLength();
        out.add(0, 32);
        out.add(0, 32);
        for (int i = 0; i < l2; ++i) {
            out.add((byte)i, 8);
        }
        out.print();
        RandomAccessOutput.writeLong("out1.txt", addr, l2);
        
        
        Ctrl_Input_EncodedFolder in = new Ctrl_Input_EncodedFolder("out1.txt");
        in.startSubfile();
        while(!in.finished()) {
            System.out.print(Input.getInstance().getBits(8) + " ");
        }
        in.endSubfile();

        System.out.println();
        
        in.startSubfile();
        while(!in.finished()) {
            System.out.print(Input.getInstance().getBits(8) + " ");
        }
        in.endSubfile();
    }
}