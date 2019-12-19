package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @class Driver__Trie
 * @brief Driver de Trie
 * @file

 * @author Joan Bellavista Bartroli
 */
public class Driver__Trie {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Trie");
		System.out.println("Constructores: ");
		System.out.println("	 1. Trie()");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. insert(ArrayList<Byte> word, Integer index) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. indexNode(ArrayList<Byte> word) ");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Trie trie, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param trie Instància Trie
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Trie<Byte> trie, String linea){
		int op = Integer.parseInt(linea);
		if(trie == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Trie<Byte> trie = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(trie, linea);
			switch(linea){
				case "1":
					trie = new Trie<Byte>();
				break;
				case "2":
					System.out.println("Escribe un conjunto de bytes separado por espacios que representaran una palabra que añadiremos a la estructura. Luego presiona enter y escribe el indice que identificara esta palabra.");
					String[] auxWord = reader.readLine().trim().split(" ");
					String auxIndex = reader.readLine().trim();
					ArrayList<Byte> word = new ArrayList<Byte>();
					for(String foraux : auxWord){
						word.add(Byte.parseByte(foraux));
					}
					Integer index = Integer.parseInt(auxIndex);
					trie.insert(word, index);
				break;
				case "3":
					System.out.println("Escribe un conjunto de bytes separado por espacios que representaran una palabra.");
					String[] aux3 = reader.readLine().trim().split(" ");
					word = new ArrayList<Byte>();
					for(String foraux1 : aux3){
						word.add(Byte.parseByte(foraux1));
					}
					Integer ret = trie.indexNode(word);
					System.out.println(ret == -1 ? "No existe" : ret);
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
