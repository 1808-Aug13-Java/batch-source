package com.revature.banking.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;


/** This is a way to facilitate data access through local storage on the 
 * file system. */
public class FileBasedDatabase implements DataInterface{
	
	// Don't forget that ReadWriteLocks exist in java. Very Helpful
	
	private static final String DEFAULT_PATH = "bank.dat";
	
	/** Holds all the users in a HashMap based on their email.
	 * Holding the users in RAM is fine as this is only a placeholder
	 * class until actual databases are implemented. */
	private HashMap<String, BankUserData> userMap = null;
	
	
	/** This function loads the initial data from the local file system
	 * into memory. 
	 * @throws ClassNotFoundException *///TODO: Comment better tomorrow
	@SuppressWarnings("unchecked") // This is fine as the exception is handled elsewhere
	public void loadInitialData() 
			throws IOException, ClassCastException, ClassNotFoundException 
	{
		//TODO: Consider data format. Thinking of something like Property:Value
		// like JSON, or maybe just go with serialized objects. 
		ObjectInputStream input = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(DEFAULT_PATH)));
		
		try {
			// Attempt to read the map from the filesystem
			userMap = (HashMap<String, BankUserData>) input.readObject();
		} finally {
			// Attempt to close the file when we are done or if there was 
			// an exception
			try {
				input.close();
			} catch (IOException ex) {}
		}
	}
	
	
	/** Saves the bank info that is currently stored in RAM into the local
	 * file system. */
	public void saveData() throws IOException {
		// A buffered Object output stream for writing to the file
		ObjectOutputStream output = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(DEFAULT_PATH)));
		
		try {
			// Attempt to save the map to the filesystem
			output.writeObject(userMap);
		} finally {
			// Attempt to close the file when we are done or if there was 
			// an exception
			try {
				output.close();
			} catch (IOException ex) {}
		}
	}
	
	@Override
	public boolean userNameExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankUserData getUserByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean userEmailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankUserData getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean addNewUser(BankUserData newUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdraw(BankUserData user, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deposit(BankUserData user, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
