package persistencia.input;
 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Ctrl_Input_JPEG
 * @brief Driver de Ctrl_Input_JPEG
 * @author Joan Lapeyra Amat
 */
public class Driver__Ctrl_Input_JPEG {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Ctrl_Input_JPEG");
		System.out.println("Constructores: ");
		System.out.println("	 1. Ctrl_Input_JPEG(String path)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. getHeight() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getWidth() ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. get(int size) ");
		System.out.println("	 Input: 4");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Ctrl_Input_JPEG ctrl_input_jpeg, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param ctrl_input_jpeg
	 * @param linea
	 */
	private static void comprovarExcepcions(Ctrl_Input_JPEG ctrl_input_jpeg, String linea){
		int op = Integer.parseInt(linea);
		if(ctrl_input_jpeg == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Ctrl_Input_JPEG ctrl_input_jpeg = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(ctrl_input_jpeg, linea);
			int x;
			switch(linea){
				case "1":
					System.out.println("Indica el path de la fuente");
					String aux1 = reader.readLine().trim();
					ctrl_input_jpeg = new Ctrl_Input_JPEG(aux1);
				break;
				case "2":
					x = ctrl_input_jpeg.getHeight();
					System.out.println(x);
				break;
				case "3":
					x = ctrl_input_jpeg.getWidth();
					System.out.println(x);

				break;
				case "4":
					System.out.println("Cuantos bits quieres leer?");
					String aux4 = reader.readLine().trim();
					int size = Integer.parseInt(aux4);
					x = ctrl_input_jpeg.get(size);
					System.out.println(x);
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
