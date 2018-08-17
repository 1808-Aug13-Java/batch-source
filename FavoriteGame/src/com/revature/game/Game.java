package com.revature.game;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
	private List<User> users;
	private List<Question> questions;
	private Scanner sc = new Scanner(System.in);
	
	public Game() {
		super();
	}
	
	public Game(List<User> users, List<Question> questions) {
		super();
		this.users = users;
		this.questions = questions;
	}
	
	public void start() {
		shuffleLists();
		System.out.println("Starting...");
		System.out.println();
		while(users.size() != 0) {
			System.out.println(users.get(0).getName());
			
			sc.nextLine();
			
			System.out.println(questions.get(0).getQuestion());
			
			users.remove(0);
			questions.remove(0);
			
			shuffleLists();
			sc.nextLine();
			
		}
		
		System.out.println("Good job! 👍");
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public void shuffleLists() {
		Collections.shuffle(users);
		Collections.shuffle(questions);
	}
	
	

}
