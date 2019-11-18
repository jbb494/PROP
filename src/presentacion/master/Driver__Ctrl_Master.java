package presentacion.master; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Ctrl_Master
 * @brief Driver de Ctrl_Master
 * @author Manel Aguilar
 */
public class Driver__Ctrl_Master {

	/**
	 * @param act Condició per una excepció.
	*/
	private static boolean act;
	
	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Ctrl_Master");
		System.out.println("Constructores: ");
		System.out.println("	 1. Ctrl_Master()");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. Context() ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. Work() ");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Ctrl_Master ctrl_master, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param ctrl_master Instància Ctrl_Master
	 * @param linea Número de operació realitzada
	 */
	private static void comprovarExcepcions(Ctrl_Master ctrl_master, String linea){
		int op = Integer.parseInt(linea);
		if(ctrl_master == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
		if(act == false && op == 3) 
			throw new IllegalArgumentException("Debes hacer primero la opción 2.");
	}

	public static void main(String[] args) {
	try {
		Ctrl_Master ctrl_master = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(ctrl_master, linea);
			switch(linea){
				case "1":
					ctrl_master = new Ctrl_Master();
					act = false;
				break;
				case "2":
					ctrl_master.Context();
					act = true;
				break;
				case "3":
					System.out.println(ctrl_master.Work());
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
