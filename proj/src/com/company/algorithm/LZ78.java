package com.company.algorithm;
import com.company.input.*;
import com.company.output.Ctrl_Output;

import java.util.*;


public class LZ78 implements Algorithm {
    Ctrl_Output Output;
   
	public LZ78(Boolean x){
		String path;
		if(x) path = "../DescompresioLZ78.out";
		else path = "../CompresioLZ78.out";
		
		Output = new Ctrl_Output(path);
	}
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
	public void Decompressor(Ctrl_Input_LZ78 in) 
	{
		Map<Integer, String> map = new HashMap<Integer, String>();
		Integer punterActual = 0;
			while(!in.finished()) {
				//System.out.println("Int: " + entr.getKey() + "Char: " + entr.getValue());
				AbstractMap.SimpleEntry <Integer, Character> entr = in.get();
				if(in.finished()) return;
				Integer punterMap = entr.getKey();
				Character nextChar = entr.getValue();
				if(punterMap == 0)
				{
					Output.add(nextChar);
					map.put(punterActual++, nextChar.toString());
				}else{
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
