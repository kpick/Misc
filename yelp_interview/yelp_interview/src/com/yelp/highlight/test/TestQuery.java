package com.yelp.highlight.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yelp.highlight.Query;


public class TestQuery {

	@Test
	public final void testConstructor(){
		String str = "deep dish pizza";
		String [] arr  = {"deep", "dish", "pizza"};
		
		Query q = new Query (str);
		String [] words = q.getWords();
		int count = 0;
		for (String s : words) {
			assertEquals(s, arr[count]);
			++count;
		}
	}
}
