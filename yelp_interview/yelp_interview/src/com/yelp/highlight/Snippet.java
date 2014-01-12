package com.yelp.highlight;

import java.util.ArrayList;
import java.util.List;

/**
 * Snippet is the broken out class for the Document sentences. They
 * have a weight and I added the matched words found in the matching
 * process for convenience when applying the highlight tags.
 * 
 * @author kpickering
 * @version 1.0
 */
public class Snippet {
	private int score;
	private String text;
	private String highlightedText;
	private List <String> matchedWords;
	
	/**
	 * Basic constructor... Only really used to build base Top Snippet now.
	 */
	public Snippet() {
		setText("");
		setScore(0);
		matchedWords = new ArrayList<String>();
	}
	
	/**
	 * Preferred constructor, takes a string and sets the Score to zero
	 * 
	 * @param input
	 */
	public Snippet(String input) {
		setText(input);
		setScore(0);
		matchedWords = new ArrayList<String>();
	}
	
	public void setScore(int score){
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setHighlightedText(String highlightedText) {
		this.highlightedText = highlightedText;
	}

	public String getHighlightedText() {
		return highlightedText;
	}

	public void addMatchedWords(String word) {
		matchedWords.add(word);
	}

	public void setMatchedWords(List <String> matchedWords) {
		this.matchedWords = matchedWords;
	}
	
	public List <String> getMatchedWords() {
		return matchedWords;
	}
}
