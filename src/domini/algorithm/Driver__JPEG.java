package domini.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import persistencia.input.Ctrl_Input;

/**
 * @class Driver__JPEG
 * @brief Driver de JPEG
 * @file
 * @author Joan Lapeyra Amat
 */
public class Driver__JPEG {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de JPEG");
		System.out.println("Constructores: ");
		System.out.println("	 1. JPEG(String aux, boolean b)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
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
	 * @fn private static void comprovarExcepcions(JPEG jpeg, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param jpeg
	 * @param linea
	 */
	private static void comprovarExcepcions(JPEG jpeg, String linea){
		int op = Integer.parseInt(linea);
		if(jpeg == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		JPEG jpeg = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(jpeg, linea);
			switch(linea){
				case "1":
					System.out.println("Introduce el path del output");
					String aux11 = reader.readLine().trim();
					System.out.println("Quiere comprimir o descomprimir?");
					String aux12 = reader.readLine().trim();
					jpeg = new JPEG(aux11, !aux12.toLowerCase().equals("comprimir"));
				break;
				case "2":
					System.out.println("Introduce el path del input");
					String aux2 = reader.readLine().trim();
					Ctrl_Input.initialize(aux2);
					jpeg.Compressor();
				break;
				case "3":
					System.out.println("Introduce el path del input");
					String aux3 = reader.readLine().trim();
					Ctrl_Input in2 = new Ctrl_Input(aux3);
					System.out.println("La metadata del fitxer correspon al tipus "+in2.getAlg());
					jpeg.Decompressor();
				break;
				case "4":
					jpeg.print();
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
