package com.revature.trivia;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TriviaDirver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathPeople = "src/com/revature/trivia/roster.txt";
		String pathQuestion = "src/com/revature/trivia/questions.txt";
		BufferedReader br;
		ArrayList<String> people = new ArrayList<String>();
		ArrayList<String> questions = new ArrayList<String>();
		String currentPerson;
		String currentQuestion;
		String name;
		String question;
		Random rand = new Random();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String nextSet = "";
		try {
			br = new BufferedReader(new FileReader(pathPeople));
			name = br.readLine();
			while(name != null) {
				people.add(name);
				name = br.readLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			br = new BufferedReader(new FileReader(pathQuestion));
			question = br.readLine();
			while(question != null) {
				questions.add(question);
				question = br.readLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(people.size() + " " + questions.size());
		
		while (nextSet != null) {
			if(!people.isEmpty() || !questions.isEmpty()) {
				currentPerson = people.get(rand.nextInt(people.size())); 
				currentQuestion = questions.get(rand.nextInt(questions.size()));
				System.out.println(currentPerson + "\n" + currentQuestion);
				System.out.println();
				people.remove(currentPerson);
				questions.remove(currentQuestion);
			} else {
				break;
			}
			nextSet = scan.nextLine();
		}
		
		System.out.println("Thanks for playing!");
		System.exit(0);
		
	}

}
