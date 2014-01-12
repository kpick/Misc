package com.yelp.highlight.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yelp.highlight.Document;
import com.yelp.highlight.Query;
import com.yelp.highlight.Snippet;
import com.yelp.highlight.DocumentMatcher;


public class TestDocumentMatcher {
	
	@Test
	public final void testScore() {

		//EXACT
		Document d = new Document("I love deep dish pizza");
		Query q = new Query("deep dish pizza");
		for (Snippet s : d.getSnippets()) {
			DocumentMatcher dm = new DocumentMatcher (s, q);
			dm.score();
			assertEquals(s.getScore(), 7);
			assertEquals(s.getMatchedWords().size(), 3);
		}
		
		//Match All Ordered
		d = new Document("I love deep dish music pizza");
		q = new Query("deep dish pizza");
		for (Snippet s : d.getSnippets()) {
			DocumentMatcher dm = new DocumentMatcher (s, q);
			dm.score();
			assertEquals(s.getScore(), 6);
			assertEquals(s.getMatchedWords().size(), 3);
		}

		//Match All Unordered
		d = new Document("I love dish deep calzone pizza.");
		q = new Query("deep dish pizza");
		for (Snippet s : d.getSnippets()) {
			DocumentMatcher dm = new DocumentMatcher (s, q);
			dm.score();
			assertEquals(s.getScore(), 5);
			assertEquals(s.getMatchedWords().size(), 3);
		}

		//Match 2 Ordered
		d = new Document("I love deep dish music");
		q = new Query("deep dish pizza");
		for (Snippet s : d.getSnippets()) {
			DocumentMatcher dm = new DocumentMatcher (s, q);
			dm.score();
			assertEquals(s.getScore(), 4);
			assertEquals(s.getMatchedWords().size(), 2);
		}

		//Match 2 Unordered
		d = new Document("I love pizza dish.");
		q = new Query("deep dish pizza");
		for (Snippet s : d.getSnippets()) {
			DocumentMatcher dm = new DocumentMatcher (s, q);
			dm.score();
			assertEquals(s.getScore(), 3);
			assertEquals(s.getMatchedWords().size(), 2);
		}
		
		// Match 1 Ordered/Unordered
		d = new Document("I love deep bowl pasta");
		q = new Query("deep dish pizza");
		for (Snippet s : d.getSnippets()) {
			DocumentMatcher dm = new DocumentMatcher (s, q);
			dm.score();
			assertEquals(s.getScore(), 2);
			assertEquals(s.getMatchedWords().size(), 1);
		}
		
		// Match 0
		d = new Document("I love really big bowls of cereal");
		q = new Query("deep dish pizza");
		for (Snippet s : d.getSnippets()) {
			DocumentMatcher dm = new DocumentMatcher (s, q);
			dm.score();
			assertEquals(s.getScore(), 0);
			assertEquals(s.getMatchedWords().size(), 0);
		}
	}
	
}
