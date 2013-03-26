package com.raj.eliza.service;

import java.util.Comparator;

import com.raj.util.fuzzy.StringDistance;

public class StringDistanceComparator implements Comparator {

	private String originalStringInput;

	public StringDistanceComparator(String originalStringInput) {
		this.originalStringInput = originalStringInput;
	}

	@Override
	public int compare(Object o1, Object o2) {
		String startingWord = originalStringInput.split(" ")[0];
		int r = 0;
		String o1lowerCase = o1.toString().toLowerCase();
		String o2lowerCase = o2.toString().toLowerCase();
		double rank1 = StringDistance.compareStrings(originalStringInput, o1lowerCase)
				+ (o1lowerCase.startsWith(startingWord) ? 1 : 0);
		double rank2 = StringDistance.compareStrings(originalStringInput, o2lowerCase)
				+ (o2lowerCase.startsWith(startingWord) ? 1 : 0);
		if (rank1 > rank2) {
			r = 1;
		} else if (rank1 < rank2) {
			r = -1;
		} else {
			r = 0;
		}
//		System.out.println(r + ">>" + rank1 + "," + rank2 + ")==(" + o1.toString() + "," + o2.toString() + ")");

		return r;
	}

}
