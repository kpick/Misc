package com.yelp.highlight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A document should consist of a series of sentences. For this, I'm breaking
 * it up into a series of snippets. Also included a Snippet Comparator to 
 * weight selected options
 * 
 * @author kpickering
 * @version 1.0
 */
public class Document {
	private List<Snippet> snippets;
	private Snippet topSnippet;
	public SnippetComparator sc;
	
	/**
	 * @param input String to be input into the Document
	 */
	public Document(String input) {
		String sentenceDelimiters = GlobalProperties.SENTENCE_DELIMITERS;
		String appendToSnippet = GlobalProperties.END_SNIPPET;
		
		topSnippet = new Snippet();
				
		snippets = new ArrayList<Snippet> ();
		String [] splitText = input.split(sentenceDelimiters);
		for (String s : splitText) {
			snippets.add(new Snippet(s + appendToSnippet));
		}
		
		sc = new SnippetComparator();
	}
	
	/**
	 * The primary function of this class, it takes a query and
	 * analyzes a snippet for a match rating for that query.
	 * 
	 * @param query The Query object to be used
	 * @return The best match for the Query
	 */
	public String executeQuery(Query query) {
		int querySize = query.getWords().length;
		
		// Loop through Snippets
		for (Snippet s : getSnippets() ) {
			DocumentMatcher dm = new DocumentMatcher(s, query);
			int score = dm.score();
			//Exact Match
			if (score == querySize*2 + 1) {
				//replaceAll of String is faster and looks better in this case.
				String queryString = query.getInputString();
				// Make the the test isn't too long.
				String text = HighlighterUtility.trimToMaxSize(s.getText().trim(), 
						queryString,  GlobalProperties.MAX_RETURN_SIZE);
				// Do the replace, ignore case
				s.setHighlightedText(
						text.replaceAll("(?i)" + queryString, GlobalProperties.HIGHLIGHT_START +
						queryString + GlobalProperties.HIGHLIGHT_END));
				setTopSnippet(s);
				// I determine the first exact match to be the most relevant result
				// so there's no need to continue the loop.
				break;
			} else if (score > 0 ) {
				// This is partial/non-exact matching, so each word needs 
				// to be highlighted separately.
				
				// First see if top result
				if (sc.compare(s, topSnippet) > 0) {
					topSnippet = s;
				}
				boolean firstResult = true;
				for (String match : s.getMatchedWords()) {
					// This was added to trim the string appropriately, but only once
					if (firstResult) {
						String result = HighlighterUtility.trimToMaxSize(s.getText(),
								match, GlobalProperties.MAX_RETURN_SIZE);
						s.setHighlightedText(result);
						firstResult = false;
					}
					// Highlight each word.
					s.setHighlightedText(
						s.getHighlightedText().replaceAll("(?i)" + match, GlobalProperties.HIGHLIGHT_START +
						match + GlobalProperties.HIGHLIGHT_END).trim()
					);
				}
			}
		}
		if (topSnippet.getHighlightedText() == null || "".endsWith(topSnippet.getHighlightedText())) {
			return "NO RESULTS";
		} else {
			return topSnippet.getHighlightedText();
		}
	}
	
	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}

	public List<Snippet> getSnippets() {
		return snippets;
	}

	public void setTopSnippet(Snippet topSnippet) {
		this.topSnippet = topSnippet;
	}

	public Snippet getTopSnippet() {
		return topSnippet;
	}
	
	public class SnippetComparator implements Comparator<Snippet> {
		@Override
		public int compare(Snippet arg0, Snippet arg1) {
			if (arg0.getScore() < arg1.getScore()) {
				return -1;
			} else if (arg0.getScore() == arg1.getScore()) {
				return 0;
			} else {
				return 1;
			}
			
		}
	}
}
