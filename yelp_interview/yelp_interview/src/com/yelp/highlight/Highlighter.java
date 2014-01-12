package com.yelp.highlight;

/**
 * @author kpickering
 * @version 1.0
 *
 * This is the main class for highlighting a particular phrase. Per requirement,
 * made method with the appropriate signature, and a Main to run it from the 
 * command line
 */
public class Highlighter {
	
	/**
	 * This is the main function for the application. It will parse a string with
	 * the appropriate query and return the highlighted document.
	 * 
	 * @param document: Text to be parsed with the query
	 * @param query: The appropriate query string
	 * @return
	 */
	public static String highlight_doc(String document, String query ) {
		if (document == null || query == null) {
			throw new RuntimeException ("Document/Query can't be null.");
		}
		
		document = document.trim();
		query = query.trim();
		
		if ("".equals(document) || "".equals(query)) {
			throw new RuntimeException ("Document/Query must both be supplied non-empty values.");
		}
		if (document.length() < query.length()) {
			throw new RuntimeException ("No values will be found. Document is smaller than the query text.");
		}
		
		Document doc = new Document (document);
		Query q = new Query (query);
		return doc.executeQuery(q);
	}
	
	/**
	 * EXECUTION FROM CLI: Main function to call Yelp Highlighter
	 * @param args 
	 * 		Arg1: The full text of the review.
	 * 		Arg2: The search criteria (escaped or unescaped
	 */
	public static void main(String [] args) {
		if (args == null || args.length < 2) {
			throw new RuntimeException ("Need to supply two arguements in order to use this application.");
		}

		String input = args[0];
		String query = args[1];
		System.out.println("Input string:\t" + input);
		System.out.println("Query string:\t" + query);
		System.out.println("Best Match:\t" + highlight_doc(input, query));
	}
}
