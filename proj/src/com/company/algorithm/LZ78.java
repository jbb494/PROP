package com.company.algorithm;

import java.util.*;

//import javax.*;

public class LZ78
{
    String Input;
    String Output;
    
    
	public LZ78(String a) 
	{
        Map<String, Integer> map = new HashMap<String, Integer>();
        
		Input = a;
		
		Output = "";
		
		Character nextChar;
		
		Integer i = 0;
		
		String seq = "";

		Integer punterMap = 0;
		
		while(i < a.length())
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
					
					String pAS = punterActual.toString();

					Output = Output.concat(pAS);

				}else{
					Output = Output.concat("0");
				}

				Output = Output.concat(nextCharS);

				map.put(novaEntrada, punterMap);

				seq = "";
				punterMap++;
			}
            i++;
		}
	}
	
	public String print()
	{
        return Output;
	}
	
}
