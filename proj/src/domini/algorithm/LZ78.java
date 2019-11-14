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
	public void Compressor(Ctrl_Input_Text in)
	{
        Map<ArrayList<Byte>, Integer> map = new HashMap<ArrayList<Byte>, Integer>();
		
		Byte nextByte;
				
		ArrayList<Byte> seq = new ArrayList<Byte>();

		Integer punterMap = 0;
		
		while(!in.finished()) {
			nextByte = in.get();

			ArrayList<Byte> novaEntrada = new ArrayList<Byte> ();
			novaEntrada.addAll(seq);
			novaEntrada.add(nextByte);
			
            if(map.containsKey(novaEntrada))
            {
				seq.add(nextByte);
            }else
            {
									
				//Integer midaPunter = (int)Math.ceil((Math.log((double)punterMap+1)/Math.log(2)));

				//System.out.println("punterMap: " + punterMap + " midaPunter: " + midaPunter);
				
				if(seq.size() >= 1){
					Integer punterActual = map.get(seq) +1;

					Output.add(punterActual, 32);

				}else{
					Output.add(0, 32);
				}

				Output.add(nextByte, 8);

				map.put(novaEntrada, punterMap);

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
		Map<Integer, ArrayList<Byte>> map = new HashMap<Integer, ArrayList<Byte>>();
		Integer punterActual = 0;
		while(!in.finished()) {
			Pair <Integer, Byte> entr = in.get();
			//System.out.println((in.finished() ? "finsihed" : "not Finished")
			// + " Int: "+ entr.getLeft() + " Byte: " + entr.getRight());
			if(in.finished()) return;
			Integer punterMap = entr.getLeft();
			Byte nextByte = entr.getRight();
			if(punterMap == 0){
				Output.add(nextByte,8);
				ArrayList<Byte> aux = new ArrayList<Byte>();
				aux.add(nextByte);
				map.put(punterActual++, aux);
			}else {
				try{
				ArrayList<Byte> seqPunterMap = map.get(punterMap-1);
				seqPunterMap.add(nextByte);
				map.put(punterActual++, seqPunterMap);
				//System.out.println("Size map: " + map.size() + " punterMap: " + punterMap + " seqPunterMap: " + seqPunterMap);
				for (byte baux : seqPunterMap){
					Output.add(baux,8);
				}
				// Output.add(seqPunterMap);
				}catch(Exception e){
					throw new IllegalArgumentException(e.getMessage());
				}
			}
		}
	}

	public Ctrl_Output print()
	{
        return Output;
	}
	
	
}
