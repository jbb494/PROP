
import presentacion.master.Ctrl_Master;

public class Main {

    public static void print_binary(Byte b) {
        System.out.println(Integer.toBinaryString(b));
    }

    public static void main(String[] args) 
    {   
        while(true)
        {
            Ctrl_Master CM = new Ctrl_Master();
            CM.Context();
            System.out.println(CM.Work());
        }
    }
}
