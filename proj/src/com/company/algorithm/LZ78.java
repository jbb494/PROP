package com.company.algorithm;

import java.util.*;


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
		
		String aux = "";

		Integer punterMap = 0;
		
		while(i < a.length())
		{
            nextChar = a.charAt(i);
			String nextCharS = nextChar.toString();

			String novaEntrada = aux.concat(nextCharS);
			
            if(map.containsKey(novaEntrada))
            {
				Output.concat(punterMap.toString());
				aux.concat(nextCharS);

            }else
            {
				Integer punterActual = map.get(aux);

                Output.concat(punterActual.toString()).concat(nextCharS);
				map.put(novaEntrada, punterMap);

				aux = "";
				punterMap++;
            }
            aux = novaEntrada;
            i++;
		}
	}
	
	public String print()
	{
        return Output;
	}
	
}
