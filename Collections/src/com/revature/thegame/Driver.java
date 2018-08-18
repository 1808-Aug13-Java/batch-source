package com.revature.thegame;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Questions Qgen = new Questions();
		Names Ngen  = new Names();
		Qgen.generateQuestions();
		Ngen.generateQuestions();
		
		for (int i=0; i<27; i++) {
			Ngen.findName(i);
			Qgen.findQuestion(i);	
			System.out.println();
		}
		

	}

}
