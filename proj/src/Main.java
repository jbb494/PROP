import java.util.AbstractMap;
import java.util.ArrayList;

import com.company.algorithm.*;
import com.company.input.Ctrl_Input;
import com.company.output.*;

public class Main {

    public static void main(String[] args) 
    {
       Integer n = 0; // n = 0 per els dos
       if(n == 1 | n == 0){ // n = 0 per comprimir

        String pathCompresio = "../hello.txt";

        System.out.println("Compresio a " + pathCompresio);    

        Ctrl_Input instance = new Ctrl_Input(pathCompresio);
            
            String aux = instance.getText();

           // System.out.println("Inicial: \n" + aux);

            LZ78 alg = new LZ78(aux);

            alg.Compressor();
        
            Ctrl_Output outp = alg.print();
            
           // System.out.println("Final: \n");

            outp.print();
       }
       if (n == 2 | n == 0){
            String pathDecompresio = "../CompresioLZ78.out";
            
            System.out.println("Compresio a " + pathDecompresio);    

            Ctrl_Input instance = new Ctrl_Input(pathDecompresio);

            ArrayList< AbstractMap.SimpleEntry <Integer, Character> > aux = instance.getLZ78();

            LZ78 alg = new LZ78(aux);

            alg.Decompressor();
            
            Ctrl_Output outp = alg.print();

            outp.print();

        }

        
        Output outp = new Output("provaOutput.out");
        outp.add((byte)31, 4);
        outp.add((byte)15, 3);
        outp.add((byte)127, 8);
        outp.print();

    }
}
