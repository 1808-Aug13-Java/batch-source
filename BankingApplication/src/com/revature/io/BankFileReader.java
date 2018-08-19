package com.revature.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BankFileReader {

	public BankFileReader() {
		super();
	}
	
	public String[] readLines(String path) {
		String[] lines = new String[3];
		File file = new File(path);
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for (int i = 0; i < lines.length; i++) {
				lines[i] = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}
}
