package com.revature.quiz;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuizDriver {

	public static void main(String[] args) {
		
		
		String names = "src/com/revature/quiz/Names.txt";
		String questions = "src/com/revature/quiz/Questions.txt";
		LinkedList<Things> nameList = new LinkedList<Things>();
		LinkedList<Things> quizList = new LinkedList<Things>();
		
		Scanner sc = new Scanner(System.in);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(names));
			String thing = br.readLine();
			nameList.add(new Things(thing));
			while(thing!=null) {
				thing=br.readLine();
				if(thing != null)
					nameList.add(new Things(thing));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(questions));
			String thing = br.readLine();
			quizList.add(new Things(thing));
			while(thing!=null) {
				thing=br.readLine();
				if(thing != null)
					quizList.add(new Things(thing));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Collections.sort(nameList);
		Collections.sort(quizList);

		while(!nameList.isEmpty()&&!quizList.isEmpty())
		{
			System.out.println(nameList.poll().getContents());
			System.out.println(quizList.poll().getContents());
			sc.nextLine();
		}
	}
}
