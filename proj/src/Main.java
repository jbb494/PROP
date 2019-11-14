//import java.util.AbstractMap;
import domini.algorithm.*;
import persistencia.input.*;
import persistencia.output.*;
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
            System.out.println(CM.Context());
        }
    }
}
