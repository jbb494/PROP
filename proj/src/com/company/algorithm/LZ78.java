package com.company.algorithm;

import com.company.output.Ctrl_Output;

import java.util.*;


public class LZ78 implements Algorithm {
    String Input;
    Ctrl_Output Output;
    ArrayList< AbstractMap.SimpleEntry <Integer, Character> > InpDesc;	
	public LZ78(String a) 
	{
		Input = a;
		
		Output = new Ctrl_Output("../CompresioLZ78.out");

	}
	public LZ78(ArrayList< AbstractMap.SimpleEntry <Integer, Character> > a) 
	{
		InpDesc = a;
		
		Output = new Ctrl_Output("../DescompresioLZ78.out");

	}

	public void Compressor()
	{
        Map<String, Integer> map = new HashMap<String, Integer>();
		
		Character nextChar;
		
		Integer i = 0;
		
		String seq = "";

		Integer punterMap = 0;
		
		while(i < Input.length())
		{
			nextChar = Input.charAt(i);
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
            i++;
		}
	}
	public void Decompressor() 
	{
		Map<Integer, String> map = new HashMap<Integer, String>();
		Integer punterActual = 0;
		for (AbstractMap.SimpleEntry <Integer, Character> entr : InpDesc)
		{
			//System.out.println("Int: " + entr.getKey() + "Char: " + entr.getValue());
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
