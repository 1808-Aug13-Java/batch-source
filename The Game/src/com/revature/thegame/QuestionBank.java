package com.revature.thegame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank {
	private ArrayList<Question> questions = new ArrayList<>();
	 public QuestionBank()  {
	        try {
	            FileReader file = new FileReader("questions.txt");
	            BufferedReader reader = new BufferedReader(file);
	            Scanner scanner = new Scanner(reader);

	            String line;
	            String question = "";
	            String[] alternatives = null;

	            int answer = 0;

	            int numberOfAlternatives = 0;
	            int counter = 0;

	            do {

	                do {
	                    line = scanner.nextLine();

	                    if (line.contains("?")) { //should store question
	                        question = line;
	                    } else if(counter == 0 && line.length() == 1) { //stores alternatives
	                        numberOfAlternatives = Integer.valueOf(line);
	                        alternatives = new String[numberOfAlternatives];
	                    } else if (line.contains(")")) { 
	                        alternatives[counter++] = line;
	                    }

	                }   while (answer == 0);

	                questions.add(new Question(question, alternatives, answer));
	                numberOfAlternatives = 0;
	                counter = 0;
	                answer = 0;

	            }   while (scanner.hasNext());


	            file.close();
	            reader.close();
	            scanner.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    public ArrayList<Question> getQuestions()  {
	        return questions;
	    }
}
