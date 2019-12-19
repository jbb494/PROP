package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Pair
 * @brief Driver de Pair
 * @file

 * @author Joan Bellavista Bartroli
 */
public class Driver__Pair {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Pair");
		System.out.println("Constructores: ");
		System.out.println("	 1. Pair(Byte i, Byte c)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. getLeft() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getRight() ");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Pair pair, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param pair Instància Pair
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Pair<Byte, Byte> pair, String linea){
		int op = Integer.parseInt(linea);
		if(pair == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Pair<Byte, Byte> pair = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(pair, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe primero el Byte izquierdo y separado por un espacio el Byte derecho.");
					String[] aux1 = reader.readLine().trim().split(" ");
					Byte i = Byte.parseByte(aux1[0]);
					Byte c = Byte.parseByte(aux1[1]);;
					pair = new Pair<Byte, Byte>(i, c);
				break;
				case "2":
					System.out.println(pair.getLeft());
				break;
				case "3":
				System.out.println(pair.getRight());
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
