 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Input_Img
 * @brief Driver de Input_Img
 * @author Joan Bellavista Bartroli
 */
public class Driver__Input_Img {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Input_Img");
		System.out.println("Constructores: ");
		System.out.println("	 1. Input_Img(String path)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. getIn() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getHeight() ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. getWidth() ");
		System.out.println("	 Input: 4");
		System.out.println("	 5. /*ArrayList< ArrayList < ArrayList <Byte> > > ReadImg(String nomImg) throws IOException");
		System.out.println("	 Input: 5");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Input_Img input_img, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param input_img
	 * @param linea
	 */
	private static void comprovarExcepcions(Input_Img input_img, String linea){
		int op = Integer.parseInt(linea);
		if(input_img == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Input_Img input_img = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(input_img, linea);
			switch(linea){
				case "1":
					System.out.println("");
					String aux1 = reader.readLine().trim();
					String path;
					input_img = new Input_Img(path);
				break;
				case "2":
					input_img.getIn();
				break;
				case "3":
					input_img.getHeight();
				break;
				case "4":
					input_img.getWidth();
				break;
				case "5":
					System.out.println("");
					String aux5 = reader.readLine().trim();
					/*ArrayList< ArrayList < ArrayList <Byte> > > ReadImg(String nomImg throws IOException;
					input_img./*ArrayList< ArrayList < ArrayList <Byte> > > ReadImg(ArrayList);
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
