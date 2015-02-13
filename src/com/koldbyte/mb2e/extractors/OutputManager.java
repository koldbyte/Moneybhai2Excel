package com.koldbyte.mb2e.extractors;

import java.util.ArrayList;

public class OutputManager {
	public String createOutput(ArrayList<TransactionEntry> entries){
		String output = "";
		
		for(TransactionEntry e : entries){
			if(!e.getCompany().equals("Company")){
			output += e;
			output += '\n';
			}
		}
		
		return output;
	}
}
