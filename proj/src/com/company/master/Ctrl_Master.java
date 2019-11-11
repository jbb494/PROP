package com.company.master;

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
                Function = 3;       // Compressió manual
            }
            else {
                Function = 1;       // Compressió automàtica
            }
        }
        else Function = 2;          // Descompressió


        //Asking the name of the file we wanna encode / decode
        System.out.println("Quin és el path de l'arxiu amb el que volem treballar?");
        Path = System.console().readLine().toLowerCase();
    }


}