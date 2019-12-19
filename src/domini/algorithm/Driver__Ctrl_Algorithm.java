package domini.algorithm; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Ctrl_Algorithm
 * @brief Driver de Ctrl_Algorithm
 * @author Joan Bellavista Bartroli
 */
public class Driver__Ctrl_Algorithm {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Ctrl_Algorithm");
		System.out.println("Constructores: ");
		System.out.println("	 1. Ctrl_Algorithm()");
		System.out.println("	 Input: 1");

		System.out.println("\nFunciones: ");
		System.out.println("	 2. Encode(String Path) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. Auto_Encoder(String Path)");
		System.out.println("	 Input: 3");
		System.out.println("	 4. Decode(String Path, String method, Integer img_quality)");
		System.out.println("	 Input: 4");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Ctrl_Algorithm ctrl_algorithm, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param ctrl_algorithm Instància Ctrl_Algorithm
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Ctrl_Algorithm ctrl_algorithm, String linea){
		int op = Integer.parseInt(linea);
		if(ctrl_algorithm == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Ctrl_Algorithm ctrl_algorithm = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(ctrl_algorithm, linea);
			switch(linea){
				case "1":
					ctrl_algorithm = new Ctrl_Algorithm();
				break;
				case "2":
					System.out.println("Escribe el path del archivo que quieres hacer la compresión y el metodo, y la calidad de compresion (separado por espacios). ");
					String[] aux = reader.readLine().trim().split(" ");
					String Path = aux[0];
					String method = aux[1];
					Double img_quality = Double.parseDouble(aux[2]);
					ctrl_algorithm.Encode(Path, method, img_quality);
				break;
				case "3":
					System.out.println("Escribe el path del archivo que quieres comprimir.");
					String aux1 = reader.readLine().trim();
					Path = aux1;
					String ret = ctrl_algorithm.Auto_Encoder(Path);
					System.err.println(ret);
				break;
				case "4":
					System.out.println("escribe el path del archivo comprimido que quieres descomprimir");
					String[] aux2 = reader.readLine().trim().split(" ");
					Path = aux2[0];
					ctrl_algorithm.Decode(Path);
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
