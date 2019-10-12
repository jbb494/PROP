package com.company.algorithm;

import com.company.output.Ctrl_Output;

import java.util.*;


public class LZ78
{
    String Input;
    Ctrl_Output Output;
    
    
	public LZ78(String a) 
	{
        Map<String, Integer> map = new HashMap<String, Integer>();
        
		Input = a;
		
		Output = new Ctrl_Output("LZ78.out");
		
		Character nextChar;
		
		Integer i = 0;
		
		String seq = "";

		Integer punterMap = 0;
		
		while(i < a.length() -1)
		{
			nextChar = a.charAt(i);
			String nextCharS;
			if(nextChar == ' ')nextCharS = "\\s";
			else nextCharS = nextChar.toString();

			String novaEntrada = seq.concat(nextCharS);
			
            if(map.containsKey(novaEntrada))
            {
				seq = seq.concat(nextCharS);

            }else
            {
				if(seq.length() >= 1){
					Integer punterActual = map.get(seq) +1;
					
					Output.add(punterActual);

				}else{
					Output.add(0);
				}

				Output.add(nextChar);

				map.put(novaEntrada, punterMap);

				seq = "";
				punterMap++;
			}
            i++;
		}
	}
	
	public Ctrl_Output print()
	{
        return Output;
	}
	
}
