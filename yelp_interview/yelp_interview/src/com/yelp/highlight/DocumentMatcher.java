package com.yelp.highlight;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function to match queries in snippets. The order of the algorithm is:
 * 		1) Exact Match
 * 	Recursive:
 * 		2) N words matched in order
 *      3) N words matched in a snippet 
 * @author kpickering
 * @version 1.0
 */
public class DocumentMatcher {
	private Snippet snippet;
	private Query query;
	private int querySize;
	
	/**
	 * Make the document matcher with the text to be parsed and the query
	 * 
	 * @param s The snippet to be used
	 * @param q The query with the snippet
	 */
	public DocumentMatcher (Snippet s, Query q) {
		snippet = s;
		query = q;
		querySize = query.getWords().length;
	}
	
	/**
	 * Matches based on several a cascading order of 'exact' and
	 * gets progressively less restrictive.
	 * 
	 * @returns the score for this Snippet
	 */
	public int score() {
		int score = exact();
		if (score == 0) {
			for (int i = querySize; i >= 1; --i) {
				score = wordsOrdered(i);
				if (score != 0) {
					break;
				} 
				score = wordsUnordered(i);
				if (score != 0) {
					break;
				}
			}
		}
		snippet.setScore(score);
		return score;
	}

	/**
	 * Exact match for a string. RegEx is easiest/quickest
	 * 
	 * @return the score for Regex (max query size + 1), the highest rating.
	 */
	private int exact() {
		Pattern regEx = HighlighterUtility.craftExactPatternRegEx(query.getInputString());
		Matcher matcher = regEx.matcher(snippet.getText());
		if (matcher.matches()) {
			for (String word : query.getWords()) {
				snippet.addMatchedWords(word);
			}
			return querySize*2 + 1;
		}
		return 0;
	}
	
	/**
	 * Looks for n words, all ordered.
	 * 
	 * @param n The number of words we're seeking to match
	 * @return The Score is number of words sought * 2
	 */
	private int wordsOrdered(int n) {
		int count = 0;
		int miscount = 0;
		int [] wordIndex = new int [querySize];
		ArrayList <String> matchedQuery = new ArrayList<String> ();
		
		for (String s : query.getWords()) {
			int index = snippet.getText().toLowerCase().indexOf(s.toLowerCase());
			if (index >= 0) {
				wordIndex[count] = index;
				++count;
				matchedQuery.add(s);
			} else {
				++ miscount;
				if (miscount >= n) {
					return 0;
				}
			}
			if (count >= 2 && wordIndex[count-2] > wordIndex[count-1]) {
				return 0;	
			}
			if (count == n) {
				snippet.setMatchedWords(matchedQuery);
				return n*2;
			}
		}
		return 0;
	}

	/**
	 * Looks for the same as above, but doesn't bother with order.
	 * 
	 * @param n The number of words we're seeking to hit on.
	 * @return The Score is number of words sought * 2 -1 
	 */
	private int wordsUnordered(int n) {
		int count = 0;
		ArrayList <String> matchedQuery = new ArrayList<String> ();

		for (String s : query.getWords()) {
			if (snippet.getText().toLowerCase().indexOf(s.toLowerCase()) >= 0) {
				count++;
				matchedQuery.add(s);
			}
		}
		if (count >= n) {
			snippet.setMatchedWords(matchedQuery);
			return n*2 - 1;
		} else {
			return 0;
		}
	}
}
