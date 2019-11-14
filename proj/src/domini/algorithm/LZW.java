/**
 * @class Algoritme LZW
 * @brief Compressió i descompressió pel mètode LZW
 * @author Miguel Paracuellos Ocaña 
 */

package domini.algorithm;

import java.util.*;

import persistencia.input.Ctrl_Input_LZW;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;
import domini.utils.LZW_Dict_Decode;
import domini.utils.LZW_Dict_Encode;

public class LZW {

	//Attributes
	/**
	 * @param Limit Màxim nombre d'entrades del diccionari
	 */
	final Integer Limit = Integer.MAX_VALUE;

	/**
	 * @param Output Del tipus Ctrl_Output, li passarem el text processat
	 */
	Ctrl_Output Output;


	//Constructor
	/**
	 * @brief Constructor de la clase LZW
	 * @param aux Path de sortida
	 */
	public LZW(String aux, boolean b) {
		Output = new Ctrl_Output(aux, "lzw", b);
	}


	//Functions
	/**
	 * @fn public ArrayList<Integer> compression(Ctrl_Input_Text inp)
	 * @brief Comprimim un text amb l'algoritme LZW
	 * @param inp accés al Controlador d'Input per el text
	 * @return llista amb els enters que representen el text
	 */
	public ArrayList<Integer> compression(Ctrl_Input_Text inp) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		//We initialize the attributes we need
		LZW_Dict_Encode table = new LZW_Dict_Encode();
		Integer i = -1;
		
		System.out.println("IM IN");

		while (!inp.finished()) {
			byte c = inp.get();
			
			/*String s1 = String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0');
			System.out.println(s1);
			System.out.println(c);*/

			Integer aux = i;

			if ( (i = table.search_and_insert_BST(aux, c)) == -1) {
				result.add(aux);
				System.out.println(aux);
				//Ahora le estoy pasando un byte, arreglarlo
				i = table.Ascii_value(c);
			}
		}

		
		if (i != -1) {
			result.add(i);	
			System.out.println(i);	
		}
		
		return result;
	}

	/**
	 * @fn public String decompression(Ctrl_Input_LZW inp)
	 * @brief Descomprimim un fitxer amb l'algoritme LZW
	 * @param inp accés al Controlador d'Input pel fitxer comprimit
	 * @return text que representa el fitxer descomprimit
	 */
	public ArrayList<Byte> decompression(Ctrl_Input_LZW inp) {
		ArrayList<Byte> result = new ArrayList<>();
		
		LZW_Dict_Decode table = new LZW_Dict_Decode();
		Integer i = -1;
		
		while (!inp.finished()) {
			Integer k = inp.get();
			
			//System.out.println(k);
			if (inp.finished()) return result;
			
			Integer sz = table.getSize();
			ArrayList<Byte> s = new ArrayList<>();

			//Dictionary's maximum size -> Reset it
			if (sz == Limit) { 
				table.reset_dictionary();
			}				
			else if (k > sz) {
				System.out.println("Liada");
			}		
			else if (k < sz) {
				s = table.getWord(k);

				if (i != -1) {
					table.add(i, s.get(0));
				}
			}
			else {
				table.add(i, table.getWord(i).get(0));
				s = table.getWord(k);
			}

			result.addAll(s);
			i = k;
		}
		return result;
	}

	/**
	 * @fn public Ctrl_Output print_encode(Ctrl_Input_Text inp)
	 * @brief La funció serà cridada quan volguem comprimir un text i obtenir el resultat
	 * @param inp Controlador d'Input amb l'accés al fitxer
	 * @return Controlador d'Output per poder esciure el resultat
	 */
	public Ctrl_Output print_encode(Ctrl_Input_Text inp) {
		System.out.println("PRE");
		ArrayList<Integer> arr = compression(inp);
		
		for (int i = 0; i < arr.size(); ++i) {
			Output.add(arr.get(i));
		}

		System.out.println("POST");

		return Output;
	}

	/**
	 * @fn public Ctrl_Output print_decode(Ctrl_Input_LZW inp)
	 * @brief La funció serà cridada quan volguem descomprimir un text i obtenir el resultat
	 * @param inp Controlador d'Input amb l'accés al fitxer comprimit amb LZW
	 * @return Controlador d'Output per poder esciure el resultat
	 */
	public Ctrl_Output print_decode(Ctrl_Input_LZW inp) {
		ArrayList<Byte> arr = decompression(inp);
		
		for (byte b : arr) {
			Output.add(b,8);
		}

		return Output;
	}
	
}
