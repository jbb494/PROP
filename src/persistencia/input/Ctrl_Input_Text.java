package persistencia.input;

/**
 * @class Ctrl_Input_Text
 * @brief Classe de Ctrl_Input_Text
 * @author Miguel Paracuellos
 */
public class Ctrl_Input_Text extends Ctrl_Input {

    /**
     * @param seguent Proper byte a llegir
     */
    byte seguent;

    /**
     * @brief Constructor de la classe
     * @param path Path de l'arxiu input
     */
    public Ctrl_Input_Text(String path) {
        super(path);
        seguent = Input_class.getBits(8);
    }
 
    /**
     * @fn public byte get()
     * @brief Proporciona el proxim byte a llegir
     * @note Passa tots els caracters a codi ASCII
     * @return Retorna el proxim byte de l'arxiu, en el cas de que no haguem arribat al final
     */
    public byte get() {
        byte actual = seguent;
        seguent = Input_class.getBits(8); 
        return actual;
    }
 

}