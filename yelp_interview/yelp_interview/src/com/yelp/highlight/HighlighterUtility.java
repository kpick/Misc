package com.yelp.highlight;

import java.util.regex.Pattern;

/**
 * Utility class to support the Highlighter classes.
 * 
 * @author kpickering
 * @version 1.0
 */
public class HighlighterUtility {
	
	/**
	 * Crafts a RegEx for this precise string.
	 * 
	 * @param input
	 * @return
	 */
	public static Pattern craftExactPatternRegEx(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append(".*");
		sb.append(input);
		sb.append(".*");
		return Pattern.compile(sb.toString(), Pattern.CASE_INSENSITIVE);
	}
	
	/**
	 * Trim String to a certain size, centered around the first instance of a key.
	 * It's either somewhat balanced, or has more information after the instance of
	 * the word. This is decision I made to hopefully show better details.
	 * 
	 * @param str The String to be trimmed
	 * @param key The key to center the trim on
	 * @param max The max size a string can be.
	 * @return The trimmed string.
	 */
	public static String trimToMaxSize(String str, String key, int max){
		if (str.length() <= max ) {
			return str;
		} else {
			int index = str.toLowerCase().indexOf(key.toLowerCase());
			int half = max/2;
			if (index >= 0) {
				int difference = index - half;
				//Remove the top of the string...
				if (difference >= 0) {
					// ... but make sure we don't blow past the end.
					if ((difference + max -1) > str.length()) {
						return GlobalProperties.OVERRUN_REP +
							str.substring(difference, str.length()) +
							GlobalProperties.OVERRUN_REP;
					// Otherwise, go to clipped length at the end. The reason I weight
					// the ending more is because people tend to mention the object
					// and then provide a description.
					} else {
						return GlobalProperties.OVERRUN_REP +
							str.substring(difference, difference + max) +
							GlobalProperties.OVERRUN_REP;
					}
				} else {
					// If you don't offset past the beginning, go from the beginning until the 
					// max length.
					return str.substring(0, max) + GlobalProperties.OVERRUN_REP;
				}
			} else {
				// If you don't find the matched word, just return the clipped string.
				return str.substring(0, max) + GlobalProperties.OVERRUN_REP;
			}
		}
	}
}
