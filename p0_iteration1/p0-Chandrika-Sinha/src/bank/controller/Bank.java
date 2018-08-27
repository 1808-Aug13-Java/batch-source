package bank.controller;

import java.util.HashMap;

public class Bank {
	private static HashMap<String, User> users = new HashMap<String, User>();
	
	private Bank() {
		//******************FOR TESTING PURPOSES*****************************
		 users.put("Chandrika", new User ("Chandrika", "password"));
		//*******************************************************************
	}
	
	public static void addUser(User u) {
		users.put(u.getUsername(), u);
	}
	
	public static boolean hasUser(String input) {
		return users.containsKey(input);
	}
	
	public static User getUser(String input) {
		if (!hasUser(input)) {
			// throw UserNotFoundException
		}
		return users.get(input);
	}
}