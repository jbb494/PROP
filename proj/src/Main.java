//import com.company.algorithm.LZ78;
//import com.company.input.Ctrl_Input;
import com.company.output.Ctrl_Output;

public class Main {

    public static void main(String[] args) 
    {
        /*Ctrl_Input instance = new Ctrl_Input("hello.txt");
        
        String aux = instance.getText();

        System.out.println("Inicial: \n" + aux);

        LZ78 alg = new LZ78(aux);
        
        aux = alg.print();
        
        System.out.println("Final: \n" + aux);
        */
        Ctrl_Output outp = new Ctrl_Output();
        outp.add((byte)1, 7);
        outp.add((byte)2, 2);
        outp.print();

    }

}
