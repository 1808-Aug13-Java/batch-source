package com.revature.client.data;

import java.io.Serializable;
import java.math.BigDecimal;

/** This class is used as a means of holding information about the current user
 * on the client side.  */
public class BankUserData implements Serializable{
	
	/**
	 * A generated serial ID for this object. 
	 */
	private static final long serialVersionUID = -5353343699521234536L;

	
	/** A uniquly generated ID for the user.  */
	private long uniqueID = 0;
	
	/** The email of the user in question. */
	private String email = "";
	
	/** The unique username of the user in question.  */
	private String username = "";
	
	/** The hashed password of the user. */
	private String passPhrase = "";
	
	/** The current balance at the time it was received from the database */
	private BigDecimal balance = new BigDecimal(0);
	
	
	public BankUserData() {
		
	}
	
	public BankUserData(long uniqueID, String email, String username, String passPhrase, BigDecimal balance) {
		super();
		this.uniqueID = uniqueID;
		this.email = email;
		this.balance = balance;
	}

	
	
	public long getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(long uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassPhrase() {
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
} // end of class BankUserData
