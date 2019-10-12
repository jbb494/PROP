package com.company.output;

import java.util.*;
import java.util.stream.Collectors;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;;

public class Output {

    ArrayList<Byte> Out;
    Integer Pos;

    DataOutputStream dataOutputStream;

    public Output(String path) {
        Out = new ArrayList<>();
        Pos = 0;

        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void add(byte b, int n_bits)
    {
        if(n_bits != 0)
        {
            byte aux, aux2;
            aux = (byte)(b << Pos);
            if(Pos != 0)
            {
                aux2 = Out.get(Out.size()-1); 
                Out.remove(Out.size()-1);
                aux = (byte)(aux | aux2);
            }
            Out.add(aux);
            if(n_bits > (8-Pos))
            {
                int restants = n_bits - (8-Pos);
                int despl = 8-Pos;
                aux = (byte)(b >>> despl);
                Pos += n_bits;
                if(Pos > 7) Pos = 0;
                add(aux, restants);
            }
            Pos += n_bits;
            if(Pos > 7) Pos = 0;
        }
    }
    public void print()
    {

        byte[] bArray = new byte[Out.size()];
        for (int i = 0; i < bArray.length; i++)
        {
            Byte bAux = Out.get(i);

            bArray[i] = bAux;
        }
        
        try {
            dataOutputStream.write(bArray, 0, bArray.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(Byte b: Out) System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));

    }
}