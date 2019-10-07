package com.company.input;

import java.util.Scanner;
import java.io.File;

public class Input {

    File fitxer;
    String paraula;
    Scanner s;
    /* write your code here */
    // Scanner obtenerNumero = new Scanner(System.in);
    public Input(String path)
    {
        fitxer = new File("../hello.txt");
        paraula = new String();
        s = null;
    }

    public String getString()
    {
        try { 
            s = new Scanner(fitxer);
            while(s.hasNextLine()) {
                paraula+=s.nextLine();
                paraula+='\n';
            }
            return paraula;          
    
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
        return null;
    } 

}