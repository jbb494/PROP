/**
 * @class Algoritme LZ78
 * @brief Compressió i descompressió pel mètode LZ78
 * @author Joan Bellavista Bartroli
 */

package domini.algorithm;
import persistencia.input.*;
import persistencia.output.Ctrl_Output;
import domini.utils.*;

import java.util.*;


public class LZ78 {
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
	public void Compressor(Ctrl_Input_Text in) {
        Trie<Byte> map = new Trie<Byte>();
		
		Byte nextByte;
				
		ArrayList<Byte> seq = new ArrayList<Byte>();

		Integer punterMap = 0;
		
		while(!in.finished()) {
			nextByte = in.get();

			ArrayList<Byte> novaEntrada = new ArrayList<Byte> ();
			novaEntrada.addAll(seq);
			novaEntrada.add(nextByte);
			
            if(map.indexNode(novaEntrada) != -1) {
				seq.add(nextByte);
            }else
            {
									
				//Integer midaPunter = (int)Math.ceil((Math.log((double)punterMap+1)/Math.log(2)));

				//System.out.println("punterMap: " + punterMap + " midaPunter: " + midaPunter);
				
				if(seq.size() >= 1){
					Integer punterActual = map.indexNode(seq) +1;

					Output.add(punterActual, 32);

				}else{
					Output.add((Integer)0, 32);
				}

				Output.add(nextByte, 8);

				map.insert(novaEntrada, punterMap);

				seq = new ArrayList<Byte>();
				punterMap++;
			}
		}
	}

	/**
	 * @fn public Decompressor(Ctrl_Input_LZW inp)
	 * @brief Descomprimim un fitxer amb l'algoritme LZ78
	 * @param inp accés al Controlador d'Input pel fitxer comprimit
	 */
	public void Decompressor(Ctrl_Input_LZ78 in) {
		Dict_Decode map = new Dict_Decode(false, 0);
		while(!in.finished()) {
			Pair <Integer, Byte> entr = in.get();
			//System.out.println((in.finished() ? "finsihed" : "not Finished")
			// + " Int: "+ entr.getLeft() + " Byte: " + entr.getRight());
			Integer punterMap = entr.getLeft();
			Byte nextByte = entr.getRight();

			ArrayList<Byte> seqPunterMap = map.getWord(punterMap);
			seqPunterMap.add(nextByte);
			map.add(punterMap, nextByte);
			//System.out.println("Size map: " + map.size() + " punterMap: " + punterMap + " seqPunterMap: " + seqPunterMap);
			for (byte baux : seqPunterMap){
				Output.add(baux,8);
			}
			// Output.add(seqPunterMap);				
		}
	}

	public Ctrl_Output print()
	{
        return Output;
	}
	
	
}
