package com.revature.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationDriver {

	public static void main(String[] args) {
		
		try (FileInputStream fis = new FileInputStream("./Cereal.ser"); ObjectInputStream ois = new ObjectInputStream(fis)) {
					
			Cereal c = (Cereal) ois.readObject();
			System.out.println(c);
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
