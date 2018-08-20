package com.revature.bank;

public class Account {

	// make private because of security

	private double balance;
	private String accountNumber;

	/**
	 * CONSTRUCTOR
	 */
	public Account() {
		super();
	}

	/**
	 * CONSTRUCTOR
	 * @param balance
	 * @param accountNumber
	 */
	public Account(double balance, String accountNumber) {
		super();
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @param amount - the amount of $$ client wants to deposit, 
	 * cannot be negative number
	 */
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("You have successfully deposited "+amount+". "
					+ "Your balance is now: " + balance);
		}
		else {
			System.out.println("This amount is invalid. Please enter positive number.");
		}
	} // end deposit method

	/**
	 * @param amount - the amount of $$ client wants to withdraw, 
	 * cannot be negative number.
	 */
	public void withdraw(double amount) {
		double balanceChecker = balance;
		
		if (amount > 0) {

			// check if we can actually remove this amount of money	before doing it
			balanceChecker -= amount;

			// if balance would still be positive, then withdraw
			if (balanceChecker >= 0) {
				balance -= amount;
				System.out.println("You have successfully withdrawn "+amount+". "
						+ "Your balance is now: " + balance);
			}
			else {
				System.out.println("Withdrawal of "+amount+" unsuccessful due to "
						+ "Insufficient funds.");
			}

		}
		else {
			System.out.println("This amount is invalid. Please enter positive amount.");
		}
	} // end withdraw method

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}

} // end Account class
