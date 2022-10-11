package application;

import java.awt.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class JavaFXTest extends Application {

	Button button;
	Stage window;
	Scene scene1, scene2;
	// static ObservableList<Entry<String, Integer>> myList;
	// ListView<String> listView = new ListView<String>();

	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * BorderPane root = new BorderPane(); Scene scene = new Scene(root,400,400);
			 * scene.getStylesheets().add(getClass().getResource("application.css").
			 * toExternalForm()); primaryStage.setScene(scene);
			 * primaryStage.setTitle("Word Frequency"); primaryStage.show();
			 */

			window = primaryStage;

			// Label label1 = new Label("What are the most frequently used words in the poem
			// The Raven?");

			ObservableList<String> myList = FXCollections.observableArrayList(
					"then=9\n" + "lenore=9\n" + "more=9\n" + "from=9\n" + "me=10\n" + "bird=11\n" + "on=11\n"
							+ "is=12\n" + "raven=12\n" + "nevermore=12\n" + "chamber=12\n" + "door=15\n" + "a=16\n"
							+ "this=17\n" + "that=19\n" + "of=23\n" + "my=25\n" + "i=33\n" + "and=39\n" + "the=58 ");
			ListView<String> listView = new ListView<String>(myList);

			//creates a button
			Button button1 = new Button("The most frequent words in The Raven");
			//uses something called lambda (look up more info) to set the eventhandler to change to the next screen
			button1.setOnAction(e -> window.setScene(scene2));

			//type of layout
			StackPane layout1 = new StackPane();
			//this adds the button to the first window
			layout1.getChildren().add(button1);
			//sets how big the window is
			scene1 = new Scene(layout1, 400, 400);

			Button button2 = new Button("Go back");
			button2.setOnAction(e -> window.setScene(scene1));

			StackPane layout2 = new StackPane();
			layout2.getChildren().add(button2);
			//adds my list of words
			layout2.getChildren().add(listView);
			scene2 = new Scene(layout2, 400, 400);

			window.setScene(scene1);
			window.setTitle("Word Frequency");
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Integer> countTheWords(String fileName) {

		HashMap<String, Integer> theRavenHashMap = new HashMap<String, Integer>();

		try {
			File fileObject = new File(fileName); // reading the exact file
			Scanner fileReader = new Scanner(fileObject);

			String poemString = null;

			while (fileReader.hasNext()) { // while there is stuff in the file, keep going
				poemString = fileReader.next(); // load the file into the string
				// poemString = poemString.replaceAll("\\W+", " "); //turn anything not a word
				// into a space
				poemString = poemString.replaceAll("<[^>]*>", " "); // remove html coding
				poemString = poemString.replaceAll("&mdash", " ");
				poemString = poemString.replaceAll("’", "");
				poemString = poemString.replaceAll("[^A-Za-z0-9 ]", " "); // remove all non-word characters excluding
																			// space
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

	// this is taking in a hashmap and supposed to sort it and place it in a list.
	// return the list
	public static List<Entry<String, Integer>> sortValue(Map<String, Integer> mapWords) {

		// create a new list and fill it with the key/values from the inputed map
		List<Map.Entry<String, Integer>> theList = new LinkedList<Map.Entry<String, Integer>>(mapWords.entrySet());

		Collections.sort(theList, new Comparator<Map.Entry<String, Integer>>() { // call Collections.sort and tell it to
																					// sort theList
			public int compare(Map.Entry<String, Integer> object1, Map.Entry<String, Integer> object2) {
				return (object1.getValue()).compareTo(object2.getValue());
			}
		});

		// Collections.sort(theList, Collections.reverseOrder()); <- this caused a fatal
		// error

		return theList; // return a list

	}

	public static void main(String[] args) {
		launch(args);

		String file = "/Users/lieslnick/Documents/The Raven with html? copy.txt";

		Map<String, Integer> words = new HashMap<String, Integer>();
		List<Map.Entry<String, Integer>> sorted = new LinkedList<Map.Entry<String, Integer>>();

		words = countTheWords(file);
		sorted = sortValue(words);

		for (int i = 0; i < sorted.size(); i++) {
			while (sorted.size() > 20) // remove all but last 20 elements
				sorted.remove(i);
		}

		

	}
}