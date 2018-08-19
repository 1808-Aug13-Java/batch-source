package com.revature.banking.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/** This is a way to facilitate data access through local storage on the 
 * file system. */
public class FileBasedDatabase implements DataInterface{
	
	// Don't forget that ReadWriteLocks exist in java. Very Helpful
	
	private static final String DEFAULT_PATH = "bank.dat";
	
	/** Holds all the users in a HashMap based on their email.
	 * Holding the users in RAM is fine as this is only a placeholder
	 * class until actual databases are implemented. */
	private HashMap<String, BankUserData> userEmailMap = new HashMap<>();
	
	
	/** Holds all the users in a HashMap based on their username. */
	private HashMap<String, BankUserData> userNameMap = new HashMap<>();
	
	
	/** Tests if the file for the "database exists". */
	public boolean dataFileExists() {
		return new File(DEFAULT_PATH).exists();
	}
	
	
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
			// Attempt to read the map from the file system
			userEmailMap = (HashMap<String, BankUserData>) input.readObject();
			
			// Re-map all of the users based on their username
			userNameMap = new HashMap<>();
			
			for (Map.Entry<String, BankUserData> entry : userEmailMap.entrySet()) {
				userNameMap.put(entry.getValue().userName, entry.getValue());
			}
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
			// Attempt to save the map to the file system
			output.writeObject(userEmailMap);
		} finally {
			// Attempt to close the file when we are done or if there was 
			// an exception
			try {
				output.close();
			} catch (IOException ex) {}
		}
	}
	
	
	
	/** {@inheritDoc} */
	@Override
	public boolean userNameExists(String userName) {
		return userNameMap.containsKey(userName);
	}
	
	/** {@inheritDoc} */
	@Override
	public BankUserData getUserByName(String userName) {
		return userNameMap.get(userName);
	}
	
	
	/** {@inheritDoc} */
	@Override
	public boolean userEmailExists(String email) {
		return userEmailMap.containsKey(email);
	}
	
	/** {@inheritDoc} */
	@Override
	public BankUserData getUserByEmail(String email) {
		return userEmailMap.get(email);
	}
	
	
	
	/** {@inheritDoc} */
	@Override
	public boolean addNewUser(BankUserData newUser) {
		userEmailMap.put(newUser.email, newUser);
		userNameMap.put(newUser.userName, newUser);
		
		// Always return true, as this won't fail for an in memory "database"
		return true;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public boolean withdraw(BankUserData user, BigDecimal amount) {
		// Only withdraw if the user has more than he is requesting
		if (user.balance.compareTo(amount) >= 0) {
			user.balance.subtract(amount);
			return true;
		}
		
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public void deposit(BankUserData user, BigDecimal amount) {
		user.balance.add(amount);
	}


	

}
