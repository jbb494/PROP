package domini.utils;

/** @class IntorByte
 *  @brief Classe auxiliar per l'algorisme LZSS.
  * @file
 *   
 *  En aquesta classe es tracta de construir un objecte
 *  per obtenir una unitat mínima per al descompressor.
 * 
 *  @author Manel Aguilar
 */
public class IntorByte {

   /**
    * @param intobyte Bool on false són 2 int's i true és un byte.
    */
   private boolean intobyte; 

   /**
    * @param caracter byte on té valor si intochar és true.
    */
   private byte caracter;

   /**
    * @param despl Té el valor d'un int si intochar és false.
    */
   private int despl;

   /**
    * @param mida Té el valor d'un int si intochar és false.
    */
   private int mida;
   
   /**
    * @brief El constructor.
    * @param b Si és true es un byte, si és false, són dos int's.
    */
   public IntorByte(boolean b)
   {
      intobyte = b;
   }
   
   /**
    * @fn public void SetDespl(int despl)
    * @brief Mètode per establir un possible despl.
    * @param despl Valor que volem posar al atribut despl.
    */
   public void SetDespl(int despl)
   {
      this.despl = despl;
   }

   /**
    * @fn public void SetMida(int mida)
    * @brief Mètode per establir una possible mida.
    * @param mida Valor que volem posar al atribut mida.
    */
   public void SetMida(int mida)
   {
      this.mida = mida;
   }

   /**
    * @fn public void SetByte(byte c)
    * @brief Mètode per establir un possible byte.
    * @param c Valor que volem posar al atribut caracter.
    */
   public void SetByte(byte c)
   {
      caracter = c;
   }
   
   /**
    * @fn public int GetDespl()
    * @brief Mètode per obtenir un possible despl.
    * @return valor del atribut despl.
    */
   public int GetDespl()
   {
      return despl;
   }

   /**
    * @fn public int GetMida()
    * @brief Mètode per obtenir una possible mida.
    * @return valor del atribut mida.
    */
   public int GetMida()
   {
      return mida;
   }

   /**
    * @fn public byte GetByte()
    * @brief Mètode per obtenir un possible byte.
    * @return valor del atribut caracter.
    */
   public byte GetByte()
   {
      return caracter;
   }

   /**
    * @fn public boolean IsIntorByte()
    * @brief Mètode per saber si és un byte o 2 int's.
    * @return valor del atribut intobyte.
    */
   public boolean IsIntorByte()
   {
      return intobyte;
   }

}