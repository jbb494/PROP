package persistencia.input; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @class Driver__Input_Text
 * @brief Driver de Input_Text
 * @author Joan Bellavista Bartroli
 */
public class Driver__Input_Text {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Input_Text");
		System.out.println("Constructores: ");
		System.out.println("	 1. Input_Text(String path)");
		System.out.println("	 Input: 1");

		System.out.println("	 2. getBits(int num_bits) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getIn()");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Input_Text input_text, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param input_text Instància Input_Text
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Input_Text input_text, String linea){
		int op = Integer.parseInt(linea);
		if(input_text == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Input_Text input_text = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(input_text, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe el path del archivo que quieres leer");
					String aux = reader.readLine().trim();
					String path = aux;
					input_text = new Input_Text(path);
				break;
				case "2":
					System.out.println("Escribe el numero de bits que quieres leer");
					aux = reader.readLine().trim();
					int num_bits = Integer.parseInt(aux);
					input_text.getBits(num_bits);
				break;
				case "3":
					ArrayList<Byte> a1 = input_text.getIn();
					System.out.println(a1);
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
