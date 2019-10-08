package com.company.algorithm;

import java.util.*;


public class LZ78
{
    String Input;
    String Output;
    
    
	public LZ78(String a) 
	{
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
		Input = a;
		
		Output = "";
		
		char nextChar;
		
		int i = 0;
		
		String aux = "";
		
		while(i < a.length())
		{
            nextChar = a.charAt(i);
            
            aux = aux.concat(Character.toString(nextChar));
            
            if(map.containsKey(nextChar))
            {
                
            }else
            {
                map.get(aux);
                
            }
            
            i++;
		}
	}
	
	public String print()
	{
        return Output;
	}
	
}
