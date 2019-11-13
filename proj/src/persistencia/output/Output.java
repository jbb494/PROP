package persistencia.output;

import java.util.*;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import domini.utils.byteToConversion;

public class Output {

    ArrayList<Byte> Out;
    Integer Pos;

    String path;

    public Output(String path) {
        Out = new ArrayList<>();
        Pos = 0;
        this.path = path;
    }


    public void add(byte b, int n_bits) //Pre: El byte b ha de poder expressar-se en n_bits
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
                aux = byteToConversion.shift_right_logic(b, despl);
                Pos = 0;
                add(aux, restants);
            }
            else
            {
                Pos += n_bits;
                if(Pos > 7) Pos -= 8;
            }
        }
   }
   
    public void print()
    {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
        
        //for(Byte b: Out) System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));

    }

    public void printString()
    {
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        char[] cArray = new char[Out.size()];
        for (int i = 0; i < cArray.length; i++)
        {
            char cAux = (char)(byte)Out.get(i);

            cArray[i] = cAux;
        }
        
        try {
            outputStreamWriter.write(cArray, 0, cArray.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(Byte b: Out) System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));

    }
}