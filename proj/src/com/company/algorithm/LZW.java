package com.company.algorithm;
import java.util.*;

public class LZW {
	
	public static void compresser(String file) {
		Map<String,Integer> table = new HashMap<String, Integer>();
		
		for (int i = 0; i < 256; ++i) {
			table.put("" + (char)i, i);
		}
		
		Integer pos = 255;
		String old = "";
		for (char new_c : file.toCharArray()) {
			String aux = old + new_c;
			
			if (table.containsKey(aux)) {
				old = aux;
			}
			else {
				table.put(aux, ++pos);
				old = "" + new_c;
			}

		}

			
			
		
		
	}


	
}
