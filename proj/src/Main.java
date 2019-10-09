import com.company.input.*;
import com.company.algorithm.*;

public class Main {

    public static void main(String[] args) 
    {
        Input instance = new Input("Hello.txt");
        
        String aux = instance.getString();

        //System.out.println("Inicial: \n:" + aux);
        
        LZ78 alg = new LZ78(aux);
        
       // aux = alg.print();
        
        //System.out.println("Final: \n:" + aux);
        
    }

}
