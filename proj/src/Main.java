//import java.util.AbstractMap;
import com.company.algorithm.*;
import com.company.input.*;
import com.company.output.*;

public class Main {

    public static void print_binary(Byte b) {
        System.out.println(Integer.toBinaryString(b));
    }

    public static void main(String[] args) 
    {   
        Integer n = 0;
        if(n == 1 | n == 0){ // n = 0 per comprimir

            String pathCompresio = "../hello.txt";

            System.out.println("Compresio de " + pathCompresio);    

            Ctrl_Input_Text in = new Ctrl_Input_Text(pathCompresio);

            // System.out.println("Inicial: \n" + aux);

            LZ78 alg = new LZ78(false);

            alg.Compressor(in);

            Ctrl_Output outp = alg.print();

            // System.out.println("Final: \n");

            outp.print();
        }
        if (n == 2 | n == 0){
            String pathDecompresio = "../CompresioLZ78.out";

            System.out.println("Descompresio de " + pathDecompresio);    

            LZ78 alg = new LZ78(true);

            Ctrl_Input_LZ78 in = new Ctrl_Input_LZ78(pathDecompresio);

            alg.Decompressor(in);

            Ctrl_Output outp = alg.print();

            outp.print();
        }   
    }
}
