package persistencia.input;
 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Ctrl_Input_Img
 * @brief Driver de Ctrl_Input_Img
 * @author 
 */
public class Driver__Ctrl_Input_Img {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Ctrl_Input_Img");
		System.out.println("Constructores: ");
		System.out.println("	 1. Ctrl_Input_Img(String path)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. getHeight() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getWidth() ");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Ctrl_Input_Img ctrl_input_img, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param ctrl_input_img
	 * @param linea
	 */
	private static void comprovarExcepcions(Ctrl_Input_Img ctrl_input_img, String linea){
		int op = Integer.parseInt(linea);
		if(ctrl_input_img == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Ctrl_Input_Img ctrl_input_img = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(ctrl_input_img, linea);
			switch(linea){
				case "1":
					System.out.println("");
					String aux1 = reader.readLine().trim();
					String path;
					ctrl_input_img = new Ctrl_Input_Img(path);
				break;
				case "2":
					ctrl_input_img.getHeight();
				break;
				case "3":
					ctrl_input_img.getWidth();
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
