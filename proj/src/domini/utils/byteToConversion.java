package domini.utils;

import java.util.*;


public class byteToConversion {

    byteToConversion()
    {

    }
    
    public static Character byteToCharacter(Byte b)
    {
        Character ret;

        ret = (char)b.byteValue();

        return ret;
    }

    public static Integer byteToInteger(List<Byte> bArg)
    //The most significant bytes are first
    {
        Integer ret = 0;

        for (int i = 0; i < bArg.size(); i++)
        {
            Byte b = bArg.get(i);
            
            ret += (b&0xFF) << (8*(bArg.size() - i -1));
        }
        return ret;
    }

    public static byte shift_right_logic(byte b, int despl) {
        return (byte) ((byte) (b >>> despl) &~ (byte) (0xff << (8-despl)));
    }

    
}
