package domini.algorithm;
 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Huffman
 * @brief Driver de Huffman
 * @author Joan Lapeyra Amat
 */
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

		System.out.println("Funciones: ");
		System.out.println("	 2. getCode(int symbol) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getSize(int symbol) ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. getSymbol(int code) ");
		System.out.println("	 Input: 4");
		System.out.println("	 5. initSearchSymbol() ");
		System.out.println("	 Input: 5");
		System.out.println("	 6. searchSymbol(int bit) ");
		System.out.println("	 Input: 6");
		System.out.println("	 7. getFoundSymbol() ");
		System.out.println("	 Input: 7");
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
		if(huffman == null && op > 1) {
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
			int x;
			switch(linea){
				case "1":
					System.out.println("");
					System.out.println("Escribe un 0 para Huffman manual y un 1 para Huffman automatico");
					//String aux1 = reader.readLine().trim();
					huffman = new Huffman();
				break;
				case "2":
					System.out.println("");
					System.out.println("Escribe un simbolo");
					x = huffman.getCode(Integer.parseInt(reader.readLine().trim()));
					System.out.println(x);
				break;
				case "3":
					System.out.println("");
					System.out.println("Escribe un simbolo");
					x = huffman.getSize(Integer.parseInt(reader.readLine().trim()));
					System.out.println(x);
				break;
				case "4":
					System.out.println("");
					System.out.println("Escribe un simbolo");
					x = huffman.getSymbol(Integer.parseInt(reader.readLine().trim()));
					System.out.println(x);
				break;
				case "5":
					huffman.initSearchSymbol();
				break;
				case "6":
					System.out.println("");
					System.out.println("Escribe un bit");
					x = huffman.searchSymbol(Integer.parseInt(reader.readLine().trim()));
					if (x == 0) System.out.println("Sigue buscando.");
					else if (x == 1) System.out.println("Simbolo encontrado. Consultalo con getFoundSymbol()");
					else if (x == -1) System.out.println("No encontrado");
				break;
				case "7":
					x = huffman.getFoundSymbol();
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
