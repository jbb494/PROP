package domini.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import domini.utils.ArrayCircular;
import persistencia.input.Ctrl_Input_LZSS;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;

/**
 * @class Driver__LZSS
 * @brief Driver de LZSS
 * @author Manel Aguilar
 */
public class Driver__LZSS {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de LZSS");
		System.out.println("Constructores: ");
		System.out.println("	 1. LZSS(String aux, boolean b)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. KMPSearch(ArrayList<Byte> paraula, ArrayCircular text) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. FailArray(ArrayList<Byte> paraula, int M)");
		System.out.println("	 Input: 3");
		System.out.println("	 4. print()");
		System.out.println("	 Input: 4");
		System.out.println("	 5. Compressor(Ctrl_Input_Text in)");
		System.out.println("	 Input: 5");
		System.out.println("	 6. Decompressor(Ctrl_Input_LZSS in)");
		System.out.println("	 Input: 6");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(LZSS lzss, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param lzss
	 * @param linea
	 */
	private static void comprovarExcepcions(LZSS lzss, String linea){
		int op = Integer.parseInt(linea);
		if(lzss == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		LZSS lzss = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(lzss, linea);
			switch(linea){
				case "1":
					System.out.println("escribe el path del archivo \"out\". Luego escribe \"true\" si quieres descomprimir o \"false\" si quieres comprimir.");
					String[] aux1 = reader.readLine().trim().split(" ");
					String aux = aux1[0];
					boolean b = aux1[1].equals("true");
					if(!b && !aux1[1].equals("false"))throw new IllegalArgumentException("Entrada no valida");
					lzss = new LZSS(aux, b);
				break;
				case "2":
					System.out.println("escribe un conjunto de bytes separado por espacios que represente la palabra a buscar. Luego presiona enter e introduce el texto de la misma forma y por último el tamaño de este texto.");
					String[] aux2 = reader.readLine().trim().split(" ");
					ArrayList<Byte> paraula = new ArrayList<>();
					for(String foraux : aux2) paraula.add(Byte.parseByte(foraux));
					String[] aux3 = reader.readLine().trim().split(" ");
					int mida = Integer.parseInt(reader.readLine().trim());
					ArrayCircular text = new ArrayCircular(mida);
					for(String foraux1 : aux3) text.setValue(Byte.parseByte(foraux1));
					System.out.println(lzss.KMPSearch(paraula, text));
				break;
				case "3":
					System.out.println("escribe un conjunto de bytes separado por espacios. Luego haz enter e introduce su tamaño.");
					String[] aux4 = reader.readLine().trim().split(" ");
					ArrayList<Byte> word = new ArrayList<>();
					for(String foraux4 : aux4){
						word.add(Byte.parseByte(foraux4));
					}
					String intaux = reader.readLine().trim();
					int intaux1 = Integer.parseInt(intaux);
					if(intaux1 != word.size()) throw new IllegalArgumentException("La array no tiene el mismo tamaño que el entero");
					int i[] = lzss.FailArray(word, intaux1);
					for(int j = 0; j < intaux1; j++) System.out.print(i[j] + " ");
					System.out.println("");
				break;
				case "4":
					Ctrl_Output a = lzss.print();
					a.printString();
					a.print();
				break;
				case "5":
					System.out.println("escribe el path del archivo que quieres comprimir.");
					String a3 = reader.readLine().trim();
					String path = a3;
					Ctrl_Input_Text in = new Ctrl_Input_Text(path);
					lzss.Compressor(in);
				break;
				case "6":
					System.out.println("escribe el path del archivo que quieres descomprimir");
					String a4 = reader.readLine().trim();
					path = a4;
					Ctrl_Input_LZSS in2 = new Ctrl_Input_LZSS(path);
					lzss.Decompressor(in2);
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
