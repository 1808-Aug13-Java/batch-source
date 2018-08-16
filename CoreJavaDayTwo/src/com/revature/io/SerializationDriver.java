package com.revature.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationDriver {
	
	public static void main(String[] args) {
		
		
		
		Cereal c = new Cereal(true, 200, 35, "Lucky Charms");
		
		System.out.println(c);
		
		try {
			
			FileOutputStream fos = new FileOutputStream("./Cereal.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			
			fos.close();
			oos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
