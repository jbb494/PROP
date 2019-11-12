package com.company.algorithm;

import com.company.input.Ctrl_Input;
import com.company.input.Ctrl_Input_LZSS;
import com.company.input.Ctrl_Input_Text;
import com.company.output.Ctrl_Output;

public class Ctrl_Algorithm {


    //Falta implementar que la classe Algorithm sigui el pare de totes les altres
    Algorithm Alg;

    public Ctrl_Algorithm() {}

    public String Choose_Encoder(String Path, String method)
    {
        int i = Path.lastIndexOf(".");
        String prefix = Path.substring(0, i+1);
        if(method == "lzss")
        {
            LZSS alg = new LZSS(prefix + method);
            Ctrl_Input_Text in = new Ctrl_Input_Text(Path);
            alg.Compressor(in);
            Ctrl_Output outp = alg.print();
            outp.print();
        }
        else if(method == "lzw")
        {

        }
        else if(method == "lz78")
        {

        }
        else if(method == "ppm")
        {

        }
        return "Compressió de " + Path;
    }
    public String Auto_Encoder(String Path)
    {

        return "";
    }
    public String Auto_Decoder(String Path, String method)
    {
        int i = Path.lastIndexOf(".");
        String prefix = Path.substring(0, i+1);
        if(method == "ppm")
        {

        }
        else
        {
            Ctrl_Input inP = new Ctrl_Input(Path);
            String decide = inP.getAlg();
            if(decide == "lzss")
            {
                Ctrl_Input_LZSS in = new Ctrl_Input_LZSS(Path);                
                LZSS alg = new LZSS(prefix + method);        
                alg.Decompressor(in);
                Ctrl_Output outp = alg.print();
                outp.print();
            }
            else if(decide == "lzw")
            {

            }
            else if(decide == "lz78")
            {

            }
            else return decide;
        }
        return "Descompressió de " + Path;
    }
}