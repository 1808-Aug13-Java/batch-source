package com.revature.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class AssociateCollection extends LinkedList<Associate> {

	private static final long serialVersionUID = 1L;
	
	public AssociateCollection() {
        
		String fileName = "Resources/Associates.txt";
        String line = null;
        
        try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				this.add(new Associate(line));
			}
			bufferedReader.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

}
