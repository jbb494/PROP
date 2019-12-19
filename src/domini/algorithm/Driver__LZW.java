package domini.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import persistencia.input.Ctrl_Input;

/**
 * @class Driver__LZW
 * @brief Driver de LZW
 * @file
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
		System.out.println("	 2. Compressor() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. Decompressor() ");
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
	 * @param lzw Instància LZW
	 * @param linea Número de operació realitzada
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
					Ctrl_Input.initialize(path);
					lzw.Compressor();
				break;
				case "3":
					System.out.println("escribe el path del archivo que quieres descomprimir");
					String aux3 = reader.readLine().trim();
					path = aux3;
					Ctrl_Input in2 = new Ctrl_Input(path);
					System.out.println("La metadata del fitxer correspon al tipus "+in2.getAlg());
					lzw.Decompressor();
				break;
				case "4":
					lzw.print();
					//a.printString();
					// a.print();					
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
