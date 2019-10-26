/*package com.company.input;

import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Input_Img extends Input {

    private ArrayList<Byte> arrayByte;
    private int llargada;
    private int alcada;

    private BufferedImage image;

    //Constructor
    public Input_Img(String path)
    {
        try {
            File file = new File(path);
            image = ImageIO.read(file);
            llargada = image.getWidth();
            alcada = image.getHeight();

            arrayByte = new ArrayList<Byte>(0);
            read();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void read() {
        byte red, green, blue;
        for(int i = 0; i < alcada; i++)
        {
            for(int j = 0; j < llargada; j++)
            {
                try
                {
                    int color = image.getRGB(i,j);
                    red   = (byte)((color & 0x00ff0000) >> 16);
                    green = (byte)((color & 0x0000ff00) >> 8);
                    blue  = (byte)(color & 0x000000ff);
                    arrayByte.add(red);
                    arrayByte.add(green);
                    arrayByte.add(blue);
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e){}
                catch(java.lang.NullPointerException e){}
            }  
        }
    }

    public ArrayList<Byte> getIn() {
        return arrayByte;
    }

    public int getHeight() {
        return alcada;
    }

    public int getWidth() {
        return llargada;
    }

    /*public static ArrayList< ArrayList < ArrayList <Byte> > > ReadImg(String nomImg) throws IOException
    {
        File file = new File(nomImg);
        BufferedImage image = ImageIO.read(file);
        int llargada = image.getWidth();
        int alcada = image.getHeight();
        ArrayList< ArrayList < ArrayList <Byte> > > torn = new ArrayList <> (alcada);
        for (int i = 0; i < llargada; i++)
        {
            torn.add(new ArrayList<ArrayList<Byte>>(alcada));
            for (int j = 0; j < alcada; j++)
            {
                torn.get(i).add(new ArrayList<Byte>(3));
            }
        }
        byte red, green, blue;
        for(int i = 0; i < alcada; i++)
        {
            for(int j = 0; j < llargada; j++)
            {
                try
                {
                    int color = image.getRGB(i,j);
                    red   = (byte)((color & 0x00ff0000) >> 16);
                    green = (byte)((color & 0x0000ff00) >> 8);
                    blue  = (byte)(color & 0x000000ff);
                    torn.get(i).get(j).add(red);
                    torn.get(i).get(j).add(green);
                    torn.get(i).get(j).add(blue);
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e){}
                catch(java.lang.NullPointerException e){}
            }  
        }
        return torn;
    }        
}*/
