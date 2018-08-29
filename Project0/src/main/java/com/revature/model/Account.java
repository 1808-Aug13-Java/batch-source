package com.revature.model;
import org.apache.log4j.Logger;

public class Account {
	
	private static Logger log = Logger.getRootLogger();

	// make private because of security
	private float balance;

	/**
	 * 
	 */
	public Account() {
		super();
	}

	/**
	 * @param balance
	 */
	public Account(float balance) {
		super();
		this.balance = balance;
	}

	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(balance);
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
		Account other = (Account) obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}


	/**
	 * @param amount - the amount of $$ client wants to deposit, 
	 * cannot be negative number
	 */
	public void deposit(float amount) {
		if (amount > 0) {
			balance += amount;
			log.info("You have successfully deposited "+amount+". "
					+ "Your balance is now: " + balance);
		}
		else {
			log.info("This amount is invalid. Please enter positive number.");
		}
	} // end deposit method

	/**
	 * @param amount - the amount of $$ client wants to withdraw, 
	 * cannot be negative number.
	 */
	public void withdraw(float amount) {
		double balanceChecker = balance;
		
		if (amount > 0) {

			// check if we can actually remove this amount of money	before doing it
			balanceChecker -= amount;

			// if balance would still be positive, then withdraw
			if (balanceChecker >= 0) {
				balance -= amount;
				log.info("You have successfully withdrawn "+amount+". "
						+ "Your balance is now: " + balance);
			}
			else {
				log.info("Withdrawal of "+amount+" unsuccessful due to "
						+ "Insufficient funds.");
			}

		}
		else {
			log.info("This amount is invalid. Please enter positive amount.");
		}
	} // end withdraw method
}