package persistencia.input; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Ctrl_Input_LZW
 * @brief Driver de Ctrl_Input_LZW
 * @author Manel Aguilar
 */
public class Driver__Ctrl_Input_LZW {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Ctrl_Input_LZW");
		System.out.println("Constructores: ");
		System.out.println("	 1. Ctrl_Input_LZW(String path)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. get() ");
		System.out.println("	 Input: 2");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Ctrl_Input_LZW ctrl_input_lzw, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param ctrl_input_lzw
	 * @param linea
	 */
	private static void comprovarExcepcions(Ctrl_Input_LZW ctrl_input_lzw, String linea){
		int op = Integer.parseInt(linea);
		if(ctrl_input_lzw == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Ctrl_Input_LZW ctrl_input_lzw = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(ctrl_input_lzw, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe el path a un fichero comprimido por LZW");
					String aux1 = reader.readLine().trim();
					String path = aux1;
					ctrl_input_lzw = new Ctrl_Input_LZW(path);
				break;
				case "2":
					System.out.println(ctrl_input_lzw.get());;
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
