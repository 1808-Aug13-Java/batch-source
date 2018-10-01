package com.revature.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileDriver {
	
	public static void main(String[] args) {
		// com -> revature -> io
		String path = "src/com/revature/io/data.txt";
		String content = "\nHello Earthlings";
		BufferedWriter bw = null;
		
		// check to see if exists, create if doesnt
		try {
			
			File file = new File(path);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			// file writer contains optional arg which specifies appending to file
			
			FileWriter fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			
			bw.write(content);
			System.out.println("Our file has been written");
			
			
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				
				if (bw != null) {
					bw.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
		
		
}
	
