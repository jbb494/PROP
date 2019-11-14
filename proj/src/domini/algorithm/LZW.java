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
		ArrayList<Integer> result = new ArrayList<>();

		//We initialize the attributes we need
		LZW_Dict_Encode table = new LZW_Dict_Encode();
		Integer i = -1;
		
		while (!inp.finished()) {
			char c = (char)inp.get();  //Hola, soc el Lapeyra i he afegit (char) perquè compili
			Integer aux = i;

			if ( (i = table.search_and_insert_BST(aux, c)) == -1) {
				result.add(aux);
				i = table.Ascii_value(c);
			}
		}

		
		if (i != -1)
			result.add(i);			
		
		return result;
	}

	/**
	 * @fn public String decompression(Ctrl_Input_LZW inp)
	 * @brief Descomprimim un fitxer amb l'algoritme LZW
	 * @param inp accés al Controlador d'Input pel fitxer comprimit
	 * @return text que representa el fitxer descomprimit
	 */
	public String decompression(Ctrl_Input_LZW inp) {
		String result = "";
		
		LZW_Dict_Decode table = new LZW_Dict_Decode();
		Integer i = -1;
		
		while (!inp.finished()) {
			Integer k = inp.get();
			
			Integer sz = table.getSize();
			String s = "";

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
					table.add(i, s.charAt(0));
				}
			}
			else {
				table.add(i, table.getWord(i).charAt(0));
				s = table.getWord(k);
			}

			result += s;
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
		ArrayList<Integer> arr = compression(inp);
		
		for (int i = 0; i < arr.size(); ++i) {
			Output.add(arr.get(i));
		}

		return Output;
	}

	/**
	 * @fn public Ctrl_Output print_decode(Ctrl_Input_LZW inp)
	 * @brief La funció serà cridada quan volguem descomprimir un text i obtenir el resultat
	 * @param inp Controlador d'Input amb l'accés al fitxer comprimit amb LZW
	 * @return Controlador d'Output per poder esciure el resultat
	 */
	public Ctrl_Output print_decode(Ctrl_Input_LZW inp) {
		String arr = decompression(inp);
		
		Output.add(arr);

		return Output;
	}
	
}
