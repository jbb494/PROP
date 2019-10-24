//import java.util.AbstractMap;
import java.util.ArrayList;

import com.company.algorithm.*;
import com.company.input.*;
import com.company.output.*;
import com.company.utils.IntorChar;

public class Main {

    public static void main(String[] args) 
    {
       Integer n = 0; // n = 0 per els dos
       if(n == 1 | n == 0){ // n = 0 per comprimir

        String pathCompresio = "../hello.txt";

        System.out.println("Compresio de " + pathCompresio);    

        Ctrl_Input instance = new Ctrl_Input(pathCompresio);
            
            String aux = instance.getText();

           // System.out.println("Inicial: \n" + aux);

            LZSS alg = new LZSS(aux);

            alg.Compressor();
        
            Ctrl_Output outp = alg.print();
            
           // System.out.println("Final: \n");

            outp.print();
       }
       if (n == 2 | n == 0){
            String pathDecompresio = "../CompresioLZSS.out";
            
            System.out.println("Descompresio de " + pathDecompresio);    

            Ctrl_Input instance = new Ctrl_Input(pathDecompresio);

            ArrayList< IntorChar > aux = instance.getLZSS();

            LZSS alg = new LZSS(aux);

            alg.Decompressor();
            
            Ctrl_Output outp = alg.print();

            outp.print();

        }   
        /*
        Ctrl_Input a = new Ctrl_InputLZ78("../hello.txt");

        System.out.println(a.get());*/
    }
}
