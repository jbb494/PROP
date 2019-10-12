package com.company.output;

import java.util.*;

public class Output
{

    static ArrayList<Byte> Out;
    static Integer Pos;

    public Output()
    {
        Out = new ArrayList<>();
        Pos = 0;
    }
    public void add(byte b, int n_bits)
    {
        byte aux, aux2;
        aux = b << Pos;
        if(Pos != 0)
        {
            aux2 = Out.get(Out.size()-1); 
            Out.remove(Out.size()-1);
            aux = aux | aux2;
        }
        Out.add(aux);
        Pos += n_bits;
        if(Pos > 7) Pos = 0;
        if(n_bits > (8-Pos))
        {
            int restants = n_bits - (8-Pos);
            int despl = 8-Pos;
            aux = b >>> despl;
            add(aux, restants);
        }
    }
    public void print()
    {
        System.out.println(Out);
    }
}