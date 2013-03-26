package com.raj.eliza.model;

public class Transformer {
	
	public static String tranform(String text, String oldSubString, String newSubString) {
		String retVal;
		
		int index = text.indexOf(oldSubString);
		
		if (index == -1) {
			retVal = text;
		}
		else {
			retVal = tranform(text.substring(0, index) + newSubString + text.substring(index + oldSubString.length()),
					oldSubString, newSubString);
		}
		return retVal;
	}

}
