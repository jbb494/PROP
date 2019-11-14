/**
 * @class Driver__Ctrl_Output
 * @brief 
 */

package persistencia.output;

import persistencia.output.Ctrl_Output;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Driver__Ctrl_Output {
   
    private static void showOptions(){
        System.out.println("Driver de Ctrl_Output");
        System.out.println("Constructores: ");
        System.out.println("\t 1. Ctrl_Output(String path, String method, boolean b)");
        System.out.println("\t Input: 1");
        System.out.println();

        System.out.println("Modificadores: ");
        System.out.println("\t 2. add(Byte b, Integer n_bits)");
        System.out.println("\t Input: 2");
        System.out.println("\t 3. add(String s)");
        System.out.println("\t Input: 3");
        System.out.println("\t 4. add(Character c)");
        System.out.println("\t Input: 4");
        System.out.println("\t 5. add(Integer x)");
        System.out.println("\t Input: 5");
        System.out.println("\t 6. add(Integer x, Integer mida)");
        System.out.println("\t Input: 6");
        System.out.println();

        System.out.println("Consultoras: ");
        System.out.println("\t 7. print()");
        System.out.println("\t Input: 7");
        System.out.println("\t 8. printString()");
        System.out.println("\t Input: 8");
        System.out.println();

        System.out.println("Escoge si el output es un descomprimido o comprimido. Y el método");
        System.out.println("\t 9. Descomprimido");
        System.out.println("\t Input: 9");
        System.out.println("\t 10. Comprimido método lz78");
        System.out.println("\t Input: 10");
        System.out.println("\t 11. Comprimido método lzw");
        System.out.println("\t Input: 11");
        System.out.println("\t 12. Comprimido método lzss");
        System.out.println("\t Input: 12");

        System.out.println("\t 0. Sortir");
        System.out.println("\t Input: 0");

        System.out.println("----------------------------------------");
    }

    private static void comprovarExcepcions(Ctrl_Output ctrl_out, String linea, String method, Boolean b){
        int op = Integer.parseInt(linea);
        if(method.equals("") && op < 9 && !linea.equals("0")){
            throw new IllegalArgumentException("Debes primero definir el método y si es compresión o descompresión");
        }
        if(ctrl_out == null && !linea.equals("1") && !linea.equals("0") && op < 6) {
            throw new IllegalArgumentException("Debes llamar al constructor antes");
        }
    }

    public static void main(String[] args) {
        try {
            Ctrl_Output ctrl_out = null;
            Boolean b = false;
            String method = "";
            String path = "../data/Driver__Ctrl_Output.out";
            showOptions();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String linea = "";
            while(linea != null){
                System.out.println("Selecciona una opción:");
                linea = reader.readLine().trim();
                System.out.println("Opción: " + linea + " seleccionada");
                comprovarExcepcions(ctrl_out, linea, method, b);
                switch(linea){
                    case "1":
                        ctrl_out = new Ctrl_Output(path, method, b);
                    break;
                    case "2":
                        System.out.println("Escribe un byte y un integer");
                        String[] aux = reader.readLine().trim().split(" ");
                        Integer numBits1 = Integer.parseInt(aux[0]);
                        Byte a = (byte)Integer.parseInt(aux[1]);
                        ctrl_out.add(a, numBits1);
                    break;
                    case "3":
                        System.out.println("Escribe una string");
                        ctrl_out.add(reader.readLine());
                    break;
                    case "4":
                        System.out.println("Escribe una character");
                        ctrl_out.add(reader.readLine().charAt(0));
                    break;
                    case "5":
                        System.out.println("Escribe una Integer");
                        ctrl_out.add(Integer.parseInt(reader.readLine()));
                    break;
                    case "6":
                        System.out.println("Escribe un Integer y su tamaño");
                        String[] aux1 = reader.readLine().split(" ");
                        ctrl_out.add(Integer.parseInt(aux1[0]), Integer.parseInt(aux1[1]));
                    break;
                    case "7":
                        ctrl_out.print();
                    break;
                    case "8":
                        ctrl_out.printString();
                    break;
                    case "9":
                        method = "descomprimir";
                        b = true;
                    break;
                    case "10":
                        method = "lz78";
                        b = false;
                    break;
                    case "11":
                        method = "lzw";
                        b = false;
                    break;
                    case "12":
                        method = "lzss";
                        b = false;
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

