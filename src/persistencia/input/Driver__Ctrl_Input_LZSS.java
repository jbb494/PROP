package persistencia.input; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

import domini.utils.IntorByte;

/**
 * @class Driver__Ctrl_Input_LZSS
 * @brief Driver de Ctrl_Input_LZSS
 * @file

 * @author Manel Aguilar
 */
public class Driver__Ctrl_Input_LZSS {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Ctrl_Input_LZSS");
		System.out.println("Constructores: ");
		System.out.println("	 1. Ctrl_Input_LZSS(String path)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. getLZSS()");
		System.out.println("	 Input: 2");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Ctrl_Input_LZSS ctrl_input_lzss, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param ctrl_input_lzss Instància Ctrl_Input_LZSS
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Ctrl_Input_LZSS ctrl_input_lzss, String linea){
		int op = Integer.parseInt(linea);
		if(ctrl_input_lzss == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Ctrl_Input_LZSS ctrl_input_lzss = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(ctrl_input_lzss, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe el path a un fichero comprimido por LZSS");
					String aux1 = reader.readLine().trim();
					String path = aux1;
					ctrl_input_lzss = new Ctrl_Input_LZSS(path);
				break;
				case "2":
					IntorByte iob =  ctrl_input_lzss.getLZSS();
					if(iob.IsIntorByte()) System.out.println(iob.GetByte());
					else { System.out.println(iob.GetDespl()); System.out.println(iob.GetMida()); }
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
