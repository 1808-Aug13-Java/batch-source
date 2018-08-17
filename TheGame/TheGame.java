package com.revature.thegame;

import java.util.LinkedList;
import java.util.Scanner;

public class TheGame {
	public static void main(String[] args) {
		LinkedList<String> associateNames = new LinkedList<>();
		LinkedList<String> questionBank = new LinkedList<>();
		Scanner consoleScanner = new Scanner(System.in);
		
		// fill an initialize ass names and qb
		associateNames.add("Chandrika 1");
		questionBank.add("Why? 1");
		
		associateNames.add("Chandrika 2");
		questionBank.add("Why? 2");
		
		associateNames.add("Chandrika 3");
		questionBank.add("Why? 3");
		
		associateNames.add("Chandrika 4");
		questionBank.add("Why? 4");
		
		associateNames.add("Chandrika 5");
		questionBank.add("Why? 5");
		
		associateNames.add("Chandrika 6");
		questionBank.add("Why? 6");
		
		associateNames.add("Chandrika 7");
		questionBank.add("Why? 7");
		
		associateNames.add("Chandrika 8");
		questionBank.add("Why? 8");
		
		associateNames.add("Chandrika 9");
		
		
		System.out.println("Welcome to The Game. Press enter to continue...");
		
		while (associateNames.size()!=0 && questionBank.size()!=0) {
			// wait until user presses enter to continue
			String userInput = consoleScanner.nextLine();
						
			// get random pair
			int maxAssociateIndex = associateNames.size()-1;
			int maxQuestionIndex = questionBank.size()-1;
			int associateIndex = (int) Math.round(Math.random()*maxAssociateIndex);
			int questionIndex = (int) Math.round(Math.random()*maxQuestionIndex);
			
			// print pair to console
			System.out.println(associateNames.get(associateIndex));
			System.out.println(questionBank.get(questionIndex));
			
			// remove from lists
			associateNames.remove(associateIndex);
			questionBank.remove(questionIndex);
		}
	}
}
