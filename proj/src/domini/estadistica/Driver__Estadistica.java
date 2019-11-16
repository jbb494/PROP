package domini.estadistica; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Estadistica
 * @brief Driver de Estadistica
 * @author Joan Bellavista Bartroli
 */
public class Driver__Estadistica {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Estadistica");
		System.out.println("Constructores: ");
		System.out.println("	 1. Estadistica()");
		System.out.println("	 Input: 1");

		System.out.println("\nFunciones: ");
		System.out.println("	 2. show_estadistica(String inp, String out) ");
		System.out.println("	 Input: 2");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Estadistica estadistica, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param estadistica
	 * @param linea
	 */
	private static void comprovarExcepcions(Estadistica estadistica, String linea){
		int op = Integer.parseInt(linea);
		if(estadistica == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Estadistica estadistica = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(estadistica, linea);
			switch(linea){
				case "1":
					estadistica = new Estadistica();
				break;
				case "2":
					System.out.println("Indica el fichero para comprimir y el comprimido");
					String[] aux = reader.readLine().trim().split(" ");
					String inp = aux[0];
					String out = aux[1];
					estadistica.show_estadistica(inp, out);
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
