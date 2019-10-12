import java.util.AbstractMap;
import java.util.ArrayList;

import com.company.algorithm.LZ78;
import com.company.input.Ctrl_Input;
import com.company.output.Ctrl_Output;

public class Main {

    public static void main(String[] args) 
    {
        
        Ctrl_Input instance = new Ctrl_Input("../hello.txt");
        
        String aux = instance.getText();

        System.out.println("Inicial: \n" + aux);

        LZ78 alg = new LZ78(aux);
      
        Ctrl_Output outp = alg.print();
        
        System.out.println("Final: \n");

        outp.printString();
        
        /*
        Ctrl_Input instance = new Ctrl_Input("./LZ78.out");

        ArrayList< AbstractMap.SimpleEntry <Integer, Character> > aux = instance.getLZ78();

        for (AbstractMap.SimpleEntry <Integer, Character> entr : aux)
        {
            System.out.println("Int: " + entr.getKey() + "Char: " + entr.getValue());
        }
        */
    }
}
