package persistencia.input;

import java.util.regex.Pattern;

/**
 * @class Ctrl_Input
 * @brief Classe de Ctrl_Input
 * @author Joan Lapeyra
 */
public class Ctrl_Input {


    /**
     * @param Extensio Extensio de l'arxiu que estem tractant
     */
    String Extensio;


    /**
     * @brief Constructor de la classe. Comança a llegir un fitxer.
     * @param path Path de l'arxiu a tractar
     */
    public Ctrl_Input(String path) {
  
        Input.initialize(path);

        Pattern p = Pattern.compile("\\.");

        String spl[] = p.split(path);

        this.Extensio = spl[1];

    }

    /**
     * @brief Constructor de la classe. Segueix llegint del fitxer del qual s'estava llegint.
     */
    public Ctrl_Input() {
        this.Extensio = null;
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
        return Input.getInstance().finished();
    }

    /**
     * @fn public String getAlg()
     * @return Proporciona el algoritme mes adient per tractar la descompressio
     */
    public String getAlg()
    {
        return Input.getInstance().getDecodeAlg();
    }

    /**
     * @fn public void getMetadata()
     * @brief Llegeix el dos primers bits de l'arxiu comprimit per saber amb quin algoritme ha sigut tractat
     */
    public int getMetadata() {
        return (int)Input.getInstance().getBits(2);
    }
}
