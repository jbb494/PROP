package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Driver__ArrayCircular {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de ArrayCircular");
		System.out.println("Constructores: ");
		System.out.println("	 1. ArrayCircular(int mida)");
		System.out.println("	 Input: 1");
		System.out.println("\nFunciones: ");
		System.out.println("	 2. setValue(byte value)");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getValue(int index)");
		System.out.println("	 Input: 3");
		System.out.println("	 4. getValueAmbDespl(int despl)");
		System.out.println("	 Input: 4");
		System.out.println("	 5. getStart()");
		System.out.println("	 Input: 5");
		System.out.println("	 6. getEnd()");
		System.out.println("	 Input: 6");
		System.out.println("	 7. incStart()");
		System.out.println("	 Input: 7");
		System.out.println("	 8. incEnd()");
		System.out.println("	 Input: 8");
		System.out.println("	 9. isIn(byte value)");
		System.out.println("	 Input: 9");
		System.out.println("	 10. getAfegits()");
		System.out.println("	 Input: 10");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(ArrayCircular arraycircular, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param arraycircular
	 * @param linea
	 */
	private static void comprovarExcepcions(ArrayCircular arraycircular, String linea){
		int op = Integer.parseInt(linea);
		if(arraycircular == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		ArrayCircular arraycircular = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(arraycircular, linea);
			switch(linea){
				case "1":
					System.out.println("Pon un entero que representará el tamaño");
					String aux = reader.readLine().trim();
					int mida = Integer.parseInt(aux);
					arraycircular = new ArrayCircular(mida);
				break;
				case "2":
					System.out.println("Pon un Byte para añadir a la ArrayCircular");
					aux = reader.readLine().trim();
					byte value = Byte.parseByte(aux);
					arraycircular.setValue(value);
				break;
				case "3":
					System.out.println("Pon un entero para conseguir el valor en esa posición");
					aux = reader.readLine().trim();
					int index = Integer.parseInt(aux);
					System.out.println(arraycircular.getValue(index));;
				break;
				case "4":
					System.out.println("Pon un entero");
					aux = reader.readLine().trim();
					int despl = Integer.parseInt(aux);
					System.out.println(arraycircular.getValueAmbDespl(despl));;
				break;
				case "5":
					System.out.println(arraycircular.getStart());;
				break;
				case "6":
					System.out.println(arraycircular.getEnd());;
				break;
				case "7":
					arraycircular.incStart();
				break;
				case "8":
					arraycircular.incEnd();
				break;
				case "9":
					System.out.println("Pon un Byte para saber si está en la Array");
					aux = reader.readLine().trim();
					value = Byte.parseByte(aux);
					System.out.println(arraycircular.isIn(value));;
				break;
				case "10":
					System.out.println(arraycircular.getAfegits());
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
