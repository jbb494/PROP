package com.company.input;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import com.company.utils.byteToConversion;

// Necesitaremos inputreader y bufferreader
// https://funnelgarden.com/java_read_file/ --> FileInputStream

public class Input_Text extends Input {

    ArrayList<Byte> arrayByte;
    private DataInputStream inputStreamReader;

    /* write your code here */
    // Scanner obtenerNumero = new Scanner(System.in);
    public Input_Text(String path) {
        try {
            FileInputStream dataInputStream = new FileInputStream(new File(/*"../" NO*/path));
            arrayByte = new ArrayList<Byte>(0);
            inputStreamReader = new DataInputStream(dataInputStream);
            read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null)
                    inputStreamReader.close();
            } catch (Exception ex2) {
                System.out.println("Msg 2: " + ex2.getMessage());
            }
        }

    }

    private void read() {
        Integer c;
        try {
            while ((c = inputStreamReader.read()) != -1) {
                Byte aux = c.byteValue();
                arrayByte.add(aux);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   
    }
    
    public byte getBits(int pos_byte, int pos_bit, int num_bits) {
        byte b = arrayByte.get(pos_byte);
        b = byteToConversion.shift_right_logic(b, pos_bit);
        if(pos_bit + num_bits > 8) 
            b = (byte) (b | (getBits(pos_byte + 1, 0, pos_bit + num_bits - 8) << (8 - pos_bit)));
        else
            b = (byte) (b & ~(byte) (0xff << num_bits)); // neteja els bits brossa a posicions altes
        return b;
    }

    public byte getBits(int pos_bit, int num_bits) {
        return getBits(pos_bit/8, pos_bit%8, num_bits);
    }

    public ArrayList<Byte> getIn()
    {
        return arrayByte;

    }
}
