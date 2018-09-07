package com.revature.quiz;

import java.io.*;
import java.util.*;

public class Quiz {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(".\\src\\com\\revature\\quiz\\question.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader(".\\src\\com\\revature\\quiz\\names.txt"));
		ArrayList<Questions> questions = new ArrayList<Questions>();
		ArrayList<Associates> assoc = new ArrayList<Associates>();
		
		try {
		    String line = br.readLine();

		    while (line != null) {
		    	if(line.equals("")) {
		    		//do not add
		    		line = br.readLine();
		    	}else {
		    		questions.add(new Questions(line));
			        line = br.readLine();
		    	}
		    	
		    }
		    for(Questions q: questions) {
		    	System.out.println(q.toString());
		    }
		    
		} finally {
		    br.close();
		}
		
		try {
		    String line = br2.readLine();

		    while (line != null) {
		    	if(line.equals("")) {
		    		//do not add
		    		line = br2.readLine();
		    	}else {
		    		assoc.add(new Associates(line));
			        line = br2.readLine();
		    	}
		    }
		    for(Associates a: assoc) {
		    	System.out.println(a.toString());
		    }
		    
		} finally {
		    br2.close();
		}
		/*
		 * logic for Quiz!!
		 */
	}

}
