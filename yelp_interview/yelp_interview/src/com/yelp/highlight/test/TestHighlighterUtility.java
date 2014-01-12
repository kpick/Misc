package com.yelp.highlight.test;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.yelp.highlight.HighlighterUtility;


public class TestHighlighterUtility {
	@Test
	public final void testRegEx() {
		String str = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is...";		
		String key = "pizza";
		Pattern p = HighlighterUtility.craftExactPatternRegEx(key);
		assertEquals(".*pizza.*", p.toString());
		
		Matcher matcher = p.matcher(str);
		boolean check = false;
		if (matcher.matches()) {
			check = true;
		}
		assertTrue(check);

		str = "Chocolate rain.";
		matcher = p.matcher(str);
		check = false;
		if (matcher.matches()) {
			check = true;
		}
		assertFalse(check);

		
	}
	
	@Test
	public final void testTrimmer() {
		String str = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is...";
		String key = "pizza";
		
		// BASIC SIZE TESTS
		int size = 500;
		String result = HighlighterUtility.trimToMaxSize(str, key, size);
		String check = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is...";
		assertEquals (result, check);
		
		size = 20;
		result = HighlighterUtility.trimToMaxSize(str, key, size);
		check = "... disaster pizza,  my...";
		assertEquals (result, check);
		
		size = 50;
		result = HighlighterUtility.trimToMaxSize(str, key, size);
		check = "...ow as deep dish disaster pizza,  my bar for what c...";
		assertEquals (result, check);
		
		size = 100;
		result = HighlighterUtility.trimToMaxSize(str, key, size);
		check = "...ghy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish...";
		assertEquals (result, check);		
	
		size = 200;
		result = HighlighterUtility.trimToMaxSize(str, key, size);
		check = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is...";
		assertEquals (result, check);	
		
		// Checking Boundaries for run off.
		size = 100;
		str = "Pizza Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is...";
		result = HighlighterUtility.trimToMaxSize(str, key, size);
		check = "Pizza Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep ...";
		assertEquals (result, check);	

		size = 100;
		str = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster,  my bar for what constitutes good deep dish pizza was set pretty low as is...";
		result = HighlighterUtility.trimToMaxSize(str, key, size);
		check = "...ster,  my bar for what constitutes good deep dish pizza was set pretty low as is......";
		assertEquals (result, check);	

		size = 100;
		key = "deep dish pizza";
		str = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster,  my bar for what constitutes good deep dish pizza was set pretty low as is...";
		result = HighlighterUtility.trimToMaxSize(str, key, size);
		check = "... dish disaster,  my bar for what constitutes good deep dish pizza was set pretty low as is......";
		assertEquals (result, check);	
		
	}
}
