package com.company.utils;

import java.util.*;
import java.util.function.BinaryOperator;

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
    {
        Integer ret = 0;

        for (int i = 0; i < bArg.size(); i++)
        {
            Byte b = bArg.get(i);

            ret += b << (8*(bArg.size() - i -1));
            //System.out.println(b + " << (8*" + i + ") = " + (b << (8*i)));
        }

        return ret;
    }
}
