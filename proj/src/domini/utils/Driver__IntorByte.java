package domini.utils; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__IntorByte
 * @brief Driver de IntorByte
 * @author Joan Bellavista Bartroli
 */
public class Driver__IntorByte {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de IntorByte");
		System.out.println("Constructores: ");
		System.out.println("	 1. IntorByte(boolean b)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. SetDespl(int despl)");
		System.out.println("	 Input: 2");
		System.out.println("	 3. SetMida(int mida)");
		System.out.println("	 Input: 3");
		System.out.println("	 4. SetByte(byte c)");
		System.out.println("	 Input: 4");
		System.out.println("	 5. GetDespl()");
		System.out.println("	 Input: 5");
		System.out.println("	 6. GetMida()");
		System.out.println("	 Input: 6");
		System.out.println("	 7. GetByte()");
		System.out.println("	 Input: 7");
		System.out.println("	 8. IsIntorByte()");
		System.out.println("	 Input: 8");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(IntorByte intorbyte, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param intorbyte
	 * @param linea
	 */
	private static void comprovarExcepcions(IntorByte intorbyte, String linea){
		int op = Integer.parseInt(linea);
		if(intorbyte == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		IntorByte intorbyte = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(intorbyte, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe \"true\" si es un byte, \"false\" si son dos enteros.");
					String aux1 = reader.readLine().trim();
					Boolean b = aux1.equals("true");
					if(!b && !aux1.equals("false"))throw new IllegalArgumentException("Entrada no valida");
					intorbyte = new IntorByte(b);
				break;
				case "2":
					System.out.println("Escribe un integer que represente el desplazamiento.");
					String aux2 = reader.readLine().trim();
					int despl = Integer.parseInt(aux2);
					intorbyte.SetDespl(despl);
				break;
				case "3":
					System.out.println("Escribe un integer que represente el tamaño");
					String aux3 = reader.readLine().trim();
					int mida = Integer.parseInt(aux3);
					intorbyte.SetMida(mida);
				break;
				case "4":
					System.out.println("Escribe un byte.");
					String aux4 = reader.readLine().trim();
					byte c = Byte.parseByte(aux4);
					intorbyte.SetByte(c);
				break;
				case "5":
					System.out.println(intorbyte.GetDespl());
				break;
				case "6":
					System.out.println(intorbyte.GetMida());
				break;
				case "7":
					System.out.println(intorbyte.GetByte());
				break;
				case "8":
					System.out.println(intorbyte.IsIntorByte() ? "Es un byte" : "Son dos enteros");
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
