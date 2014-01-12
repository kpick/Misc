package com.yelp.highlight;

/**
 * Class to represent the input user Query. Stores raw string
 * and split string for convenience.
 * 
 * @author kpickering
 * @version 1.0
 */
public class Query {
	private String inputString;
	private String [] words;
	
	public Query(String input){
		setInputString(input);
		setWords(input.split("\\s"));
	}
	
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public String getInputString() {
		return inputString;
	}

	public void setWords(String [] words) {
		this.words = words;
	}

	public String [] getWords() {
		return words;
	}
}
