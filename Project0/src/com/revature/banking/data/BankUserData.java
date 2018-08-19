package com.revature.banking.data;

import java.io.Serializable;
import java.math.BigDecimal;

//TODO: Implement Serializable for easy object storage? Or something else?
public class BankUserData implements Serializable{
	
	/**
	 * A generated serial ID for this object. 
	 */
	private static final long serialVersionUID = -5353343699521234536L;

	//TODO: Make this more relevant when security and databases are implemented
	/** A uniquly generated ID for the user.  */
	long uniqueID = 0;
	
	/** The email of the user in question. */
	String email = null;
	
	/** The unique username of the user in question.  */
	String userName = null;
	
	//TODO: Consider not holding this here but soely on the back end database
	/** The hashed password of the user. */
	String passwordHash = null;
	
	/** The current balance at the time it was received from the database */
	BigDecimal balance = null;
}
