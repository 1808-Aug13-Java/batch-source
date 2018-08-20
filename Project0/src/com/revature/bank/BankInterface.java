/**
 * 
 */
package com.revature.bank;

/**
 * @author Nozuko Sutherland
 *
 */
public interface BankInterface {
	
	/**
	 * As a user, I can:
	 * create an account with a unique email or username, along with a password (for this first iteration, multiple users need not be accommodated)
	 * log in 
	 * log out
	 * deposit money
	 * withdraw money
	 * view balance
	 */
	
	/**
	 * @return the client
	 */
	public Client[] getClient();

	/**
	 * @param client the client to set
	 */
	public void setClient(Client[] client);

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode();

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj);

}
