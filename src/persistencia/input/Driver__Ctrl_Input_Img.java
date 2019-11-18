package persistencia.input;
 

import java.io.BufferedReader;
import java.io.InputStreamReader;

import persistencia.input.Ctrl_Input_Img;

/**
 * @class Driver__Ctrl_Input_Img
 * @brief Driver de Ctrl_Input_Img
 * @author Joan Lapeyra Amat
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
		System.out.println("	 4. get() ");
		System.out.println("	 Input: 4");
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
			int x;
			switch(linea){
				case "1":
					System.out.println("Indica el path de la fuente");
					String aux1 = reader.readLine().trim();
					ctrl_input_img = new Ctrl_Input_Img(aux1);
				break;
				case "2":
					x = ctrl_input_img.getHeight();
					System.out.println(x);
				break;
				case "3":
					x = ctrl_input_img.getWidth();
					System.out.println(x);
				break;
				case "4":
					double[][][][] mat = ctrl_input_img.get();
					int n = ctrl_input_img.getWidth()/8;
					for (int bk = 0; bk < n; ++bk) {
						for (int i = 0; i < 8; ++i) {
							for (int j = 0; j < 8; ++j) {
								System.out.print("(");
								for (int k = 0; k < 3; ++k) {
									System.out.print(mat[bk][i][j][k]);
									if (k != 2) System.out.print(" ");
								}
								System.out.print(")  ");
							}
							System.out.println();
						}
						System.out.println();
					}
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
