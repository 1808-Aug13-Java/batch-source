package com.revature.thegame;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;

import com.revature.thegame.TheGame;

public class Driver {

	public static void main(String[] args) {
		
//		Scanner 
		TheGame theGame = new TheGame();

		String questionPath = "src/com/revature/thegame/question_bank.txt";
		String associatePath = "src/com/revature/thegame/associate_bank.txt";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(questionPath));
			BufferedReader questionBank = new BufferedReader(new FileReader(associatePath));
			String line = br.readLine();
			String qBankLine = questionBank.readLine();
			
			while(line != null) {
				System.out.println(line);
				line=br.readLine();
			}
			System.out.println();
			
			while(qBankLine != null) {
				System.out.println(qBankLine);
				qBankLine=questionBank.readLine();
			}
			

			br.close();
			System.out.println();
			questionBank.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		ArrayList<TheGame> gameList = new ArrayList<TheGame>();
		Collections.shuffle(gameList);

		System.out.println(gameList);
		

		
		for (TheGame g : gameList) {
			
			System.out.println(g);
		}
	}

}
