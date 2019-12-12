package domini.utils;

import java.util.*;

/**
 * @class byteToConversion
 * @brief Classe byteToConversion
 * @author Joan Bellavista
 */
public class byteToConversion {
  
    /**
     * @fn public static Character byteToCharacter(Byte b)
     * @brief Transforma un byte a Character
     * @param b Byte a transformar
     * @return Character resultat de la transformació
     */
    public static Character byteToCharacter(Byte b)
    {
        Character ret;

        ret = (char)b.byteValue();

        return ret;
    }

    /**
     * @fn public static Integer byteToInteger(List<Byte> bArg)
     * @brief Transforma un Array de bytes a un Enter
     * @param bArg Llista de bytes que volem transformar (de més a menys significatius)
     * @return Integer resultat de la conversió
     */
    public static Integer byteToInteger(List<Byte> bArg)
    {
        Integer ret = 0;

        for (int i = 0; i < bArg.size(); i++)
        {
            Byte b = bArg.get(i);
            
            ret += (b&0xFF) << (8*(bArg.size() - i -1));
        }
        return ret;
    }

    /**
     * @fn public static byte shift_right_logic(byte b, int despl)
     * @brief Desplaçament lògic cap a la detra d'un byte
     * @param b Byte que volem desplaçar
     * @param despl Nombre de bits que volem desplaçar
     * @return Byte desplaçat
     */
    public static byte shift_right_logic(byte b, int despl) {
        return (byte) ((byte) (b >>> despl) &~ (byte) (0xff << (8-despl)));
    }

    
}
