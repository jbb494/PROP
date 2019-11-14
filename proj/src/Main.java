
import presentacion.master.Ctrl_Master;

public class Main {

    public static void print_binary(Byte b) {
        System.out.println(Integer.toBinaryString(b));
    }

    public static void main(String[] args) 
    {   
<<<<<<< HEAD
        Integer n = 0;
        String mode = "JPEG";
        if (mode == "JPEG") {
            double[][] mat1 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
            };
            JPEG.discrete_cosine_transform(mat1);
            /*for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    System.out.print(mat1[i][j]);
                    System.out.print(" ");
                }
                System.out.println("");
            }
            System.out.println("");*/
            JPEG.inverse_discrete_cosine_transform(mat1);
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    System.out.print(mat1[i][j]);
                    System.out.print(" ");
                }
                System.out.println("");
            }

            /*

            double[] x = {255, 0, 255};
            double[][] mat2 = {x, x, x, x,  x, x, x, x};
            double[][][] mat3 = { mat2, mat2, mat2, mat2, mat2, mat2, mat2, mat2 };
            double[][][][] mat4 = {mat3};

            Ctrl_Output_Img out1 = new Ctrl_Output_Img(args[0], 8, 8, 255);
            out1.add(mat4);
            out1.print();

            double y = 255;
            double[] mat1 = {y,y, y,y,  y,y, y,y};
            double[][] m2 = { mat1, mat1, mat1, mat1, mat1, mat1, mat1, mat1 };
            JPEG.discrete_cosine_transform(m2, 0,0);
            JPEG.inverse_discrete_cosine_transform(m2, 0,0);
            double[][][] m3 = new double[8][8][3];
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    for (int k = 0; k < 3; ++k) {
                        m3[i][j][k] = m2[i][j];
                    }
                }
            }

            Ctrl_Output_Img out2 = new Ctrl_Output_Img(args[1], 8, 8, 255);
            out2.add(mat4);
            out2.print();*/

            /*
            Ctrl_Input_Img in = new Ctrl_Input_Img(args[0]);
            JPEG alg = new JPEG();
            alg.encode(in);
            alg.decode(args[1]);*/
        }
        else if (mode == "LZSS") {
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

                LZW alg = new LZW(true);

                Ctrl_Input_Text in = new Ctrl_Input_Text(pathCompresio);

                Ctrl_Output outp = alg.print_encode(in);

                outp.print();
            }
            if (n == 2 | n == 0){

                String pathDecompresio = "../CompresioLZW.out";
                
                System.out.println("Descompresio de " + pathDecompresio);
                
                LZW alg = new LZW(false);

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

            }*/   
            String path = "in.txt";
            Ctrl_Input_Img in = new Ctrl_Input_Img(path);
            System.out.println(in.getExtension());
            System.out.println(in.getWidth());
            System.out.println(in.getHeight());
=======
        while(true)
        {
            Ctrl_Master CM = new Ctrl_Master();
            CM.Context();
            System.out.println(CM.Work());
>>>>>>> 8c0f125e396b99d4ee3f953bd5e92131b877c9ca
        }
    }
}
