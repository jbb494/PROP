package domini.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import persistencia.input.Ctrl_Input_Img;

/**
 * @class Driver__JPEG
 * @brief Driver de JPEG
 * @author Joan Bellavista Bartroli
 */
public class Driver__JPEG {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de JPEG");
		System.out.println("Constructores: ");
		System.out.println("	 1. JPEG()");
		System.out.println("	 Input: 1");

		System.out.println("\nFunciones: ");
		System.out.println("	 2. discrete_cosine_transform(double[][] mat1) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. inverse_discrete_cosine_transform(double[][] mat1) ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. encode(Ctrl_Input_Img in) ");
		System.out.println("	 Input: 4");
		System.out.println("	 5. decode(String path) ");
		System.out.println("	 Input: 5");
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
					jpeg = new JPEG();
				break;
				case "2":
					System.out.println("escribe la matriz separando por espacios y para nueva fila pon una \",\"");
					String aux2 = reader.readLine().trim();
					double[][] mat1 = {{1},{2}};
					JPEG.discrete_cosine_transform(mat1);
				break;
				case "3":
					System.out.println("");
					String aux3 = reader.readLine().trim();
					double[][] mat2 = {{1},{2}};
					JPEG.inverse_discrete_cosine_transform(mat2);
				break;
				case "4":
					System.out.println("");
					String aux4 = reader.readLine().trim();
					String path = aux4;
					Ctrl_Input_Img in = new Ctrl_Input_Img(path);
					jpeg.encode(in);
				break;
				case "5":
					System.out.println("");
					String aux5 = reader.readLine().trim();
					path = aux5;
					jpeg.decode(path);
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
