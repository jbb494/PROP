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
import domini.utils.Dict_Decode;
import domini.utils.Dict_Encode;

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
	public void compression(Ctrl_Input_Text inp) {

		//We initialize the attributes we need
		Dict_Encode table = new Dict_Encode();
		Integer i = -1;
		
		while (!inp.finished()) {
			byte c = inp.get();

			Integer aux = i;

			if ( (i = table.search_and_insert_BST(aux, c)) == -1) {
				Output.add(aux);
				i = table.Ascii_value(c);
			}
		}

		
		if (i != -1) {
			Output.add(i);	
		}
	}

	/**
	 * @fn public String decompression(Ctrl_Input_LZW inp)
	 * @brief Descomprimim un fitxer amb l'algoritme LZW
	 * @param inp accés al Controlador d'Input pel fitxer comprimit
	 * @return text que representa el fitxer descomprimit
	 */
	public void decompression(Ctrl_Input_LZW inp) {
		
		Dict_Decode table = new Dict_Decode(true, -1);
		Integer i = -1;
		
		while (!inp.finished()) {
			Integer k = inp.get();
			
			if (inp.finished()) return;
			
			Integer sz = table.getSize();
			ArrayList<Byte> s = new ArrayList<>();

			//Dictionary's maximum size -> Reset it
			if (sz == Limit) { 
				table.reset_dictionary(true);
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
			Output.add(s);
			i = k;
		}
	}

	/**
	 * @fn public Ctrl_Output print()
	 * @brief La funció serà cridada quan volguem obtenir el resultat d'una compressio o descompressio
	 * @return Controlador d'Output per poder esciure el resultat
	 */
	public Ctrl_Output print() {
		return Output;
	}
}
