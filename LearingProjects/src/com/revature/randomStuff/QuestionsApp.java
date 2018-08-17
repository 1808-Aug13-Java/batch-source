package com.revature.randomStuff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionsApp {
	
	public static final String PEOPLE_FILE_PATH = "People.txt";
	
	public static final String QUESTION_FILE_PATH = "Questions.txt";
	
	
	public static void main(String[] args) {
		// Keeps track of which question we are currently on.
		int currentQuestion = 0;
		
		// A scanner for use in blocking execution until the enter key is pressed
		Scanner scanner = null;
		
		// The list of people
		ArrayList<String> peopleList = null;
		// The list of questions
		ArrayList<String> questionsList = null;
		
		
		// Scan the people file for the names of the participants
		try {
			peopleList = populateList(PEOPLE_FILE_PATH);
		} catch (IOException e) {
			System.err.println("There was a problem loading the people: ");
			System.err.println(e.getMessage());
		}
		
		// If there are no people, either because of IOException or empty 
		// file, exit program. We can't have a quiz show without any people
		if (peopleList == null || peopleList.isEmpty()) {
			System.out.println("We can't have a quiz with no people. ");
			return;
		}
		
		// Scan the question file for the questions
		try {
			questionsList = populateList(QUESTION_FILE_PATH);
		} catch (IOException e) {
			System.err.println("There was a problem loading the questions: ");
			System.err.println(e.getMessage());
		}
		
		// If there are no questions, either because of IOException or empty 
		// file, exit program. We can't have a quiz show without any questions
		if (questionsList == null || questionsList.isEmpty()) {
			System.out.println("We can't have a quiz with no questions. ");
			return;
		}
		
		
		// For testing, if we want to hardcode some values. 
//		peopleList.add("Dennis");
//		peopleList.add("John");
//		peopleList.add("Christian");
//		peopleList.add("David");
//		peopleList.add("Jan");
//		peopleList.add("Austin");
//		peopleList.add("Alex");
//		peopleList.add("Julie");
//		peopleList.add("Frank");
//		peopleList.add("Smithy");
//		peopleList.add("Mario");
//		peopleList.add("Etzio");
//		peopleList.add("Geno");
//		peopleList.add("Link");
//		peopleList.add("Smitty Wermen Yeager Man-Jensen");
//		
//		
//		questionsList.add("Whom did Saul meet on the way to Antioch?");
//		questionsList.add("Which son of David rose up against him?");
//		questionsList.add("Who were Jacob's sons?");
//		questionsList.add("Who were the seven sons of Scheva?");
//		questionsList.add("What were the four faces of the beings from Ezekiel's vison?");
//		questionsList.add("How many days was Jesus in the tomb before he rose from the dead?");
//		questionsList.add("In which book(s) is the life of David recorded?");
//		questionsList.add("Which is the first gospel?");
//		questionsList.add("John 3:16?");
//		questionsList.add("How big was the boat Noah built?");
//		questionsList.add("What is the order of Melchizedek");
		
		// Randomize the order of people and questions
		Collections.shuffle(peopleList);
		Collections.shuffle(questionsList);
		
		
		// Open the scanner to the console, so we can read when the 
		// enter key is hit. 
		scanner = new Scanner(System.in);
		
		// For each person, ask them the next question in the list. 
		for (String person : peopleList) {
			System.out.print(person);
			System.out.println(": ");
			
			// Get the current question and increment to the next question
			System.out.println(questionsList.get(currentQuestion++));
			
			// Block execution until the user has hit the enter key on the console
			scanner.nextLine();
			
			// If we have exhausted the list of questions, shuffle the order 
			// and ask them again. 
			if (currentQuestion >= questionsList.size()) {
				Collections.shuffle(questionsList);
				currentQuestion = 0;
			}
		}
		
		scanner.close();
	}
	
	
	/** A private helper function to assist in the population of lists. */
	private static ArrayList<String> populateList(String filename)
													throws IOException
	{
		// A temporary string to temporarily hold values as they are read in
		String tempString;
		
		// The list that will be returned
		ArrayList<String> list = new ArrayList<>();
		
		// Scan the file for the contents
		try (Scanner scanner = new Scanner(new File(filename))) {
			// Scan each line of the file
			while (scanner.hasNextLine()) {
				// If the line isn't empty, add the line to the list
				if (!(tempString = scanner.nextLine()).isEmpty()) {
					list.add(tempString);
				}
			}
		} catch (IOException e) {
			// The catch block is merely here so we can autoclose the 
			// scanner. The exception is not actually handled here. 
			throw e;
		}
		
		return list;
	} // end of populateList
	
} // end of class QuestionsApp
