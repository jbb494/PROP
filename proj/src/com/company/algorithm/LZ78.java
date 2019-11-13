/**
 * @class Algoritme LZ78
 * @brief Compressió i descompressió pel mètode LZ78
 * @author Joan Bellavista Bartroli
 */

package com.company.algorithm;
import com.company.input.*;
import com.company.output.Ctrl_Output;
import com.company.utils.*;

import java.util.*;


public class LZ78 implements Algorithm {
	/**
     * @param Output Utilitzat per a la compressió i descompressió de fitxers.
     */
    Ctrl_Output Output;
   
	//Constructor
	/**
	 * @brief Constructor de la clase LZW
	 * @param aux Path de sortida
	 */
	public LZ78(String aux, boolean b) {
		Output = new Ctrl_Output(aux, "lz78", b);
	}

	//Functions
	/**
	 * @fn public Compressor(Ctrl_Input_Text in)
	 * @brief Comprimim un text amb l'algoritme LZ78
	 * @param in accés al Controlador d'Input per el text
	 */
	public void Compressor(Ctrl_Input_Text in)
	{
        Map<String, Integer> map = new HashMap<String, Integer>();
		
		Character nextChar;
				
		String seq = "";

		Integer punterMap = 0;
		
		while(!in.finished()) {
			nextChar = in.get();
			String nextCharS;
			//if(nextChar == ' ')nextCharS = "\\s";
			//else nextCharS = nextChar.toString();
			nextCharS = nextChar.toString();

			String novaEntrada = seq.concat(nextCharS);
			
            if(map.containsKey(novaEntrada))
            {
				seq = seq.concat(nextCharS);

            }else
            {
									
				Integer midaPunter = (int)Math.ceil((Math.log((double)punterMap+1)/Math.log(2)));

				//System.out.println("punterMap: " + punterMap + " midaPunter: " + midaPunter);
				
				if(seq.length() >= 1){
					Integer punterActual = map.get(seq) +1;

					Output.add(punterActual, 32);

				}else{
					Output.add(0, 32);
				}

				Output.add(nextChar);

				map.put(novaEntrada, punterMap);

				seq = "";
				punterMap++;
			}
		}
	}

	/**
	 * @fn public Decompressor(Ctrl_Input_LZW inp)
	 * @brief Descomprimim un fitxer amb l'algoritme LZ78
	 * @param inp accés al Controlador d'Input pel fitxer comprimit
	 */
	public void Decompressor(Ctrl_Input_LZ78 in) 
	{
		Map<Integer, String> map = new HashMap<Integer, String>();
		Integer punterActual = 0;
		while(!in.finished()) {
			//System.out.println("Int: " + entr.getKey() + "Char: " + entr.getValue());
			Pair <Integer, Character> entr = in.get();
			if(in.finished()) return;
			Integer punterMap = entr.getLeft();
			Character nextChar = entr.getRight();
			if(punterMap == 0){
				Output.add(nextChar);
				map.put(punterActual++, nextChar.toString());
			}else {
				String seqPunterMap = map.get(punterMap-1);
				map.put(punterActual++, seqPunterMap.concat(nextChar.toString()));
				Output.add(seqPunterMap.concat(nextChar.toString()));		
			}
		}
	}

	public Ctrl_Output print()
	{
        return Output;
	}
	
	
}
