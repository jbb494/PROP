package domini.algorithm;
import persistencia.input.*;
import persistencia.output.Ctrl_Output;
import domini.utils.*;

import java.util.*;

/**
 * @class LZ78
 * @brief Compressió i descompressió pel mètode LZ78
 * 
 * Aquest algoritme de compresió es basa en utilitzar una estructura de dades (Trie en el meu cas), per guardar
 * seqüencies de bytes i fer referència a aquestes seqüències amb punters al Trie.
 * 
 * @author Joan Bellavista Bartroli
 */
public class LZ78 {

    /**
	 *  @param Utilitzat per a la compressió i descompressió de fitxers..
	*/
    Ctrl_Output Output ;
   
	//Constructor
	/**
	 * @brief Constructor de la clase LZ78
	 * @param aux Path de sortida
	 * @param b False si estas comprimint, True si estas descomprimint
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
				if(in.finished()){
					Integer punterActual = map.indexNode(seq) +1;
					Output.add((Integer)punterActual, 32);
					Output.add(nextByte, 8);	
					return;
				}
				seq.add(nextByte);
            }else
            {									
				if(seq.size() >= 1){
					Integer punterActual = map.indexNode(seq) +1;
					
					Output.add((Integer)punterActual, 32);

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
			if(in.finished()) return;
			Integer punterMap = entr.getLeft();
			Byte nextByte = entr.getRight();

			ArrayList<Byte> seqPunterMap = map.getWord(punterMap);
			seqPunterMap.add(nextByte);
			map.add(punterMap, nextByte);
			Output.add(seqPunterMap);
		}
	}
	/**
	 * @fn public print()
	 * @brief Retorna el Ctrl_Output.
	 * @return Retorna el Ctrl_Output on està l'arxiu comprimit o descomprimit.
	 */
	public Ctrl_Output print() {
        return Output;
	}
	
	
}

