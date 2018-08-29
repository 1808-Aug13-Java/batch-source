package com.revature.model;


import com.revature.dao.UserDaoImpl;

import org.apache.log4j.Logger;

public class User {
	private static Logger log = Logger.getRootLogger();

	private String userId;
	private String password;
	private float balance;
	
	// has a relationship because customer HAS A account
	// so we will have account object inside of user/client => Account account
	private Account account;

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userId
	 * @param password
	 * @param balance
	 */
	public User(String userId, String password, float balance) {
		super();
		this.userId = userId;
		this.password = password;
		this.balance = balance;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	/**
	 * @param amount - the amount of $$ client wants to deposit, 
	 * cannot be negative number
	 */
	public void deposit(User user, String id, float amount) {
		if (amount > 0) {
			balance += amount;
			user.setBalance(balance);
			UserDaoImpl userDao = new UserDaoImpl();
	        userDao.makeDeposit(id, balance);
			log.info("You have successfully deposited $"+amount+". "
					+ "Your balance is now: $" + balance);
			}
			else {
				log.info("This amount is invalid. Please enter positive number.");
			}
		} // end deposit method

	/**
	 * @param amount - the amount of $$ client wants to withdraw, 
	 * cannot be negative number.
	 */
	public void withdraw(User user, String id, float amount) {
		double balanceChecker = balance;
		
		if (amount > 0) {

			// check if we can actually remove this amount of money	before doing it
			balanceChecker -= amount;

			// if balance would still be positive, then withdraw
			if (balanceChecker >= 0) {
				balance -= amount;
				user.setBalance(balance);
				UserDaoImpl userDao = new UserDaoImpl();
		        userDao.makeWithdrawal(id, -balance);
				log.info("You have successfully withdrawn $"+amount+". "
						+ "Your balance is now: " + balance);
			}
			else {
				log.info("Withdrawal of $"+amount+" unsuccessful due to "
						+ "Insufficient funds.");
			}
		}
		else {
			log.info("This amount is invalid. Please enter positive amount.");
		}
	} // end withdraw method

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", balance=" + balance + ", account=" + account
				+ "]";
	}
	
}