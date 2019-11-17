package domini.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import persistencia.input.Ctrl_Input_LZW;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;

/**
 * @class Driver__LZW
 * @brief Driver de LZW
 * @author Joan Bellavista Bartroli
 */
public class Driver__LZW {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de LZW");
		System.out.println("Constructores: ");
		System.out.println("	 1. LZW(String aux, boolean b)");
		System.out.println("	 Input: 1");

		System.out.println("\nFunciones: ");
		System.out.println("	 2. compression(Ctrl_Input_Text inp) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. decompression(Ctrl_Input_LZW inp) ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. print() ");
		System.out.println("	 Input: 4");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(LZW lzw, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param lzw
	 * @param linea
	 */
	private static void comprovarExcepcions(LZW lzw, String linea){
		int op = Integer.parseInt(linea);
		if(lzw == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		LZW lzw = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(lzw, linea);
			switch(linea){
				case "1":
					System.out.println("escribe el path del archivo \"out\" separado por espacios. Luego escribe \"true\" si quieres descomprimir o \"false\" si quieres comprimir.");
					String[] aux1 = reader.readLine().trim().split(" ");
					String aux = aux1[0];
					boolean b = aux1[1].equals("true");
					if(!b && !aux1[1].equals("false"))throw new IllegalArgumentException("Entrada no valida");
					lzw = new LZW(aux, b);
				break;
				case "2":
					System.out.println("escribe el path del archivo que quieres comprimir.");
					String aux2 = reader.readLine().trim();
					String path = aux2;
					Ctrl_Input_Text inp = new Ctrl_Input_Text(path);
					lzw.compression(inp);
				break;
				case "3":
					System.out.println("escribe el path del archivo que quieres descomprimir");
					String aux3 = reader.readLine().trim();
					path = aux3;
					Ctrl_Input_LZW inp2 = new Ctrl_Input_LZW(path);;
					lzw.decompression(inp2);
				break;
				case "4":
					Ctrl_Output a = lzw.print();
					a.printString();
					a.print();					
				break;
				case "0":
					return;
				default:
					System.out.println("La opción no es válida");
				break;
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
}
