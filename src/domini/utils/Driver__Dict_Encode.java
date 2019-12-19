package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Dict_Encode
 * @brief Driver de Dict_Encode
 * @file

 * @author Joan Bellavista Bartroli
 */
public class Driver__Dict_Encode {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Dict_Encode");
		System.out.println("Constructores: ");
		System.out.println("	 1. Dict_Encode()");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. reset_dictionary() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. Ascii_value(byte c) ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. search_and_insert_BST(Integer i, byte c) ");
		System.out.println("	 Input: 4");
		System.out.println("	 5. getLimit() ");
		System.out.println("	 Input: 5");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Dict_Encode dict_encode, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param dict_encode Instància Dict_Encode
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Dict_Encode dict_encode, String linea){
		int op = Integer.parseInt(linea);
		if(dict_encode == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Dict_Encode dict_encode = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(dict_encode, linea);
			switch(linea){
				case "1":
					dict_encode = new Dict_Encode();
				break;
				case "2":
					dict_encode.reset_dictionary();
					System.out.println("El diccionari ha sigut reiniciat");
				break;
				case "3":
					System.out.println("Escribe un byte.");
					String aux3 = reader.readLine().trim();
					byte c = Byte.parseByte(aux3);
					System.out.println(dict_encode.Ascii_value(c));
				break;
				case "4":
					System.out.println("Escribe un entero que represente el puntero a una cadena de caracteres. Separado de un espacio escribe un byte");
					String[] aux4 = reader.readLine().trim().split(" ");
					Integer i = Integer.parseInt(aux4[0]);
					c = Byte.parseByte(aux4[1]);
					Integer ret = dict_encode.search_and_insert_BST(i, c);
					System.out.println(ret == -1 ? "no existe" : ret);
				break;
				case "5":
					dict_encode.getLimit();
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
