import com.company.algorithm.*;
import com.company.input.Ctrl_Input;
import com.company.output.Ctrl_Output;

public class Main {

    public static void main(String[] args) 
    {
        
        Ctrl_Input instance = new Ctrl_Input("../hello.txt");
        
        String aux = instance.getText();

        System.out.println("Inicial: \n" + aux);

        LZSS alg = new LZSS(aux);
      
        Ctrl_Output outp = alg.print();
        
        System.out.println("Final: \n");

        outp.print();
        
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
