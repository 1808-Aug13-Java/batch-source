package com.revature.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game {

	static ArrayList<String> names = new ArrayList<String>();
	static Map<String, String> questions = new HashMap<String, String>();
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new FileReader(new File("src/com/revature/game/nameFile.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(fileIn.hasNext())
			names.add(fileIn.nextLine());
		
		fileIn = null;
		try {
			fileIn = new Scanner(new FileReader(new File("src/com/revature/game/quizFile.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(fileIn.hasNext())
			questions.put(fileIn.nextLine(),fileIn.nextLine());
		
		Collections.shuffle(names);
		Iterator<String> nameIt = names.iterator();
		Iterator<String> quesIt = questions.keySet().iterator();
		
		String current;
		while(nameIt.hasNext() && quesIt.hasNext()){
			System.out.println(nameIt.next());
			current = quesIt.next();
			System.out.println(current);
			
			scan.nextLine();
			System.out.println(questions.get(current));
			scan.nextLine();
		}

	}

}
