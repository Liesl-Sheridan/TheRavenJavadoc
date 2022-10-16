package application;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class TheRavenOG {

	public static Map<String, Integer> countTheWords(String fileName) {

		HashMap<String, Integer> theRavenHashMap = new HashMap<String, Integer>();

		try {
			File fileObject = new File(fileName); // reading the exact file
			Scanner fileReader = new Scanner(fileObject);

			String poemString = null;

			while (fileReader.hasNext()) { // while there is stuff in the file, keep going
				poemString = fileReader.next(); // load the file into the string
				poemString = poemString.replaceAll("<[^>]*>", " "); // remove html coding
				poemString = poemString.replaceAll("&mdash", " ");
				poemString = poemString.replaceAll("’", "");
				poemString = poemString.replaceAll("[^A-Za-z0-9 ]", " "); // remove non-word characters excluding space
				poemString = poemString.replaceAll("\\s{2,}", " ");
				poemString = poemString.replaceAll("br", " ");
				poemString = poemString.replaceAll("left", " ");
				poemString = poemString.replaceAll("margin", " ");
				poemString = poemString.replaceAll("20", "");
				poemString = poemString.replaceAll("style", " ");
				poemString = poemString.replaceAll("span", " ");

				StringTokenizer tokenString = new StringTokenizer(poemString, " "); // this will allow the removal of
																					// html code

				while (tokenString.hasMoreTokens()) { // while there are more tokens, keep going
					String temp = tokenString.nextToken().toLowerCase();

					if (!theRavenHashMap.containsKey(temp))
						theRavenHashMap.put(temp, 1);
					Integer count = theRavenHashMap.get(temp);

					if (theRavenHashMap.containsKey(temp))
						theRavenHashMap.put(temp, count + 1);

				}

			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred in reading the file."); // in case of a problem
			e.printStackTrace();
		}
		return theRavenHashMap;

	}

	// this is taking in a hashmap and supposed to sort it and place it in a
	// list.return the list
	public static List<Entry<String, Integer>> sortValue(Map<String, Integer> mapWords) {

		// create a new list and fill it with the key/values from the inputed map
		List<Map.Entry<String, Integer>> theList = new LinkedList<Map.Entry<String, Integer>>(mapWords.entrySet());

		Collections.sort(theList, new Comparator<Map.Entry<String, Integer>>() { // call Collections.sort and tell it to
																					// sort theList
			public int compare(Map.Entry<String, Integer> object1, Map.Entry<String, Integer> object2) {
				return (object1.getValue()).compareTo(object2.getValue());
			}
		});

		return theList; // return a list

	}

	public static List<Map.Entry<String, Integer>> shrinkListToTwenty(List<Map.Entry<String, Integer>> sorted) {
		for (int i = 0; i < sorted.size(); i++) {
			while (sorted.size() > 20) // remove all but last 20 elements
				sorted.remove(i);
		}
		return sorted;
	}

	public static void printTheFinalList(List<Map.Entry<String, Integer>> sorted) {
		for (int i = 0; i < sorted.size(); i++) {
			System.out.println(sorted.get(i));
		}
	}

	public static void main(String[] args) {

		String file = "/Users/lieslnick/Documents/The Raven with html? copy.txt";

		Map<String, Integer> words = new HashMap<String, Integer>();
		List<Map.Entry<String, Integer>> sorted = new LinkedList<Map.Entry<String, Integer>>();

		words = countTheWords(file); // this loads the file into a string and outputs it as a map
		sorted = sortValue(words); // this accepts a map and then sorts it and places it in a list
		sorted = shrinkListToTwenty(sorted); // this accepts the sorted list and reduces it to 20 key/value pairs
		printTheFinalList(sorted); // prints out the list

	}

}
