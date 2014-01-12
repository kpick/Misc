package com.yelp.highlight.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yelp.highlight.Document;
import com.yelp.highlight.Query;
import com.yelp.highlight.Snippet;


public class TestDocument {
	
	@Test
	public final void testConstructor() {
		String document = "When my friend who I was visiting told me she'd found the best chicago style deep dish pizza place, I was skeptical but open minded. Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
				"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
				"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
				"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
				"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
				"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
				"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		Document d = new Document (document);
		
		assertEquals(d.getSnippets().size(), 28);
		assertNotNull(d.getTopSnippet());
	}
	
	@Test
	public final void testComparator() {
		String document = "When my friend who I was visiting told me she'd found the best chicago style deep dish pizza place, I was skeptical but open minded. Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		Document d = new Document (document);
		
		Snippet s1 = new Snippet();
		s1.setScore(2);
		Snippet s2 = new Snippet();
		s2.setScore(4);
		Snippet s3 = new Snippet();
		s3.setScore(4);
		
		int i = d.sc.compare(s1, s2);
		assertEquals(i, -1);
		
		int j = d.sc.compare(s2, s1);
		assertEquals(j, 1);
		
		int k = d.sc.compare(s2, s3);
		assertEquals(k, 0);
	}
	
	@Test
	public final void testExecute() {
		String document = "When my friend who I was visiting told me she'd found the best chicago style deep dish pizza place, I was skeptical but open minded. Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
				"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
				"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
				"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
				"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
				"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
				"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		Document d = new Document (document);
		Query q = new Query ("deep dish pizza");
		
		String top = d.executeQuery(q);
		String result = "When my friend who I was visiting told me she'd found the best chicago style [[HIGHLIGHT]]deep dish pizza[[ENDHIGHLIGHT]] place, I was skeptical but open minded...";
		assertEquals(top, result);

		// Test Exact Match
		document = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		d = new Document (document);
		q = new Query ("deep dish pizza");
		
		top = d.executeQuery(q);
		result = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] disaster [[HIGHLIGHT]]pizza[[ENDHIGHLIGHT]],  my bar for what constitutes good [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] was set pretty low as is...";
		assertEquals(top, result);

		// Test non-first result.
		document = "Noise. Noise. Noise. Deep. Dish. Deep Pizza. Pizza Deep Dish. Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
			"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
			"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
			"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
			"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
			"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
			"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		d = new Document (document);
		q = new Query ("deep dish pizza");
		
		top = d.executeQuery(q);
		result = "Living in Hawaii and trying other poor examples of the doughy mess I had come to know as [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] disaster [[HIGHLIGHT]]pizza[[ENDHIGHLIGHT]],  my bar for what constitutes good [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]] was set pretty low as is...";
		assertEquals(top, result);		
		
		// Test unordered w/caps
		document = "Noise. Noise. Noise. Deep. Dish. Deep Pizza. Pizza Deep Dish. Deep Pizza Dish";
		d = new Document (document);
		q = new Query ("deep dish pizza");
		
		top = d.executeQuery(q);
		result = "[[HIGHLIGHT]]pizza[[ENDHIGHLIGHT]] [[HIGHLIGHT]]deep[[ENDHIGHLIGHT]] [[HIGHLIGHT]]dish[[ENDHIGHLIGHT]]...";
		assertEquals(top, result);		
	
		//Test non-first and CAPS, Exact Match
		document = "Noise. Deep Dish. deep dish. pizza deep Dish. When my friend who I was visiting told me she'd found the best chicago style Deep Dish Pizza place, I was skeptical but open minded. Living in Hawaii and trying other poor examples of the doughy mess I had come to know as deep dish disaster pizza,  my bar for what constitutes good deep dish was set pretty low as is." +
				"Though Friday night was probably not the best night to attempt to dine here without reservations as upon arrival we were warned the wait for a table for 2 would be 30 to 40 minutes.  This was not surprising because 1 - it *was* Friday night and 2 - I had seen studio apartments in Hawaii that rivalled the dining area of this restaurant." +
				"My friend promised it was worth the wait so we grabbed a couple of drinks from the bar, grabbed a menu to peruse and discuss while seated in the waiting area... er... blue comfy chairs in the corner." +
				"We eventually settled on the Little Star with sausage added. When we gave our order to the hostess (who kindly and wisely advised us to place our order with her first in order to minimize our wait time) she told us that we had inadvertently stumbled on a \"secret\" menu item. Allegedly the brass monkey earned its name as the most frequently ordered menu item by one of the Beastie Boys." +
				"Satisfied with our ordering prowess but dissatisfied with our empty wine glasses we ventured back to the bar for refills.  Luckily as we made our way through the dinner crowd two bar seats opened up.  Happily seated at the bar with a carafe of water and bottle of wine planted in front of us, I have no clue how long our wait actually was.  Whatever the length of time, it was most definitely worth it." +
				"Our pizza eventually arrived from the kitchen and was a perfect circle of mostly red with a golden brown crust outline.  I was immediately apprehensive because it seemed to be the epitome of everything I dislike in a pizza - excessive sauce and overly doughy crusts.  However the moment that first bite hit my lips all was forgiven. The sauce was amazing! Perfectly seasoned and not at all runny. Oh and the crust! I admit, I can be a bit of a crust snob and was certainly expecting to find an uncooked doughy disaster underneath all the topping but I was thrilled that these expectations were proven wrong.  The crust was crispy but not uncomfortably crunchy and overall the pizza just had wonderful flavors!" +
				"Overall despite the Friday night rush of people and the inevitable wait because of it, the service was still great and the food was amazing! Whenver I make it back up to San Francisco I am definitely eating here again!";
		d = new Document (document);
		q = new Query ("deep dish pizza");
		
		top = d.executeQuery(q);
		result = "When my friend who I was visiting told me she'd found the best chicago style [[HIGHLIGHT]]deep dish pizza[[ENDHIGHLIGHT]] place, I was skeptical but open minded...";
		assertEquals(top, result);
		
	}
}
