package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Node
 * @brief Driver de Node
 * @author Joan Bellavista Bartroli
 */
public class Driver__Node {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Node");
		System.out.println("Constructores: ");
		System.out.println("	 1. Node(byte c)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. Modify_Left(Integer i) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. Modify_Right(Integer i) ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. Modify_First(Integer i) ");
		System.out.println("	 Input: 4");
		System.out.println("	 5. Return_Left() ");
		System.out.println("	 Input: 5");
		System.out.println("	 6. Return_Right() ");
		System.out.println("	 Input: 6");
		System.out.println("	 7. Return_First() ");
		System.out.println("	 Input: 7");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Node node, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param node Instància Node
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Node node, String linea){
		int op = Integer.parseInt(linea);
		if(node == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Node node = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(node, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe un byte");
					String aux1 = reader.readLine().trim();
					byte c = Byte.parseByte(aux1);
					node = new Node(c);
				break;
				case "2":
					System.out.println("Escribe un entero que represente el nuevo valor que obtendra el parametro \"Left\"");
					String aux2 = reader.readLine().trim();
					Integer i = Integer.parseInt(aux2);
					node.Modify_Left(i);
				break;
				case "3":
					System.out.println("Escribe un entero que represente el nuevo valor que obtendra el parametro \"Right\"");
					String aux3 = reader.readLine().trim();
					i = Integer.parseInt(aux3);
					node.Modify_Right(i);
				break;
				case "4":
					System.out.println("Escribe un entero que represente el nuevo valor que obtendra el parametro \"First\"");
					String aux4 = reader.readLine().trim();
					i = Integer.parseInt(aux4);
					node.Modify_First(i);
				break;
				case "5":
					System.out.println("El valor de Left es " + node.Left);
				break;
				case "6":
					System.out.println("El valor de Right es " + node.Right);
				break;
				case "7":
					System.out.println("El valor de First es " + node.First);
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
