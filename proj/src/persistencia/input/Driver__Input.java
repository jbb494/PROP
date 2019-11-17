package persistencia.input;

import persistencia.input.Input;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Necesitaremos inputreader y bufferreader
// https://funnelgarden.com/java_read_file/ --> FileInputStream


/**
 * @class Driver__Input
 * @brief Classe de Driver__Input
 * @author Joan Bellavista
 */
public class Driver__Input {
    
    /**
     * @fn private static void showOptions()
     * @brief Mostra les accions a realitzar durant l'execució
     */
    private static void showOptions(){
        System.out.println("Driver de Input");
        System.out.println("Constructores: ");
        System.out.println("\t 1. Input(String path)");
        System.out.println("\t Input: 1");
        System.out.println();

        System.out.println("Consultoras: ");
        System.out.println("\t 2. getBits()");
        System.out.println("\t Input: 2");
        System.out.println("\t 3. getMoreBits()");
        System.out.println("\t Input: 3");
        System.out.println("\t 4. finished()");
        System.out.println("\t Input: 4");
        System.out.println("\t 5. getDecodeAlg()");
        System.out.println("\t Input: 5");
        System.out.println();

        System.out.println("Escoge el 'input PATH'");
        System.out.println("\t 6. PATH = Input1.txt");
        System.out.println("\t Input: 6");
        System.out.println("\t 7. PATH = Input1.lz78");
        System.out.println("\t Input: 7");
        System.out.println("\t 8. PATH = Input1.lzW");
        System.out.println("\t Input: 8");
        System.out.println("\t 9. PATH = Input1.lzss");
        System.out.println("\t Input: 9");
        System.out.println();

        System.out.println("\t 0. Sortir");
        System.out.println("\t Input: 0");

        System.out.println("----------------------------------------");
    }
    
    /**
     * @fn private static void comprovarExcepcions(Input inp, String linea, String path)
     * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
     * @param inp
     * @param linea
     * @param path
     */
    private static void comprovarExcepcions(Input inp, String linea, String path){
        int op = Integer.parseInt(linea);
        if(path.equals("") && op < 6 && !linea.equals("0")){
            throw new IllegalArgumentException("Debes escoger un path antes");
        }
        if(inp == null && !linea.equals("1") && !linea.equals("0") && op < 6) {
            throw new IllegalArgumentException("Debes llamar al constructor antes");
        }
    }

    /**
     * @fn public static void main(String[] args)
     * @brief Main de la classe
     * @param args
     */
    public static void main(String[] args) {
        try {
            Input inp = null;
            String path = "";
            showOptions();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String linea = "";
            while(linea != null){
                System.out.println("Selecciona una opción:");
                linea = reader.readLine().trim();
                System.out.println("Opción: " + linea + " seleccionada");
                comprovarExcepcions(inp, linea, path);
                switch(linea){
                    case "1":
                        inp = new Input(path);
                    break;
                    case "2":
                        System.out.println("Escribe un integer que represente el numero de bits que quieres leer");
                        Integer numBits1 = Integer.parseInt(reader.readLine().trim());
                        byte a = inp.getBits(numBits1);
                        System.out.println(a);
                    break;
                    case "3":
                        System.out.println("Escribe un integer que represente el numero de bits que quieres leer");
                        Integer numBits2 = Integer.parseInt(reader.readLine().trim());
                        ArrayList<Byte> b= inp.getMoreBits(numBits2);
                        System.out.println(b);
                    break;
                    case "4":
                        String aux = "";
                        if(inp.finished()){
                            aux = "Input ha terminado de leer";
                        }else{
                            aux = "Input aún puede leer";
                        }
                        System.out.println(aux);
                    break;
                    case "5":
                        System.out.println("Los dos primeros bits del fichero comprimido indican que se comprimió con el algoritmo " + inp.getDecodeAlg());
                    break;
                    case "6":
                        path = "../data/Input1.txt";
                    break;
                    case "7":
                    path = "../data/Input1.lz78";
                    break;
                    case "8":
                    path = "../data/Input1.lzw";
                    break;
                    case "9":
                    path = "../data/Input1.lzss";
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
