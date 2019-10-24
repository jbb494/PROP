package com.company.algorithm;
import java.util.*;

import com.company.output.Ctrl_Output;
import com.company.utils.LZW_Dict_Decode;
import com.company.utils.LZW_Dict_Encode;

public class LZW implements Algorithm {

	//Attributes
	final Integer Limit = Integer.MAX_VALUE;

	Ctrl_Output Output;


	//Constructor
	public LZW() {
		Output = new Ctrl_Output("LZW.out");
	}


	//Functions
	public ArrayList<Integer> compression(String file) {
		ArrayList<Integer> result = new ArrayList<>();

		//We initialize the attributes we need
		LZW_Dict_Encode table = new LZW_Dict_Encode();
		Integer i = Limit;
		
		for (char c : file.toCharArray()) {
			Integer aux = i;

			if ( (i = table.search_and_insert_BST(aux, c)) == Limit) {
				result.add(aux);
				i = table.Ascii_value(c);
			}
		}

		if (i != Limit)
			result.add(i);			

		return result;
	}


	public String decompression(ArrayList<Integer> file) {
		String result = "";
		
		LZW_Dict_Decode table = new LZW_Dict_Decode();
		Integer i = Limit;
		
		for (Integer x : file) {
			Integer sz = table.getSize();

			//Dictionary's maximum size -> Reset it
			if (sz == Limit) 
				table.reset_dictionary();

			/*	
			if (x > sz)
				Throw some Exception
			*/

			String s;

			if (x == sz) {
				table.add(i, table.getWord(i).charAt(0));
				s = table.getWord(x);
			}

			else {
				s = table.getWord(x);

				if (i != Limit) {
					table.add(i, s.charAt(0));
				}
			}

			result += s;
			i = x;
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
