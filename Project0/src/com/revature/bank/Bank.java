/**
 * 
 */
package com.revature.bank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author nozuko
 *
 */
public class Bank {
	
	/**
	 * As a user, I can:
	 * create an account with a unique email or username, along with a password (for this first iteration, multiple users need not be accommodated)
	 * log in 
	 * log out
	 * deposit money
	 * withdraw money
	 * view balance
	 */
	
	// has a relationship because bank HAS A user/client
	// so we will have client object inside of bank class => Client client
	
	private Client[] client = new Client[1]; //MAKE THIS HASHMAP!!! only supports 1 user now

	/**
	 * 
	 */
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param interestRate
	 * @param client
	 */
	public Bank(Client[] client) {
		super();
		this.client = client;
	}

	/**
	 * @return the client
	 */
	public Client[] getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client[] client) {
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(client);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (!Arrays.equals(client, other.client))
			return false;
		return true;
	}

} // end Bank class
