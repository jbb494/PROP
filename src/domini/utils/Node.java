package domini.utils;

/**
 * @class Node
 * @brief Representa un node emprat per la representació de cadenes de caràcters.
 * @author Miguel Paracuellos Ocaña
 */
public class Node {
    //Attributes
    
    /**
     * @param First Primer valor que accedeix a la seqüencia de caracters representada per aquest node
     */
    Integer First;

    /**
     * @param c Valor del node
     */
    byte c;

    /**
     * @param Left fill esquerra
     */
    Integer Left;

    /**
     * @param Right fill dret
     */
    Integer Right;


    /**
     * @brief Constructor de la classe
     * @param c Valor del node
     */
    public Node(byte c) {
        First = Left = Right = -1;
        this.c = c;
    }

    //Functions

    /**
     * @fn public void Modify_Left(Integer i)
     * @brief Modifica el valor del fill esquerra
     * @param i Nou valor pel fill esquerra
     */
    public void Modify_Left(Integer i) {
        this.Left = i;
    }

    /**
     * @fn public void Modify_Right(Integer i)
     * @brief Modifica el valor del fill dret
     * @param i Nou valor pel fill dret
     */
    public void Modify_Right(Integer i) {
        this.Right = i;
    }

    /**
     * @fn public void Modify_First(Integer i)
     * @brief Modifica el valor del primer acces
     * @param i Nou valor pel primer acces al node
     */
    public void Modify_First(Integer i) {
        this.First = i;
    }

}
