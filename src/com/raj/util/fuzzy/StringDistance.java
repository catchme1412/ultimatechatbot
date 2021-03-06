package com.raj.util.fuzzy;

import java.util.ArrayList;
import java.util.List;

/**
 * The algorithm works by comparing the two strings like 'France' and 'French'.
 * First, we map them both to their upper case characters (making the algorithm
 * insensitive to case differences), then split them up into their character
 * pairs find out the the similarity.
 * 
 * FRANCE: {FR, RA, AN, NC, CE}
 * 
 * FRENCH: {FR, RE, EN, NC, CH}
 * 
 * @see http://www.catalysoft.com/articles/StrikeAMatch.html
 */

public class StringDistance {

	/** @return an array of adjacent letter pairs contained in the input string */

	private static String[] letterPairs(String str) {
		int numPairs = str.length() - 1;
		// Added by Rajesh
		numPairs = numPairs < 0 ? 0 : numPairs;
		// ////
		String[] pairs = new String[numPairs];
		for (int i = 0; i < numPairs; i++) {
			pairs[i] = str.substring(i, i + 2);
		}

		return pairs;

	}

	/** @return an ArrayList of 2-character Strings. */

	private static List wordLetterPairs(String str) {
		List allPairs = new ArrayList();
		// Tokenize the string and put the tokens/words into an array
		String[] words = str.split("\\s");
		// For each word
		for (int w = 0; w < words.length; w++) {
			// Find the pairs of characters
			String[] pairsInWord = letterPairs(words[w]);
			for (int p = 0; p < pairsInWord.length; p++) {
				allPairs.add(pairsInWord[p]);
			}
		}

		return allPairs;

	}

	/** @return lexical similarity value in the range [0,1] */

	public static double compareStrings(String str1, String str2) {
		List pairs1 = wordLetterPairs(str1.toUpperCase());
		List pairs2 = wordLetterPairs(str2.toUpperCase());

		int intersection = 0;

		int union = pairs1.size() + pairs2.size();

		for (int i = 0; i < pairs1.size(); i++) {
			Object pair1 = pairs1.get(i);
			for (int j = 0; j < pairs2.size(); j++) {
				Object pair2 = pairs2.get(j);
				if (pair1.equals(pair2)) {
					intersection++;
					System.out.print(pair1);
					pairs2.remove(j);
					break;
				}
			}
		}

		return (2.0 * intersection) / union;

	}

	public static void main(String[] args) {
		System.out.println(compareStrings(" how are you today ".trim(), " YOU "));
	}
}
