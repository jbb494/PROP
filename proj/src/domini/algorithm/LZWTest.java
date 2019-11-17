package domini.algorithm;

import java.util.ArrayList;

import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;

public class LZWTest {
    @Test

    private LZW lzw;

    private void initialize(String path, boolean b) {
        lzw = new LZW(path, b);
    }

    public void testConstructor() {
        String path = "output.txt";
        boolean b = true;
        Ctrl_Output aux = new Ctrl_Output(path, "lzw", b);
        initialize(path, b);
        assertArrayEquals(aux.getOut().getOut(), lzw.print().getOut().getOut());
        assertEqual(aux.getOut().getPath(),lzw.print().getOut().getPath());
        assertEqual(aux.getOut().getPos(), lzw.print().getOut().getPos());
    }

    public void testCompression() {
        String path = "output.txt";
        boolean b = true;   
        
        Ctrl_Output aux = new Ctrl_Output(path, lzw, b);
        ArrayList<Integer> compr = {66,65,256,257,65,260};
        for (Integer x : compr) 
            aux.add(x);
        
        Ctrl_Input_Text in = new Ctrl_Input_Text("../../../junit.txt");
    }

    


    
}
