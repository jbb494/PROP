package domini.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @class Driver_byteToConversion
 * @brief Driver de byteToConversion
 * @author Joan Bellavista Bartroli
 */
public class Driver__byteToConversion {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de byteToConversion");
		System.out.println("Constructores: ");

		System.out.println("	 1. byteToCharacter(Byte b)");
		System.out.println("	 Input: 1");
		System.out.println("	 2. byteToInteger(List<Byte> bArg)");
		System.out.println("	 Input: 2");
		System.out.println("	 3. shift_right_logic(byte b, int despl) ");
		System.out.println("	 Input: 3");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(byteToConversion bytetoconversion, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param bytetoconversion
	 * @param linea
	 */
	private static void comprovarExcepcions(byteToConversion bytetoconversion, String linea){
		// int op = Integer.parseInt(linea);
	}

	public static void main(String[] args) {
	try {
		byteToConversion bytetoconversion = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(bytetoconversion, linea);
			switch(linea){
				case "1":
					System.out.println("escribe un byte");
					String aux1 = reader.readLine().trim();
					Byte b = Byte.parseByte(aux1);
					Character ret = byteToConversion.byteToCharacter(b);
					System.out.println(ret);
				break;
				case "2":
					System.out.println("escribe 4 bytes separados por espacios");
					String[] aux2 = reader.readLine().trim().split(" ");
					List<Byte> bArg = new ArrayList<Byte>(0);
					for (String aux22 : aux2){
						bArg.add(Byte.parseByte(aux22));
					}
					Integer ret2 = byteToConversion.byteToInteger(bArg);
					System.out.println(ret2);
				break;
				case "3":
					System.out.println("escribe un byte (entre 0 y 127) y un integer que indique el desplazamiento");
					String[] aux3 = reader.readLine().trim().split(" ");
					b = Byte.parseByte(aux3[0]);
					int despl = Integer.parseInt(aux3[1]);
					byte ret3 = byteToConversion.shift_right_logic(b, despl);
					System.out.println(ret3);
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
