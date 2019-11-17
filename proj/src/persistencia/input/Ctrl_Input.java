package persistencia.input;

import java.util.regex.Pattern;

/**
 * @class Ctrl_Input
 * @brief Classe de Ctrl_Input
 * @author 
 */
public class Ctrl_Input {

    //Attributes
    /**
     * @param Input_class Classe Input
     */
    protected Input Input_class;

    /**
     * @param Extensio Extensio de l'arxiu que estem tractant
     */
    String Extensio;


    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu a tractar
     */
    public Ctrl_Input(String path) {
  
        Input_class = new Input(path);

        Pattern p = Pattern.compile("\\.");

        String spl[] = p.split(path);

        this.Extensio = spl[1];
        
    }
    
    //Function
    /**
     * @fn public String getExtension()
     * @return Retorna l'extensio de l'arxiu
     */
    public String getExtension() {
        return Extensio;
    }

    /**
     * @fn public boolean finished()
     * @return Retorna si hem acabat de llegir l'arxiu o no
     */
    public boolean finished() {
        return Input_class.finished();
    }

    /**
     * @fn public String getAlg()
     * @return Proporciona el algoritme mes adient per tractar la descompressio
     */
    public String getAlg()
    {
        return Input_class.getDecodeAlg();
    }

    /**
     * @fn public void getMetadata()
     * @brief Llegeix el dos primers bits de l'arxiu comprimit per saber amb quin algoritme ha sigut tractat
     */
    public void getMetadata() {
        Input_class.getBits(2);
    }

    /*
    Agafeu d'aquí la vostra funció, modifiqueu-la convenientment i posau-la al vostre fill de Ctrl_Input

    public String getText()
    {
        ArrayList<Byte> arrayByte =  Input_class.getIn();
       
        //System.out.println("Imprimeixo amb Bytes: \n" + arrayByte);

        String outputString = "";

        for(int i = 0; i<arrayByte.size(); i++)
        {
            char a = (char)arrayByte.get(i).byteValue();
            outputString += a ;
        }
        return outputString;

    }

    public Byte[][][] getImg() {
        int h; //= Input_class.getHeight();
        int w; //= Input_class.getWidth();
        ArrayList<Byte> arrayByte =  Input_class.getIn();

        Byte[][][] ret = new Byte[h][w][3];
        
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                for (int k = 0; k < 3; ++k) {
                    ret[i][j][k] = arrayByte.get(k + 3*(j + w*i));
                }
            }
        }

        return ret;
    }

    public ArrayList< AbstractMap.SimpleEntry <Integer, Character> > getLZ78()
    {
        ArrayList< AbstractMap.SimpleEntry <Integer, Character> > ret = 
        new ArrayList< AbstractMap.SimpleEntry <Integer, Character> > (0);

        List<Byte> arrayByte =  Input_class.getIn();

        Integer intAux = 0;

        Character charAux = '0';

        for(int i = 0; i<arrayByte.size();)
        {
            //System.out.println("arrayByte.get(" + i + ") = " + arrayByte.get(i));
            
            List<Byte> arrayAux = arrayByte.subList(i, i+4);
            i+=4;
            intAux = byteToConversion.byteToInteger(arrayAux);
            

            charAux = byteToConversion.byteToCharacter(arrayByte.get(i));
            i++;
            ret.add(new AbstractMap.SimpleEntry<Integer, Character>(intAux, charAux));
        }

        return ret;
    }

    public ArrayList<Integer> getLZW() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ArrayList<Byte> arrayByte = Input_class.getIn();

        for (int i = 0; i < arrayByte.size(); i += 4) {
            
            List<Byte> aux = arrayByte.subList(i, i+4);
            
            ret.add(byteToConversion.byteToInteger(aux));
        
        }

        return ret;
    }

    public ArrayList< IntorChar > getLZSS()
    {
        ArrayList<IntorChar> ret = new ArrayList<IntorChar>();
        boolean end = false;
        while(!end)
        {
            byte aux = Input_class.getBits(1);
            if(aux == (byte)0) //char
            {
                aux = Input_class.getBits(8);
                IntorChar ioc = new IntorChar(true);
                ioc.SetChar(byteToConversion.byteToCharacter(aux));
                ret.add(ioc);
            }
            else
            {
                List<Byte> lb = new ArrayList<Byte>();
                aux = Input_class.getBits(5);
                lb.add(aux);
                aux = Input_class.getBits(8);
                lb.add(aux);
                int despl = byteToConversion.byteToInteger(lb);
                IntorChar ioc = new IntorChar(false);
                ioc.SetDespl(despl);
                aux = Input_class.getBits(5);
                lb = new ArrayList<Byte>();
                lb.add(aux);
                int mida = byteToConversion.byteToInteger(lb) + 2;
                if(mida == 2) end = true;
                else
                {
                    ioc.SetMida(mida);
                    ret.add(ioc);
                }
            }
        }
        return ret;
    }
    */
}

















