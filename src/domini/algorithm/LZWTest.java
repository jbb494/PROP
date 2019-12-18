package domini.algorithm;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

import persistencia.browser.Ctrl_Browser;
import persistencia.input.Ctrl_Input_LZW;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;

/**
 *@class LZWTest
 *@brief Junit de la classe LZW
 *@note Ha d'existir l'arxiu junit.jm a ../src/persistencia/data/
 *@author Miguel Paracuellos Ocaña
*/
public class LZWTest {
    
	/**
	 * @param lzw Atribut de tipu LZW per a la comprovació de la classe
	 */
    public LZW lzw;

    private String path_lzw = "../src/persistencia/data/junit.jm";
    private String path_txt = "../src/persistencia/data/junit.txt";

    /**
     * @fn private void initialize(String path, boolean b)
     * @brief Inicialitza l'atribut lzw
     * @param path Primer paràmetre del constructor lzw
     * @param b  Segon paràmetre del constructor lzw
     */
    private void initialize(String path, boolean b) {
        lzw = new LZW(path, b);
    }

	/**
	 * @fn private void compare_Ctrl_Output(Ctrl_Output aux, String descrp)
	 * @brief Compara si dos elements de tipus Ctrl_Output son iguals
	 * @param aux De tipus Ctrl_Output
	 * @param descrp Text a treure a treure per pantalla en cas de correcte funcionament
	 */
    private void compare_Ctrl_Output(Ctrl_Output aux, String descrp) {
        assertEquals(descrp + "El contigut no és l'esperat", aux, lzw.Output.Output.getInstance());


        /*ArrayList<Byte> exp = aux.getOut().getOut();
        ArrayList<Byte> act = lzw.print().getOut().getOut();
        assertEquals(descrp + "El contigut no és l'esperat",exp.size(),act.size());
        for (int i = 0; i < exp.size(); ++i) {
            assertEquals(descrp + "El contigut no és l'esperat",exp.get(i), act.get(i));
        }

        assertEquals(descrp + "El path de sortida no coincideix", aux.getOut().getPath(),lzw.print().getOut().getPath());
        assertEquals(descrp + "La posicio del punter de la classe no coincideix",aux.getOut().getPos(), lzw.print().getOut().getPos());
        */
    }

    @Test
    /**
     * @fn public void testConstructor() 
     * @brief Comprovació del correcte funcionament del constructor LZW
     */
	public void testConstructor() {
        String descp = "Comprovació del constructor de LZW... ";
        
        String path = "output.txt";
        Ctrl_Output aux = new Ctrl_Output(path);
        initialize(path, false);
        compare_Ctrl_Output(aux, descp);
    }


    @Test
    /**
     * @fn public void testCompression()
     * @brief Comprovació del correcte funcionament de la compressio per LZW
     */
	public void testCompression() {
        String descp = "Comprovació de la compressio amb LZW... ";
        
        boolean b = true;   
        
        Ctrl_Output aux = new Ctrl_Output(path_lzw);
        Integer[] compr = {66,65,256,257,65,260};
        for (Integer x : compr) 
            aux.add(x);
        
        initialize(path_lzw, false);
        Ctrl_Input_Text in = new Ctrl_Input_Text(path_txt);
        lzw.Compressor();
        compare_Ctrl_Output(aux,descp);    
    }

    @Test
    /**
     * @fn public void testDecompression()
     * @brief Comprovació del correcte funcionament de la descompressio per LZW
     */
    public void testDecompression() {
        String descp = "Comprovació de la descompressio amb LZW... ";
                
        boolean b = false;

        Ctrl_Output aux = new Ctrl_Output(path_txt);
        byte[] decomp = {66,65,66,65,65,66,65,65,65};
        for (byte bt : decomp)
            aux.add(bt,8);
        
        
        initialize(path_txt, true);
        Ctrl_Input_LZW in = new Ctrl_Input_LZW(path_lzw);
        lzw.Decompressor();
        compare_Ctrl_Output(aux,descp);
    }

    @Test
    /**
     * @fn public void testReturn()
     * @brief Comprovació del correcte resultat de la classe LZW
     */
    public void testReturn() {
        String descp = "Comprovacio del resultat actual amb LZW... ";

        String path = "output.txt";
        boolean b = true;

        Ctrl_Output aux = new Ctrl_Output(path);
        initialize(path, b);
        compare_Ctrl_Output(aux,descp);
    }
}
