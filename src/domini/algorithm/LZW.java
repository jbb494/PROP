package domini.algorithm;

import java.util.*;

import persistencia.input.Ctrl_Input_LZW;
import persistencia.input.Ctrl_Input_Text;
import persistencia.output.Ctrl_Output;
import domini.utils.Dict_Decode;
import domini.utils.Dict_Encode;

/**
 * @class LZW
 * @brief Compressió i descompressió pel mètode LZW
 * 
 * El mètode LZW crea sobre la marxa, de manera automàtica i
 * en una única passada un diccionari de cadenes que es trobin dins del text a comprimir mentre al
 * mateix temps es procedeix a la seva codificació. Aquest diccionari no és transmès amb el text 
 * comprimit, ja que el descompressor pot reconstruir-lo usant la mateixa lògica amb què el fa el
 * compressor i, si està codificat correctament, tindrà exactament les mateixes cadenes que el
 * diccionari del compressor tenia.
 * @author Miguel Paracuellos Ocaña 
 */
public class LZW extends Algorithm {

	//Attributes
	/**
	 * @param Limit Màxim nombre d'entrades del diccionari
	 */
	final Integer Limit = Integer.MAX_VALUE;

	


	//Constructor
	/**
	 * @brief Constructor de la classe
	 * @param path Path de sortida
	 * @param b False si estas comprimint, True si estas descomprimint
	 */
	public LZW(String path, boolean b) {
		super(path, b);
		if (!b) {
			Output.addMetadata("lzw");
		}
	}

	/**
	 * @brief Constructor de la classe
	 * @param b False si estas comprimint, True si estas descomprimint
	 * @note Es continua escrivint al fitxer que s'estava escrivint
	 */
	public LZW(boolean b) {
		super(b);
		if (!b) {
			Output.addMetadata("lzw");
		}
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
				throw new IllegalArgumentException("El text no està ben comprimit");
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
