package com.revature.dao;

import java.util.List;

import com.revature.model.Bank;

public interface BankDao {
	public List<Bank> getAllBanks();
	public Bank getBankById(int id);
	public int createBank(Bank bank);
	public int updateBank(Bank bank);
	public int deleteBankById(int id);
}