package com.revature.game;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> names = new ArrayList<String>();
		names.add("person1");
		names.add("person2");
		names.add("person3");
		names.add("person4");
		names.add("person5");
//		System.out.println(names.size());
		
		ArrayList<String> questions = new ArrayList<String>();
		questions.add("q1");
		questions.add("q2");
		questions.add("q3");
		questions.add("q4");
		questions.add("q5");
		
		People p = new People(names);
		Questions q = new Questions(questions);
		
		String name = p.getName();
		String question = q.getQuestion();
		System.out.println("Press Enter to continue");
		sc.nextLine();
		while((name != null) || (question != null)) {
			System.out.println(name + " " + question);
			name = p.getName();
			question = q.getQuestion();
			sc.nextLine();
		}
		System.out.println("game over");
		
	}
}