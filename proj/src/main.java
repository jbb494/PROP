import com.company.input.Input;
import com.company.algorithm.LZ78;

public class Main {

    public static void main(String[] args) 
    {
        Input instance = new Input("Hello.txt");
        
        String aux = instance.getString();

        System.out.println("Inicial: \n:" + aux);
        
        LZ78 alg = new LZ78(aux);
        
        aux = alg.print();
        
        System.out.println("Final: \n:" + aux);
        
    }

}
