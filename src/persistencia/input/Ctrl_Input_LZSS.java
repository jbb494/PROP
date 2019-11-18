package persistencia.input;

import java.util.ArrayList;
import java.util.List;

import domini.utils.IntorByte;
import domini.utils.byteToConversion;

public class Ctrl_Input_LZSS extends Ctrl_Input {

    /**
       * @brief El constructor on es crida a la classe pare.
       * @param path Conté el path del arxiu.
       */
    public Ctrl_Input_LZSS(String path) {
        super(path);
        getMetadata();
    }

    /**
       * @fn public IntorByte getLZSS()
       * @brief Mètode per aconseguir la unitat mínima necessària.
       * @return Tracta d'obtenir o bé un Byte o bé dos Integers.
       */
    public IntorByte getLZSS()
    {

        byte aux = Input_class.getBits(1);
        IntorByte ioc;
        if(aux == (byte)0) //char
        {
            aux = Input_class.getBits(8);
            ioc = new IntorByte(true);
            ioc.SetByte(aux);
        }
        else
        {
            List<Byte> lb = new ArrayList<Byte>();
            aux = Input_class.getBits(3);
            lb.add(aux);
            aux = Input_class.getBits(8);
            lb.add(aux);
            int despl = byteToConversion.byteToInteger(lb);
            ioc = new IntorByte(false);
            ioc.SetDespl(despl);
            aux = Input_class.getBits(5);
            lb = new ArrayList<Byte>();
            lb.add(aux);
            int mida = byteToConversion.byteToInteger(lb) + 3;
            ioc.SetMida(mida);
        }
        return ioc;
    }
       
}
/** @class Ctrl_Input_LZSS 
 *  @brief Aquesta és la classe fill del controlador Ctrl_Input.
 *   
 *  Aquesta classe tracta d'obtenir la unitat mínima necessària per al
 *  input de l'algorisme LZSS.
 * 
 *  @author Manel Aguilar
 */