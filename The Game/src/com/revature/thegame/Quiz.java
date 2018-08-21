package com.revature.thegame;

public class Quiz {
	 public static void main(String[] args)  {
	        QuestionBank quiz = new QuestionBank();
	        for (Question question: quiz.getQuestions()) {
	            System.out.println(question);
	        }
	    }
}
