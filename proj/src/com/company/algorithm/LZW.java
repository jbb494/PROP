package com.company.algorithm;
import java.util.*;

public class LZW {
	
	public static ArrayList<Integer> compression(String file) {
		Map<String,Integer> table = new HashMap<String, Integer>();
		
		//We initialize the map with the ASCII table (String as key)
		for (int i = 0; i < 256; ++i) {
			table.put("" + (char)i, i);
		}
		
		//We prepare the variables before the main loop
		Integer pos = 255;
		String old = "";
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (char new_c : file.toCharArray()) {
			String aux = old + new_c;
			
			if (table.containsKey(aux)) {
				old = aux;
			}
			else {
				//We add the next value to the compressed_file
				result.add(pos);

				//We add the word to the table
				table.put(aux, pos++);

				//We update the old value
				old = "" + new_c;
			}
		}
		
		if (!old.equals("")) {
			result.add(table.get(old));
		}

		return result;
	}


	public static String decompression(ArrayList<Integer> file) {
		Map<Integer, String> table = new HashMap<Integer, String>();

		//We initialize the table with the ASCII table (Integer as key)
		for (int i = 0; i < 256; ++i) 
			table.put(i, "" + (char)i);

		
		//We prepare the variables before the main loop
		Integer old = file.get(0);
		Integer pos = 256;
		String s = table.get(old);
		String c = "" + s.charAt(0);
		String result = "";
	

		for (Integer new_num = 1; new_num < file.size(); ++new_num) {
			if (!table.containsKey(new_num)) {
				s = table.get(old) + c;
			}
			else {
				s = table.get(new_num);
			}
			result += s;

			c = "" + s.charAt(0);
			table.put(pos++, table.get(old) + c);
			old = new_num;
			
		}

		return result;
	}


	
}
