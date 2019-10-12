package com.company.output;

import java.util.*;

public class Output
{

    static ArrayList<Byte> Out;
    static Integer Pos;

    public Output()
    {
        Out = new ArrayList<>();
        Pos = 7;
    }
    public void add(byte b, int n_bits)
    {
        if(n_bits > (Pos+1))
        {
            int desplesq = 8-n_bits;
            int despldret = 7-Pos;
            byte aux2 = (byte)(b << desplesq);
            aux2 = (byte)(aux2 >>> despldret);
            byte aux3 = Out.get(Out.size()-1);
            aux3 = (byte)(aux3 | aux2);
            Out.remove(Out.size()-1);
            Out.add(aux3);
            int restants = n_bits-Pos-1;
            aux2 = (byte)(b << restants);
            aux2 = (byte)(aux2 >>> restants);
            Pos = 7;
            add(aux2, restants);
            
        }
        else
        {
            if(Pos != 7)
            {
                byte aux = Out.get(Out.size()-1);
                int desplesq = 8-n_bits;
                byte aux2 = (byte)(b << desplesq);
                int despldret = 7-Pos;
                aux2 = (byte)(aux2 >>> despldret);
                aux2 = (byte)(aux | aux2);
                Out.remove(Out.size()-1);
                Out.add(aux2);
                Pos -= n_bits;
                if(Pos < 0) Pos = 7;
            }
            else
            {
                int desplesq = 8-n_bits;
                byte aux = (byte)(b << desplesq);
                Out.add(aux);
                Pos -= n_bits;
                if(Pos < 0) Pos = 7;
            } 
        }
    }
}