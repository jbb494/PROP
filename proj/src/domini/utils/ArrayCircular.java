package domini.utils;

import java.util.*;


public class ArrayCircular {

    private int start;
    private int end;
    private int size;
    private byte[] array;
    private int afegits;
    
    public ArrayCircular(int mida)
    {
        size = mida;
        start = 0;
        afegits = 0;
        end = mida-2;
        array = new byte[mida];
    }

    public void setValue(byte value)
    {
        array[start] = value;
        start = (++start)%size;
        if(afegits < size) afegits++;
        if(afegits == size) end = (++end)%size;
    }

    public byte getValue(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Fora de rang");
        return array[index];
    }

    public byte getValueAmbDespl(int despl)
    {
        int ret = start - despl;
        if(ret < 0) ret += size;
        if(ret < 0 || ret >= size){
            System.out.println("start: " +start);
            System.out.println("despl: "+ despl);
            System.out.println("ret:" +ret);
            throw new IndexOutOfBoundsException("Fora de rang");}
        return array[ret];
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    public void incStart()
    {
        start = (++start)%size;
    }

    public void incEnd()
    {
        end = (++end)%size;
    }

    public boolean isIn(byte value)
    {
        for(int i = 0; i < afegits; i++)
            if(array[i] == value) return true;
        return false;
    }
    
    public int getAfegits()
    {
        return afegits;
    }
}
