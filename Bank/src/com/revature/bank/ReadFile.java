package com.revature.bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args) {
		readFile();
	}
	
	public static void readFile() {
		String path = "src/com/revature/bank/bank_data.txt";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			
			while(line != null) {
				System.out.println(line);
				line=br.readLine();
			}
			
			br.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
