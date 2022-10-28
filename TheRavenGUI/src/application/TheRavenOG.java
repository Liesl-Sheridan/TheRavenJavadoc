

package application;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;


/**
 * The purpose of this program is to accept a file from a computer, filter out any punctuation or other symbols,
 * place the words and how often they appear into a hashmap, and then sort, reduce, and print the words and their count
 * @author Liesl Sheridan
 */
public class TheRavenOG {

	/**
	 * 
	 * @param fileName This method is used to accept a file, remove any HTML tags,
	 *                 backslashes, or punctuation, and places the words into a
	 *                 hashmap as a value. It counts how often the word appears in
	 *                 the file and uses that number as the key in the hashmap
	 * @return heRavenHashMap
	 */
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

	/**
	 * @param mapWords this method takes in a hashmap. I create a List Map.Entry
	 *                 and fill it with the hashmap parameter. It sorts the hashmap
	 *                 by calling Collections.sort and places it in the
	 *                 List Map.Entry. Returns the list
	 *                 @return theList
	 * 
	 */
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

	/**
	 * 
	 * @param sorted This method is for reducing the List down to 20 values. It runs
	 *               a for loop that checks if the list is larger than 20 and
	 *               removes that value if it is true
	 * @return sorted
	 */
	public static List<Map.Entry<String, Integer>> shrinkListToTwenty(List<Map.Entry<String, Integer>> sorted) {
		for (int i = 0; i < sorted.size(); i++) {
			while (sorted.size() > 20) // remove all but last 20 elements
				sorted.remove(i);
		}
		return sorted;
	}

	/**
	 * 
	 * @param sorted This method is simply for printing out the sorted List into the
	 *               console
	 */
	public static void printTheFinalList(List<Map.Entry<String, Integer>> sorted) {
		for (int i = 0; i < sorted.size(); i++) {
			System.out.println(sorted.get(i));
		}
	}

	/**
	 * 
	 * @param args
	 * Main method. This assigns the file location and calls all methods
	 */
	public static void main(String[] args) {

		String file = "/Users/lieslnick/Documents/The Raven with html? copy.txt";

		Map<String, Integer> words = new HashMap<String, Integer>();
		List<Map.Entry<String, Integer>> sorted = new LinkedList<Map.Entry<String, Integer>>();

		// this loads the file into a string and outputs it as a map
		words = countTheWords(file);
		// this accepts a map and then sorts it and places it in a list
		sorted = sortValue(words);
		// this accepts the sorted list and reduces it to 20 key/value pairs
		sorted = shrinkListToTwenty(sorted);
		// prints out the list
		printTheFinalList(sorted);

	}

}
