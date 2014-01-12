package com.yelp.highlight.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yelp.highlight.Snippet;


public class TestSnippet {
	@Test
	public final void testGenericConstructor () {
		Snippet s = new Snippet();
		assertNull(s.getHighlightedText());
		assertEquals(s.getText(), "");
		assertEquals(s.getScore(), 0);
	}
	
	@Test
	public final void testMainConstructor () {
		String str = "I love deep dish pizza.";
		Snippet s = new Snippet(str);
		assertNull(s.getHighlightedText());
		assertEquals(s.getText(), str);
		assertEquals(s.getScore(), 0);
	}
	
	@Test
	public final void testAddMatch(){
		String str = "I love deep dish pizza.";
		Snippet s = new Snippet(str);
		
		s.setScore(2);
		s.addMatchedWords("deep");
		
		assertNull(s.getHighlightedText());
		assertEquals(s.getText(), str);
		assertEquals(s.getScore(), 2);
		assertEquals(s.getMatchedWords().get(0), "deep");
		assertEquals(s.getMatchedWords().size(), 1);
	}
	
}
