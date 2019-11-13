
import presentacion.master.Ctrl_Master;

public class Main {

    public static void print_binary(Byte b) {
        System.out.println(Integer.toBinaryString(b));
    }

    public static void main(String[] args) 
    {   
        while(true)
        {
            Ctrl_Master CM = new Ctrl_Master();
            System.out.println(CM.Context());
        }
        /*
        Integer n = 0;
        String mode = "LZSS";
        if (mode == "LZSS") {
            if(n == 1 | n == 0){ // n = 0 per comprimir
     
                String pathCompresio = "../hello.txt";

                System.out.println("Compresio de " + pathCompresio);

                LZSS alg = new LZSS("../CompresioLZSS.out");

                Ctrl_Input_Text in = new Ctrl_Input_Text(pathCompresio);

                alg.Compressor(in);

                Ctrl_Output outp = alg.print();

                outp.print();
            }
            if (n == 2 | n == 0){
                String pathDecompresio = "../CompresioLZSS.out";
                
                System.out.println("Descompresio de " + pathDecompresio);
    
                Ctrl_Input_LZSS in = new Ctrl_Input_LZSS(pathDecompresio);
                
                LZSS alg = new LZSS("../DescompresioLZSS.out");
                
                alg.Decompressor(in);
                
                Ctrl_Output outp = alg.print();
    
                outp.print();
             }
        }

        else if (mode == "LZW") {
            if(n == 1 | n == 0){ // n = 0 per comprimir
     
                String pathCompresio = "../hello.txt";

                System.out.println("Compresio de " + pathCompresio);

                LZW alg = new LZW("../hello.");

                Ctrl_Input_Text in = new Ctrl_Input_Text(pathCompresio);

                Ctrl_Output outp = alg.print_encode(in);

                outp.print();
            }
            if (n == 2 | n == 0){

                String pathDecompresio = "../hello.lzw";
                
                System.out.println("Descompresio de " + pathDecompresio);
                
                LZW alg = new LZW("../bye.");

                Ctrl_Input_LZW in = new Ctrl_Input_LZW(pathDecompresio);
                                
                Ctrl_Output outp = alg.print_decode(in);
    
                outp.print();
             }
        }
        else {
            /*Integer n = 0; // n = 0 per els dos
            if(n == 1 | n == 0){ // n = 0 per comprimir

            String pathCompresio = "../hello.txt";

            System.out.println("Compresio de " + pathCompresio);    

            Ctrl_Input instance = new Ctrl_Input(pathCompresio);

            String aux = instance.getText();

            // System.out.println("Inicial: \n" + aux);

            LZSS alg = new LZSS(aux);

            alg.Compressor();

            Ctrl_Output outp = alg.print();

            // System.out.println("Final: \n");

            outp.print();
            }
            if (n == 2 | n == 0){
            String pathDecompresio = "../CompresioLZSS.out";

            System.out.println("Descompresio de " + pathDecompresio);    

            Ctrl_Input instance = new Ctrl_Input(pathDecompresio);

            ArrayList< IntorChar > aux = instance.getLZSS();

            LZSS alg = new LZSS(aux);

            alg.Decompressor();

            Ctrl_Output outp = alg.print();

            outp.print();

            }
            String path = "in.txt";
            Ctrl_Input_Img in = new Ctrl_Input_Img(path);
            System.out.println(in.getExtension());
            System.out.println(in.getWidth());
            System.out.println(in.getHeight());
        }
        */
       

    }
}
