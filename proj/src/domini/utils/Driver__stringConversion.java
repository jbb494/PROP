package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__stringConversion
 * @brief Driver de stringConversion
 * @author 
 */
public class Driver__stringConversion {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de stringConversion");
		System.out.println("Constructores: ");

		System.out.println("Funciones: ");
		System.out.println("	 1. atoi(String str) ");
		System.out.println("	 Input: 1");
		System.out.println("	 2. intToString(int x) ");
		System.out.println("	 Input: 2");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(stringConversion stringconversion, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param stringconversion
	 * @param linea
	 */
	private static void comprovarExcepcions(stringConversion stringconversion, String linea){
		int op = Integer.parseInt(linea);
		if(stringconversion == null && op > 0) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		stringConversion stringconversion = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(stringconversion, linea);
			switch(linea){
				case "1":
					System.out.println("");
					String aux1 = reader.readLine().trim();
					String str;
					stringconversion.atoi(str);
				break;
				case "2":
					System.out.println("");
					String aux2 = reader.readLine().trim();
					int x;
					stringconversion.intToString(x);
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
