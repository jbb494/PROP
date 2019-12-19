package domini.algorithm;

import static org.junit.Assert.*;
import org.junit.Test; 
import java.util.ArrayList;

import persistencia.input.Ctrl_Input;
import persistencia.input.Ctrl_Input_LZW;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;

/**
 *@class LZWTest
 *@brief Junit de la classe LZW
 *@author Miguel Paracuellos Ocaña
*/
public class LZWTest {
    
	/**
	 * @param lzw Atribut de tipu LZW per a la comprovació de la classe
	 */
    private LZW lzw;

    private String path_lzw = "../src/persistencia/data/junit/junit.jm";
    private String path_lzw_check = "../src/persistencia/data/junit/junit_check.jm";
    private String path_txt_check = "../src/persistencia/data/junit/junit_check.txt";
    private String path_txt = "../src/persistencia/data/junit/junit.txt";

    /**
     * @fn private void initialize(String path, boolean b)
     * @brief Inicialitza l'atribut lzw
     * @param path Primer paràmetre del constructor lzw
     * @param b  Segon paràmetre del constructor lzw
     */
    private void initialize(String path, boolean b) {
        lzw = new LZW(path, b);
    }

    /*
	/**
	 * @fn private void compare_Ctrl_Output(Ctrl_Output aux, String descrp)
	 * @brief Compara si dos elements de tipus Ctrl_Output son iguals
	 * @param aux De tipus Ctrl_Output
	 * @param descrp Text a treure a treure per pantalla en cas de correcte funcionament
	 */
    /*private void compare_Ctrl_Output(Ctrl_Output aux, String descrp) {
        ArrayList<Byte> exp = aux.getOut().getOut();
        ArrayList<Byte> act = lzw.print().getOut().getOut();
        assertEquals(descrp + "El contigut no és l'esperat",exp.size(),act.size());
        for (int i = 0; i < exp.size(); ++i) {
            assertEquals(descrp + "El contigut no és l'esperat",exp.get(i), act.get(i));
        }

        assertEquals(descrp + "El path de sortida no coincideix",aux.getOut().getPath(),lzw.print().getOut().getPath());
        assertEquals(descrp + "La posicio del punter de la classe no coincideix",aux.getOut().getPos(), lzw.print().getOut().getPos());

    }*/

    //@Test
    /**
     * @fn public void testConstructor() 
     * @brief Comprovació del correcte funcionament del constructor LZW
     */
	/*public void testConstructor() {
        String descp = "Comprovació del constructor de LZW... ";
        
        String path = "output.txt";
        boolean b = true;
        Ctrl_Output aux = new Ctrl_Output(path, "lzw", b);
        initialize(path, b);
        compare_Ctrl_Output(aux, descp);
    }*/
    


    @Test
    /**
     * @fn public void testCompression()
     * @brief Comprovació del correcte funcionament de la compressio per LZW
     */
	public void testCompression() {
        String descp = "Comprovació de la compressio amb LZW... ";

        Ctrl_Output aux = new Ctrl_Output(path_lzw_check);
        aux.addMetadata("lzw");
        Integer[] compr = {66,65,256,257,65,260};
        for (Integer x : compr) 
            aux.add(x);
        aux.print();

        initialize(path_lzw, false);
        Ctrl_Input.initialize(path_txt);
        lzw.Compressor();
        lzw.print();

        Ctrl_Input_LZW in = new Ctrl_Input_LZW(path_lzw);
        ArrayList<Integer> arr1 = new ArrayList<>();
        while (true) {
            Integer kk = in.get();
            if (in.finished()) break;
            arr1.add(kk);
        }

        in = new Ctrl_Input_LZW(path_lzw);
        ArrayList<Integer> arr2 = new ArrayList<>();
        while (true) {
            Integer kk = in.get();
            if (in.finished()) break;
            arr2.add(kk);
        }
        
        assertEquals("El contigut no és l'esperat", arr1.size(), arr2.size());
        for (int i = 0; i < arr1.size(); ++i) {
            assertEquals("El contigut no és l'esperat",arr1.get(i), arr2.get(i));
        }
    }

    @Test
    /**
     * @fn public void testDecompression()
     * @brief Comprovació del correcte funcionament de la descompressio per LZW
     */
    public void testDecompression() {
        String descp = "Comprovació de la compressio amb LZW... ";
        
        Ctrl_Output aux = new Ctrl_Output(path_txt_check);
        byte[] decomp = {66,65,66,65,65,66,65,65,65};
        for (byte x : decomp) 
            aux.add(x,8);
        aux.print();
        
        initialize(path_txt, true);

        Ctrl_Input.initialize(path_lzw);
        Ctrl_Input.getMetadata();
        lzw.Decompressor();
        lzw.print();

        Ctrl_Input_Text in = new Ctrl_Input_Text(path_txt);
        ArrayList<Byte> arr1 = new ArrayList<>();
        while (true) {
            Byte kk = in.get();
            if (in.finished()) break;
            arr1.add(kk);
        }

        in = new Ctrl_Input_Text(path_txt_check);
        ArrayList<Byte> arr2 = new ArrayList<>();
        while (true) {
            Byte kk = in.get();
            if (in.finished()) break;
            arr2.add(kk);
        }
        
        assertEquals("El contigut no és l'esperat", arr1.size(), arr2.size());
        for (int i = 0; i < arr1.size(); ++i) {
            assertEquals("El contigut no és l'esperat",arr1.get(i), arr2.get(i));
        }
    }

    //@Test
    /**
     * @fn public void testReturn()
     * @brief Comprovació del correcte resultat de la classe LZW
     */
    /*public void testReturn() {
        String descp = "Comprovacio del resultat actual amb LZW... ";

        String path = "output.txt";
        boolean b = true;

        Ctrl_Output aux = new Ctrl_Output(path, "lzw", b);
        initialize(path, b);
        compare_Ctrl_Output(aux,descp);
    }  
    */
}
