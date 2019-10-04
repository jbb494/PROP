package com.company;
import java.util.Scanner;
import java.io.File;
public class Main {
    public static void main(String[] args) {
        /* write your code here */
       // Scanner obtenerNumero = new Scanner(System.in);
        File fitxer = new File("/home/manel/Escritorio/Estudios/3 UNI/Prop/hello.txt");
        StringBuilder paraula = new StringBuilder();
        Scanner s = null;
        try {

            s = new Scanner(fitxer);
            while(s.hasNextLine()) {
                paraula.append(s.nextLine());
                paraula.append('\n');
            }
            System.out.println(paraula);

        }
        catch (Exception ex){
            System.out.println("Msg: " + ex.getMessage());
        }
        finally {
            try{
                if(s != null) s.close();
            }
            catch (Exception ex2)
            {
                System.out.println("Msg 2: " + ex2.getMessage());
            }
        }
    }

}