package domini.utils;

/**
 * @class Pair
 * @brief Template d'una classe Pair
 * @author Miguel Paracuellos Ocaña
 */
public class Pair<T1,T2> {
    //Attributes
    /**
     * @param L primer paràmetre
     */
    T1 L;
    
    /**
     * @param R segon paràmetre
     */
    T2 R;

    /**
     * @brief Constructor de la classe
     * @param i Primer paràmetre
     * @param c Segon paràmetre
     */
    public Pair(T1 i, T2 c) {
        L = i;
        R = c;
    }

    //Functions

    /**
     * @fn public T1 getLeft()
     * @return Retorna el primer paràmetre
     */
    public T1 getLeft() {
        return L;
    }

    /**
     * @fn public T2 getRight()
     * @return Retorna el segon paràmetre
     */
    public T2 getRight() {
        return R;
    }
}