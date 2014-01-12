package com.yelp.highlight.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.yelp.highlight.Highlighter;

public class TestHighlighter {

	@Test
	public final void testHighlighterBasic(){
		String result = Highlighter.highlight_doc("I like pizza. Bob.", "pizza");
		String check = "I like [[HIGHLIGHT]]pizza[[ENDHIGHLIGHT]]...";
		
		assertEquals(result, check);
	}

	@Test
	public final void testHighlighterParagraph4Words(){
		String document = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		String result = Highlighter.highlight_doc(document, "deep dish pizza mess");
		String check = "Living in Hawaii and trying other poor examples of the doughy [[HIGHLIGHT]]mess[[ENDHIGHLIGHT]] I had come to know as [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] disaster [[HIGHLIGHT]]pizza[[ENDHIGHLIGHT]],  my bar for what constitutes good [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] was set pretty low as is...";
		assertEquals(result, check);
	}

	@Test
	public final void testHighlighterParagraph2WordsToTestBetterExactMatch(){
		String document = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		String result = Highlighter.highlight_doc(document, "doughy disaster");
		String check = "I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked [[HIGHLIGHT]]doughy disaster[[ENDHIGHLIGHT]] underneath all the topping but I was thrilled that these expectations were proven wrong...";
		assertEquals(result, check);
	}

	@Test
	public final void testHighlighterParagraph1WordMatch(){
		String document = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		String result = Highlighter.highlight_doc(document, "disaster");
		String check = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish [[HIGHLIGHT]]disaster[[ENDHIGHLIGHT]] pizza,  my bar for what constitutes good deep dish was set pretty low as is...";
		assertEquals(result, check);
	}
	
	@Test
	public final void testHighlighterParagraph1NoMatch(){
		String document = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		String result = Highlighter.highlight_doc(document, "Barry");
		String check = "NO RESULTS";
		assertEquals(result, check);
	}
	
	@Test
	public final void testHighlighterLongStringInDoc(){
		String document = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is " +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant" +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		String result = Highlighter.highlight_doc(document, "deep dish pizza");
		String check = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] disaster [[HIGHLIGHT]]pizza[[ENDHIGHLIGHT]],  my bar for what constitutes good [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] was set pretty low as is Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we ...";
		assertEquals(result, check);
	}

	@Test
	public final void testAnotherReview() {
		String document = "My boyfriend and I were looking for a place to grab dinner in the city.  After looking on Yelp and seeing this place serves Deep Dish Pizza (similar to Chicago style), we decided we had to try!  Great little restaurant, which we happened to get there at perfect timing before the crowd started rolling in (5pm).  We ordered a Pepperoni Dish Dish.  Takes 30 minutes to make-- but that is typical and expected for this type of meal.  Service was great.  Has a juke box to listen to some music. Meal was great, however nothing to compare to Chicago.  The crust was almost like a Fried Cornmeal.  However, for a deep dish pizza on this side of the coast, it's great to get your fix.  I saw a few people order the salad and garlic bread near by and that looked great too.";
		String result = Highlighter.highlight_doc(document, "deep dish pizza");
		String check = "After looking on Yelp and seeing this place serves [[HIGHLIGHT]]deep dish pizza[[ENDHIGHLIGHT]] (similar to Chicago style), we decided we had to try...";
		assertEquals(result, check);
	}
	
	@Test
	public final void testBoundaryConditions() {
		boolean ex = false;
		try {
			Highlighter.highlight_doc(null, null);	
		} catch (Exception e) {
			assertTrue (e instanceof RuntimeException);
			ex = true;
		}
		assertTrue (ex);
		
		ex = false;
		try {
			Highlighter.highlight_doc("aaa", null);	
		} catch (Exception e) {
			assertTrue (e instanceof RuntimeException);
			ex = true;
		}
		assertTrue (ex);
		
		ex = false;
		try {
			Highlighter.highlight_doc(null, "aaa");	
		} catch (Exception e) {
			assertTrue (e instanceof RuntimeException);
			ex = true;
		}
		assertTrue (ex);
		
		ex = false;
		try {
			Highlighter.highlight_doc("aaaa", "  ");	
		} catch (Exception e) {
			assertTrue (e instanceof RuntimeException);
			ex = true;
		}
		assertTrue (ex);
		
		ex = false;
		try {
			Highlighter.highlight_doc("  ", "a");	
		} catch (Exception e) {
			assertTrue (e instanceof RuntimeException);
			ex = true;
		}
		assertTrue (ex);		
		
		ex = false;
		try {
			Highlighter.highlight_doc("aaa", "aaaaa");	
		} catch (Exception e) {
			assertTrue (e instanceof RuntimeException);
			ex = true;
		}
		assertTrue (ex);
	}
}
