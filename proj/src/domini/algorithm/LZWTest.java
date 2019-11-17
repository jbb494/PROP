package domini.algorithm;

import static org.junit.Assert.*;
import org.junit.Test; 
import java.util.ArrayList;
import persistencia.input.Ctrl_Input_LZW;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;

public class LZWTest {
    

    private LZW lzw;

    
    private void initialize(String path, boolean b) {
        lzw = new LZW(path, b);
    }

    private void compare_Ctrl_Output(Ctrl_Output aux, String descrp) {
        ArrayList<Byte> exp = aux.getOut().getOut();
        ArrayList<Byte> act = lzw.print().getOut().getOut();
        assertEquals(descrp + "El contigut no és l'esperat",exp.size(),act.size());
        for (int i = 0; i < exp.size(); ++i) {
            assertEquals(descrp + "El contigut no és l'esperat",exp.get(i), act.get(i));
        }

        assertEquals(descrp + "El path de sortida no coincideix",aux.getOut().getPath(),lzw.print().getOut().getPath());
        assertEquals(descrp + "La posicio del punter de la classe no coincideix",aux.getOut().getPos(), lzw.print().getOut().getPos());

    }

    @Test
	public void testConstructor() {
        String descp = "Comprovació del constructor de LZW... ";
        
        String path = "output.txt";
        boolean b = true;
        Ctrl_Output aux = new Ctrl_Output(path, "lzw", b);
        initialize(path, b);
        compare_Ctrl_Output(aux, descp);
    }


    @Test
	public void testCompression() {
        String descp = "Comprovació de la compressio amb LZW... ";
        
        String path = "../data/junit.lzw";
        boolean b = true;   
        
        Ctrl_Output aux = new Ctrl_Output(path, "lzw", b);
        Integer[] compr = {66,65,256,257,65,260};
        for (Integer x : compr) 
            aux.add(x);
        
        initialize(path, true);
        Ctrl_Input_Text in = new Ctrl_Input_Text("../data/junit.txt");
        lzw.compression(in);
        compare_Ctrl_Output(aux,descp);    
    }

    @Test
    public void testDecompression() {
        String descp = "Comprovació de la descompressio amb LZW... ";
                
        String path = "../data/junit.txt";
        boolean b = false;

        Ctrl_Output aux = new Ctrl_Output(path, "lzw", b);
        byte[] decomp = {66,65,66,65,65,66,65,65,65};
        for (byte bt : decomp)
            aux.add(bt,8);
        
        
        initialize(path, false);
        Ctrl_Input_LZW in = new Ctrl_Input_LZW("../data/junit.lzw");
        lzw.decompression(in);
        compare_Ctrl_Output(aux,descp);
    }

    @Test
    public void testReturn() {
        String descp = "Comprovacio del resultat actual amb LZW... ";

        String path = "output.txt";
        boolean b = true;

        Ctrl_Output aux = new Ctrl_Output(path, "lzw", b);
        initialize(path, b);
        compare_Ctrl_Output(aux,descp);
    }  
}
