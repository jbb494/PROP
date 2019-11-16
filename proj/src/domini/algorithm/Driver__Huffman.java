package domini.algorithm; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Driver__Huffman {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Huffman");
		System.out.println("Constructores: ");
		System.out.println("	 1. Huffman(boolean automatic)");
		System.out.println("	 Input: 1");

		System.out.println("\nFunciones: ");
		System.out.println("	 2. set_auto_codes() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. bit_from_code(int n, long cd) ");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Huffman huffman, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param huffman
	 * @param linea
	 */
	private static void comprovarExcepcions(Huffman huffman, String linea){
		int op = Integer.parseInt(linea);
		if(huffman == null && op > 1 && op != 3) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Huffman huffman = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(huffman, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe \"cierto\" o \"falso\" dependiendo de si quieres que sea automatico o no");
					String aux1 = reader.readLine().trim();
					boolean automatic = aux1.equals("cierto");
					huffman = new Huffman(automatic);
				break;
				case "2":
					huffman.set_auto_codes();
				break;
				case "3":
					System.out.println("Escribe un integer y un long (n y cd)");
					String[] aux3 = reader.readLine().trim().split(" ");
					int n = Integer.parseInt(aux3[0]);
					long cd = Long.parseLong(aux3[1]);
					int ret = Huffman.bit_from_code(n, cd);
					System.out.println(ret);
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