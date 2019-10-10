import com.company.input.*;
import com.company.algorithm.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) 
    {
        Ctrl_Input instance = new Ctrl_Input("hello.txt");
        
        String aux = instance.getText();

        System.out.println("Inicial: \n" + aux);

        LZ78 alg = new LZ78(aux);
        
        aux = alg.print();
        
        System.out.println("Final: \n" + aux);
        
    }

}
