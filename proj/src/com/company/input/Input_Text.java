package com.company.input;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

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
                System.out.println("nou Byte: " +aux);
                arrayByte.add(aux);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   
    }

    public ArrayList<Byte> getIn()
    {
        return arrayByte;

    }
}
