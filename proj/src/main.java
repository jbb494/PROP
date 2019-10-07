import com.company.input.Input;


public class Main {

    public static void main(String[] args) 
    {
        Input instance = new Input("Hello.txt");

        System.out.println("Main: " + instance.getString());
    }

}