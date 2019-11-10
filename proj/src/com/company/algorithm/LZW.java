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
	public LZW(boolean b) {
		if (b)
			Output = new Ctrl_Output("../CompresioLZW.out");

		else 
			Output = new Ctrl_Output("../DescompresioLZW.out");
	}


	//Functions
	public ArrayList<Integer> compression(String file) {
		ArrayList<Integer> result = new ArrayList<>();

		//We initialize the attributes we need
		LZW_Dict_Encode table = new LZW_Dict_Encode();
		Integer i = -1;
		
		for (char c : file.toCharArray()) {
			Integer aux = i;

			if ( (i = table.search_and_insert_BST(aux, c)) == -1) {
				result.add(aux);
				i = table.Ascii_value(c);
			}
		}

		if (i != -1)
			result.add(i);			

		return result;
	}


	public String decompression(ArrayList<Integer> file) {
		String result = "";
		
		LZW_Dict_Decode table = new LZW_Dict_Decode();
		Integer i = -1;
		
		for (Integer k : file) {
			Integer sz = table.getSize();
			String s = "";

			//Dictionary's maximum size -> Reset it
			if (sz == Limit) { 
				table.reset_dictionary();
			}				
			else if (k > sz) {
				System.out.println("Liada");
			}		
			else if (k < sz) {
				s = table.getWord(k);

				if (i != -1) {
					table.add(i, s.charAt(0));
				}
			}
			else {
				table.add(i, table.getWord(i).charAt(0));
				s = table.getWord(k);
			}

			result += s;
			i = k;
		}
		return result;
	}

	public Ctrl_Output print_encode(String file) {
		ArrayList<Integer> arr = compression(file);
		
		for (int i = 0; i < arr.size(); ++i) {
			Output.add(arr.get(i));
		}

		return Output;
	}

	public Ctrl_Output print_decode(ArrayList<Integer> file) {
		String arr = decompression(file);
		
		Output.add(arr);

		return Output;
	}
	
}
