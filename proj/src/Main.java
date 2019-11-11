
//import java.util.AbstractMap;
import java.util.AbstractMap;
import java.util.ArrayList;

import com.company.algorithm.*;
import com.company.input.*;
import com.company.output.*;
import com.company.utils.IntorChar;

public class Main {

    public static void print_binary(Byte b) {
        System.out.println(Integer.toBinaryString(b));
    }

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

            System.out.println("Descompresio de " + pathDecompresio);    

            Ctrl_Input instance = new Ctrl_Input(pathDecompresio);

            ArrayList< AbstractMap.SimpleEntry <Integer, Character> > aux = instance.getLZ78();

            LZ78 alg = new LZ78(aux);

            alg.Decompressor();

            Ctrl_Output outp = alg.print();

            outp.print();

        }   
    
    }
}
