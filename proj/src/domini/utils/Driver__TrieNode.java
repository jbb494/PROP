package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__TrieNode
 * @brief Driver de TrieNode
 * @author Joan Bellavista Bartroli
 */
public class Driver__TrieNode {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de TrieNode");
		System.out.println("Constructores: ");
		System.out.println("	 1. TrieNode(Integer index)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. getChildren() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getIndex()");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(TrieNode trienode, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param trienode
	 * @param linea
	 */
	private static void comprovarExcepcions(TrieNode trienode, String linea){
		int op = Integer.parseInt(linea);
		if(trienode == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		TrieNode<Byte> trienode = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(trienode, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe un entero que dara un valor al nodo root");
					String aux1 = reader.readLine().trim();
					Integer index = Integer.parseInt(aux1);
					trienode = new TrieNode<Byte>(index);
				break;
				case "2":
					trienode.getChildren();
				break;
				case "3":
					System.out.println(trienode.getIndex());
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
