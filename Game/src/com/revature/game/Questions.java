package com.revature.game;

import java.util.ArrayList;

public class Questions {

	private ArrayList<String> questions;

	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Questions(ArrayList<String> questions) {
		super();
		this.questions = questions;
	}

	public String getQuestion() {
		int size = questions.size();
		if (size == 0) {
//			System.out.println("No more questions");
			return null;
		}
		int index = (int) (Math.random()*1500) % size;
		String question = questions.get(index);
		questions.remove(index);
		questions.trimToSize();
		return question;	
		}

	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}
	
}
