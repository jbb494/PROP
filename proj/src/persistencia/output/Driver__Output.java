package persistencia.output;

import persistencia.output.Output;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver__Output {
    static final String path = "../data/Driver__Output.out";

    private static void showOptions(){
        System.out.println("Driver de Output");
        System.out.println("Constructores: ");
        System.out.println("\t 1. Output(String path)");
        System.out.println("\t Input: 1");
        System.out.println();

        System.out.println("Modificadores: ");
        System.out.println("\t 2. Add(byte b, int n_bits)");
        System.out.println("\t Input: 2");
        System.out.println();

        System.out.println("Consultoras: ");
        System.out.println("\t 3. print()");
        System.out.println("\t Input: 3");
        System.out.println("\t 4. test_printString()");
        System.out.println("\t Input: 4");
        System.out.println();

        System.out.println("\t 0. Sortir");
        System.out.println("\t Input: 0");

        System.out.println("----------------------------------------");
    }

    private static void comprovarExcepcions(Output out, String linea){
        if(out == null && !linea.equals("1") && !linea.equals("0")) {
            throw new IllegalArgumentException("Debes llamar al constructor antes");
        }
    }
    public static void main(String[] args) {
        try {
            Output out = null;
            showOptions();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String linea = "";
            while(linea != null){
                System.out.println("Selecciona una opción:");
                linea = reader.readLine().trim();
                System.out.println("Opción: " + linea + " seleccionada");
                comprovarExcepcions(out, linea);
                switch(linea){
                    case "1":
                        out = new Output(path);
                    break;
                    case "2":
                    try{
                        System.out.println("Escribe un byte y un integer separado de un espacio");
                        String StringAux = reader.readLine().trim();
                        byte ByteAux = (byte)Integer.parseInt(StringAux.split(" ")[0]);
                        Integer midaN = Integer.parseInt(StringAux.split(" ")[1]);
                        
                        out.add(ByteAux, midaN);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    case "3":
                        out.print();
                    break;
                    case "4":
                        out.printString();
                    break;
                    case "0":
                        return;
                    default:
                        System.out.println("La opción no es válida");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}