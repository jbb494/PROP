package domini.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import persistencia.input.Ctrl_Input_Img;
import persistencia.input.Ctrl_Input_JPEG;

/**
 * @class Driver__JPEG
 * @brief Driver de JPEG
 * @author 
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
		System.out.println("	 2. encode(Ctrl_Input_Img in) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. decode(Ctrl_Input_JPEG in) ");
		System.out.println("	 Input: 3");
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
					System.out.println("");
					String[] aux1 = reader.readLine().trim().split(" ");
					String aux;
					boolean b;
					jpeg = new JPEG(aux, b);
				break;
				case "2":
					System.out.println("");
					String aux2 = reader.readLine().trim();
					Ctrl_Input_Img in;
					jpeg.encode(in);
				break;
				case "3":
					System.out.println("");
					String aux3 = reader.readLine().trim();
					Ctrl_Input_JPEG in;
					jpeg.decode(in);
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
