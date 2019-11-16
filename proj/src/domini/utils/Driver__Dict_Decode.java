package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @class Driver__Dict_Encode
 * @brief Driver de Dict_Encode
 * @author Joan Bellavista Bartroli
 */
public class Driver__Dict_Decode {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Dict_Decode");
		System.out.println("Constructores: ");
		System.out.println("	 1. Dict_Decode(Boolean inicializeAscii, Integer valorNeutre)");
		System.out.println("	 Input: 1");

		System.out.println("\nFunciones: ");
		System.out.println("	 2. reset_dictionary(Boolean b) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. getWord(Integer i) ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. getSize() ");
		System.out.println("	 Input: 4");
		System.out.println("	 5. add(Integer i, byte c) ");
		System.out.println("	 Input: 5");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Dict_Decode dict_decode, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param dict_decode
	 * @param linea
	 */
	private static void comprovarExcepcions(Dict_Decode dict_decode, String linea){
		int op = Integer.parseInt(linea);
		if(dict_decode == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Dict_Decode dict_decode = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(dict_decode, linea);
			switch(linea){
				case "1":
					System.out.println("escribe \"true\" si quieres que el diccionario se inicialize con la tabla ascii. escribe \"false\" de lo contrario. Separado de un espacio escribe un integer que represente el valor neutro");
					String[] aux1 = reader.readLine().trim().split(" ");
					Boolean inicializeAscii = aux1[0].equals("true");
					if(!inicializeAscii && !aux1[0].equals("false"))throw new IllegalArgumentException("Entrada no valida");
					Integer valorNeutre = Integer.parseInt(aux1[1]);
					dict_decode = new Dict_Decode(inicializeAscii, valorNeutre);
				break;
				case "2":
					System.out.println("escribe \"true\" si quieres que el diccionario se reinicialize con la tabla ascii. ");
					String aux2 = reader.readLine().trim();
					Boolean b = aux2.equals("true");
					if(!b && !aux2.equals("false"))throw new IllegalArgumentException("Entrada no valida");
					dict_decode.reset_dictionary(b);
				break;
				case "3":
					System.out.println("Escribe un integer.");
					String aux3 = reader.readLine().trim();
					Integer i = Integer.parseInt(aux3);
					ArrayList<Byte> ret = dict_decode.getWord(i);
					System.out.println(ret);
				break;
				case "4":
					Integer ret2 = dict_decode.getSize();
					System.out.println(ret2);
				break;
				case "5":
					System.out.println("escribe un integer que represente la cadena previa. Separado por un espacio escribe un byte");
					String[] aux5 = reader.readLine().trim().split(" ");
					Integer i2 = Integer.parseInt(aux5[0]);
					byte c = Byte.parseByte(aux5[1]);
					dict_decode.add(i2, c);
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
