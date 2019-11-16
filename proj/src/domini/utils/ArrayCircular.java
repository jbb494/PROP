package domini.utils;


public class ArrayCircular {

    /**
     * @param start punter a la primera posició disponible.
     */
    private int start;

    /**
     * @param end punter a la última posició.
     */
    private int end;

    /**
     * @param size mida del atribut array.
     */
    private int size;

    /**
     * @param array vector de bytes que contindrà la informació.
     */
    private byte[] array;

    /**
     * @param afegits nombre de valors afegits al atribut array.
     */
    private int afegits;
    
    /**
     * @brief El constructor.
     * @param mida inicialitza el atribut array amb mida mida.
     */
    public ArrayCircular(int mida)
    {
        size = mida;
        start = 0;
        afegits = 0;
        end = mida-2;
        array = new byte[mida];
    }

    /**
     * @fn public void setValue(byte value)
     * @brief Mètode per assignar el valor value al atribut array a la posició start.
     * @param value
     */
    public void setValue(byte value)
    {
        array[start] = value;
        start = (++start)%size;
        if(afegits < size) afegits++;
        if(afegits == size) end = (++end)%size;
    }

    /**
     * @fn public byte getValue(int index)
     * @param index
     * @return Retorna el byte a la posició index de array
     */
    public byte getValue(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Fora de rang");
        return array[index];
    }

    /**
     * @fn public byte getValueAmbDespl(int despl)
     * @brief dóna el byte de despl vegades cap enrere (des de start).
     * @param despl
     * @return Retorna el byte a la posició indicada del array
     */
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

    /**
     * @fn public int getStart()
     * @brief dóna el int de la primera posició vàlida del array.
     * @return Retorna l'atribut start
     */
    public int getStart()
    {
        return start;
    }

    /**
     * @fn public int getEnd()
     * @brief dóna el int de la última posició vàlida del array.
     * @return Retorna l'atribut end
     */
    public int getEnd()
    {
        return end;
    }

    /**
     * @fn public boolean isIn(byte value)
     * @brief Recorregut lineal sobre l'atribut array per trobar value.
     * @param value
     * @return true si value està a array else false.
     */
    public boolean isIn(byte value)
    {
        for(int i = 0; i < afegits; i++)
            if(array[i] == value) return true;
        return false;
    }
    
    /**
     * @fn public int getAfegits()
     * @return retorna el atribut privat afegits.
     */
    public int getAfegits()
    {
        return afegits;
    }
}

/** @class ArrayCircular 
 *  @brief Aquesta és la classe per a estructura de dades del algoritme LZSS.
 *   
 *  Aquesta classe tracta d'utilitzar un vector amb política circular.
 * 
 *  @author Manel Aguilar
 */