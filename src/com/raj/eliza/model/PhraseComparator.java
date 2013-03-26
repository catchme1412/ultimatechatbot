package com.raj.eliza.model;

import java.util.Comparator;

import com.raj.util.fuzzy.StringDistance;

public class PhraseComparator implements Comparator<Phrase> {

	private String userInput;

	public PhraseComparator(String userInput) {
		this.userInput = userInput.toLowerCase();
	}

	@Override
	public int compare(Phrase o1, Phrase o2) {

		String o1lowerCase = o1.getPhraseText().toLowerCase();
		String o2lowerCase = o2.getPhraseText().toLowerCase();
		double rank1 = StringDistance.compareStrings(userInput, o1lowerCase);
		double rank2 = StringDistance.compareStrings(userInput, o2lowerCase);
		int startValue1 = userInput.startsWith(o1lowerCase) ? 1 : 0;
		int startValue2 = userInput.startsWith(o2lowerCase) ? 1 : 0;
		double finalRank1 = rank1 + startValue1;
		double finalRank2 = rank2 + startValue2;
		if (finalRank1 < finalRank2) {
			return 1;
		} else if (finalRank1 > finalRank2) {
			return -1;
		}
		return 0;
		// if (userInput.startsWith(o1lowerCase)) {
		// return -1;
		// } else if (userInput.startsWith(o2.getPhraseText().toLowerCase())) {
		// return 1;
		// }
		// if (o1.getPriority() < o2.getPriority()) {
		// return -1;
		// } else if (o1.getPriority() > o2.getPriority()) {
		// return 1;
		// } else {
		// return 0;
		// }
	}

}
