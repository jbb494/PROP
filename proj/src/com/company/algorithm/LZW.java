package com.company.algorithm;
import java.util.*;

import com.company.output.Ctrl_Output;

public class LZW {

	//Attributes
	Ctrl_Output Output;

	public LZW() {
		Output = new Ctrl_Output("LZW.out");
	}
	
	public ArrayList<Integer> compression(String file) {
		Map<String,Integer> table = new HashMap<String, Integer>();
		
		//We initialize the map with the ASCII table (String as key)
		for (int i = 0; i < 256; ++i) {
			table.put("" + (char)i, i);
		}
		
		//We prepare the variables before the main loop
		Integer pos = 256;
		String old = "";
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (char new_c : file.toCharArray()) {
			String aux = old + new_c;
			
			if (table.containsKey(aux)) {
				old = aux;
			}
			else {
				//We add the next value to the compressed_file
				result.add(table.get(old));

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


	public String decompression(ArrayList<Integer> file) {
		Map<Integer, String> table = new HashMap<Integer, String>();

		//We initialize the map with the ASCII table (Integer as key)
		for (int i = 0; i < 256; ++i) 
			table.put(i, "" + (char)i);

		
		//We prepare the variables before the main loop
		Integer old = file.get(0);
		Integer pos = 256;
		String s = table.get(old);
		String c = "" + s.charAt(0);
		String result = s;
	

		for (Integer i = 1; i < file.size(); ++i) {
			Integer aux = file.get(i);
			if (!table.containsKey(aux)) {
				s = table.get(old) + c;
			}
			else {
				s = table.get(aux);
			}
			result += s;

			c = "" + s.charAt(0);
			table.put(pos++, table.get(old) + c);
			old = aux;			
		}

		return result;
	}


	public Ctrl_Output print(String file) {
		ArrayList<Integer> arr = compression(file);
		
		for (int i = 0; i < arr.size(); ++i) {
			Output.add(arr.get(i));
		}

		return Output;
	}
	
}
