package com.yelp.highlight;

/**
 * Global Properties are the global paramters for this code. Normally, they'd
 * be in a properties file or a BD, but I don't know the execution environment.
 * 
 * @author kpickering
 * @version 1.0
 */
public class GlobalProperties {
	// Max length a return can be. Set to 300 characters by default.
	public static int MAX_RETURN_SIZE=300;
	// Highlight Start Tag
	public static String HIGHLIGHT_START="[[HIGHLIGHT]]";
	// Highlight End Tag
	public static String HIGHLIGHT_END="[[ENDHIGHLIGHT]]";
	// String characted to apply if a snippet is too long and needs to be trimmed.
	public static String OVERRUN_REP="...";
	// Delimiters to break up sentences... used most common English carries,
	// although I might exclude semi-colon depending on performance.
	public static String SENTENCE_DELIMITERS="[\\.?!;]";
	// Mark the end of a snippet with this.
	public static String END_SNIPPET="...";
	
}
