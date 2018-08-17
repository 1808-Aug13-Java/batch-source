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
		/*Each of the try/catch blocks exists to read lines from the appropriate txt file
		 * and then add the line to the appropriate LinkedList. For example, the lines in 
		 * Names.txt are read to the String thing, which is then used in nameList.add(new Things(thing));
		 * This repeats until the file has no more lines to read.
		 * Same for quiz.txt.
		 */
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
		//These sort each randomly using my overridden random compareTo method in Things
		Collections.sort(nameList);
		Collections.sort(quizList);

		//this just prints out each name and each question in the order they are currently in inside of
		//the corresponding LinkedList.
		//It then waits for input from the user to print the next pair.
		while(!nameList.isEmpty()&&!quizList.isEmpty())
		{
			System.out.println(nameList.poll().getContents());
			System.out.println(quizList.poll().getContents());
			sc.nextLine();
		}
		sc.close();
	}
}
