package com.company.master;

import com.company.algorithm.Ctrl_Algorithm;
import com.company.input.Ctrl_Input;

public class Ctrl_Master {

    //Attributes
    Integer Function;

    String Path;


    //Constructor
    public Ctrl_Master() {
        Function = 0;
        Path = "";
    }


    //Functions
    public void Context() {
        
        //Asking if we wanna encode or decode
        System.out.println("Vol comprimir o descomprimir un fitxer?");
        String input = System.console().readLine().toLowerCase();
        
        if (input.equals("comprimir")) {
            System.out.println("Vol fer-ho de manera manual o automàtica?");
            String aux = System.console().readLine().toLowerCase();
            
            if (aux.equals("manual")) {
                Function = 3;
            }
            else {
                Function = 1; 
            }
        }
        else Function = 2;


        //Asking the name of the file we wanna encode / decode
        System.out.println("Quin és el path de l'arxiu amb el que volem treballar?");
        Path = System.console().readLine().toLowerCase();
    }


    public void Read() {
        Ctrl_Input Inp = new Ctrl_Input(Path);
        switch (Function) {
            case 1:
                //Encara cal implementar la funció per cridar a una compressió JPEG
                if (Inp.getExtension().equals("txt")) {
                    String file = Inp.getText();
                    Ctrl_Algorithm alg = new Ctrl_Algorithm();

                }
                else {

                }

                break;
            case 2:
            
                break;
            default:

                break;
        }
    }


}