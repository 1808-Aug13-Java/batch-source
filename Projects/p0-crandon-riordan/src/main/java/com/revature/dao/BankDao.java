package com.revature.dao;

import java.util.List;

import com.revature.model.Bank;

public interface BankDao {

	public Bank getBankById(int id);
	public Bank getBankByUserId(int id);
	public int createBank(Bank bank);
	public int updateBank(Bank bank);
	public int hideBankById(int id);
	public void deposit(int id);
	public void withdraw(int id);
	public void transfer(int fromId);
}
