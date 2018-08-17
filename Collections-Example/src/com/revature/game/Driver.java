package com.revature.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		//if using io files, change, just first read lines into
		//arrays
		
		// questions collection
		LinkedList<String> questions = new LinkedList<String> ();
		// students collection
		LinkedList<String> students = new LinkedList<String> ();
		
		questions.add("Who is?");
		questions.add("What is?");
		questions.add("Where are?");
		questions.add("You are?");
		questions.add("Are you?");
		questions.add("Am I?");
		
		students.add("Nancy");
		students.add("Cindy");
		students.add("Bob");
		students.add("Joe");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter \"quit\" to end early");
		
		//whichever is smaller, is the max for loop i
		int maxRounds = Math.min(students.size(), questions.size());
		for(int i = 0; i < maxRounds; i++)
		{
			
			String input = sc.nextLine(); //get any user input
			if(input.equals("quit"))
				break;
			
			//random generator choose student
			Random studentSeed = new Random();
			int iStudents = studentSeed.nextInt(students.size());
			System.out.println( students.get( iStudents ) );
			students.remove(iStudents);
			//random generator choose question
			Random questionSeed = new Random();
			int iQuestions = questionSeed.nextInt(questions.size());
			System.out.println( questions.get(iQuestions) );
			questions.remove(iQuestions);
		}

	}

}
