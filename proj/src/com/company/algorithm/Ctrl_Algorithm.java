package com.company.algorithm;

public class Ctrl_Algorithm {


    //Falta implementar que la classe Algorithm sigui el pare de totes les altres
    Algorithm Alg;

    public Ctrl_Algorithm() {}

    public static String Choose_Encoder(String file, String method) {
        switch (method) {
            case "LZW":
                Alg = new LZW();
                break;

            case "LZSS":
                Alg = new LZSS();    
                break;

            case "LZ78":
                Alg = new LZ78(); 
                break;

            default:
                //Hem de decidir l'heuristica amb la que escollirem l'algoritme de manera automàtica 
                break;
        }


        return "still_deciding";
    }
}