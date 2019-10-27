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

public class Input {

    

    private DataInputStream inputStreamReader;
    private int punter; 
    // 0 <= punter < 8,  indica el proper bit que ha de ser llegit del byte en qüestió
    
    private Byte last_byte; //últim byte llegit
    private boolean end;
    private int illegals; //nombre de bits que s'han intentat legir però queden fora del fitxer
    
    // Scanner obtenerNumero = new Scanner(System.in);
    public Input(String path) {
        try {   
            end = false;
            illegals = 0;
            punter = 0;
            //pos = 0;
            FileInputStream dataInputStream = new FileInputStream(new File(/*"../" NO*/path));
            inputStreamReader = new DataInputStream(dataInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { //saltaven excepcions fins que he comentat aquestes línies i no entenc per què
                //if (inputStreamReader != null);
                //    inputStreamReader.close();
            } catch (Exception ex2) {
                System.out.println("Msg 2: " + ex2.getMessage());
            }
        }

    }

    private void read() {
        Integer c;
        try {
            if ((c = inputStreamReader.read()) != -1) {
                last_byte = c.byteValue();
            }
            else {
                end = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   
    }
    
    public byte getBits(int num_bits) 
    // pre: 1 <= num_bits <= 8        <-- IMPORTANT!!!
    {
        if (end) {
            illegals += num_bits;
            return 0;
        }
        if (punter == 0) {
            read();
            if (end) {
                illegals += num_bits;
                return 0;
            }
        }
        byte b = byteToConversion.shift_right_logic(last_byte, punter);
        if(punter + num_bits > 8) 
        {
            int mou = 8 - punter;
            int restants = punter + num_bits - 8;
            punter = 0;
            b = (byte) (b | (getBits(restants) << mou));
        }
        else if (punter + num_bits == 8) 
        {
            punter = 0;
        }
        else //punter + num_bits < 8
        { 
            b = (byte) (b & ~(byte) (0xff << num_bits)); // neteja els bits brossa a posicions altes
            punter += num_bits;
        }
        return b;
    }

    public boolean finished() {
        return end;
    }

    public int outOfFile() { //nombre de bits que s'han intentat legir però queden fora del fitxer
        return illegals;
    }
}



/*package com.company.input;

import java.util.ArrayList;

public class Input {
	
	public Input() {}

	public ArrayList<Byte> getIn() {
		return null;
	}

	public int getHeight() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }
	
    public byte getBits(int num_bits) {
        return 0;
    }

    public byte getBits(int pos_bit, int num_bits) {
        return 0;
    }	

    
}*/
