package persistencia.output;

import persistencia.output.Output;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Output
 * @brief Classe Driver__Output
 * @file

 * @author Joan Bellavista Bartroli
 */
public class Driver__Output {
    /**
     * @param path Path a la carpeta de data
     */
    static final String path = "../src/persistencia/data/Driver__Output.out";

    /**
     * @fn private static void showOptions()
     * @brief Mostra les diferents accion que podem pendre durant l'execucio
     */
    private static void showOptions(){
        System.out.println("Driver de Output");
        System.out.println("Constructores: ");
        System.out.println("\t 1. initialize(String path)");
        System.out.println("\t Input: 1");
        System.out.println();

        System.out.println("Modificadores: ");
        System.out.println("\t 2. getInstance().Add(byte b, int n_bits)");
        System.out.println("\t Input: 2");
        System.out.println();

        System.out.println("Consultoras: ");
        System.out.println("\t 3. getInstance().print()");
        System.out.println("\t Input: 3");
        System.out.println();

        System.out.println("\t 0. Sortir");
        System.out.println("\t Input: 0");

        System.out.println("----------------------------------------");
    }

    

    /**
     * @fn public static void main(String[] args)
     * @brief Main de la classe
     * @param args
     */
    public static void main(String[] args) {
        try {
            //Output out = null;
            showOptions();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String linea = "";
            while(linea != null){
                System.out.println("Selecciona una opción:");
                linea = reader.readLine().trim();
                System.out.println("Opción: " + linea + " seleccionada");
                //comprovarExcepcions(out, linea);
                switch(linea){
                    case "1":
                        Output.initialize(path);
                    break;
                    case "2":
                    try{
                        System.out.println("Escribe un byte y un integer separado de un espacio");
                        String StringAux = reader.readLine().trim();
                        byte ByteAux = (byte)Integer.parseInt(StringAux.split(" ")[0]);
                        Integer midaN = Integer.parseInt(StringAux.split(" ")[1]);
                        
                        Output.getInstance().add(ByteAux, midaN);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    case "3":
                        Output.getInstance().print();
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
