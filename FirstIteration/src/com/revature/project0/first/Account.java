package com.revature.project0.first;

import java.util.ArrayList;

public class Account {

	private String userName;
	private String password;
	private double balance;
	private ArrayList<Double> history;
	
	public Account() {
		this.userName = "";
		this.password = "";
		this.balance = 0.0f;
		this.history = new ArrayList<Double>();
	}
	
	public Account(String user, String pass, double bal, ArrayList<Double> hist) {
		this.userName = user;
		this.password = pass;
		this.balance = bal;
		this.history = hist;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<Double> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<Double> history) {
		this.history = history;
	}
	
	public void adjustBalance(double adjustment) {
		this.balance += adjustment;
	}

	@Override
	public String toString() {
		String msg = "" + userName + " " + password + " " + balance + " ";
		for(Double entry : history)
			msg += " " + entry.toString();
		return msg;
	}
	
	public void appendHistory(double value) {
		history.add(value);
	}
}
