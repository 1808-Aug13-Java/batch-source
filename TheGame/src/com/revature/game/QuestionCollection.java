package com.revature.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionCollection extends ArrayList<Question> {

	private static final long serialVersionUID = 1L;

	public QuestionCollection() {
		String fileName = "Resources/Questions.txt";
        String line = null;
        
        try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				this.add(new Question(line));
			}
			bufferedReader.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
