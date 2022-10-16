package application;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TheRavenOGTest {

	@Test
	public void testIfCountTheWordsIsNotNull() {
		assertNotNull(TheRavenOG.countTheWords("/Users/lieslnick/Documents/The Raven with html? copy.txt"));
	}
	
	@Test
	public void testIFSortTheWordsIsNull() {
		Map<String, Integer> theList = new HashMap<String, Integer>();
		theList.put("String", 1);
		assertNotNull(TheRavenOG.sortValue(theList));
	}
	
	@Test
	public void testThatSortWorks() {
		Map<String, Integer> theList = new HashMap<String, Integer>();
		theList.put("goodbye", 2);
		theList.put("ricotta", 3);
		theList.put("banana", 1);
		TheRavenOG.sortValue(theList);
		assertEquals(theList.size(), 3);
	}
	
	
	@Test
	public void testForFirstListItemMatch() {
		List<Map.Entry<String, Integer>> sortTest = new LinkedList<Map.Entry<String, Integer>>();
		
		while (sortTest.size() < 25) {
			sortTest.add(0, null);
		}
		TheRavenOG.shrinkListToTwenty(sortTest);
		assertEquals(sortTest.size(), 20);
	}
	

}
