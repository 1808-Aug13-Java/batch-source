package com.revature.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileDriver {

	public static void main(String[] args) {
		
		String path = "src/com/revature/io/data.txt";
		String content = "\nGreetings Earthlings";
		
		try {
		//specify the file we want to write to
		File file = new File(path);
		
		//checking first to see if the file exists, creating it if it doesn't
		if(!file.exists()) {
				file.createNewFile();
		}
		
		// our FileWriter has an optional argument which specifies if it's appending to the file
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(content);
		System.out.println("Our file has been written");
		
		bw.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
