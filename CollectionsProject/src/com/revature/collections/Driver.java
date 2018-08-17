package com.revature.collections;

import java.util.ArrayList;
import java.util.Random;

public class Driver {
	
	public static void main(String[] args) {
		
		ArrayList<Names> names = new ArrayList<Names>();
		names.add(new Names("Bob"));
		names.add(new Names ("Mac"));
		names.add(new Names ("Jules"));
		names.add(new Names ("Jess"));
		//System.out.println(names);
		
		ArrayList<Questions> question = new ArrayList<Questions>();
		question.add(new Questions ("How old are you?"));
		question.add(new Questions ("Where are you from?"));
		question.add(new Questions ("How far is it to the sun?"));
		question.add(new Questions ("Who was the first person on the moon?"));
		question.add(new Questions ("What year is is?"));
		//System.out.println(question);
		
		//I know this should be in a separate class, but I had trouble
		//figuring out how to organize everything
		
		
		int number = 0;
		
		//logic here was to set the size of the smallest arrayList as the maximum index
		//and to use Random based on index size to put together a name and question by 
		//index. I am still getting used to Java syntax (from a C++ background), so implementing
		//my logic at all is slow. I didn't even manage to test if it worked.
		if(names.size() < question.size()) {
			number = names.size();
		} else {
			number = question.size();
		}
		
		Random rand = new Random();
		int n = rand.nextInt(number);
		
		System.out.println(rand);
		
				
		//for(int i = 0; i < number; i++) {
			
			
		//}
	
	}
	
	

}
