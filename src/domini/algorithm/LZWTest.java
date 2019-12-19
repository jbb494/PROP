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

    @Test
    /**
     * @fn public void testCompression()
     * @brief Comprovació del correcte funcionament de la compressio per LZW
     */
	public void testCompression() {
        System.out.println("Comprovació de la compressio amb LZW: ");

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

        System.out.println("Ok.");

    }

    @Test
    /**
     * @fn public void testDecompression()
     * @brief Comprovació del correcte funcionament de la descompressio per LZW
     */
    public void testDecompression() {
        System.out.println("Comprovació de la descompressio amb LZW: ");
        
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
        System.out.println("Ok.");
    }
}
