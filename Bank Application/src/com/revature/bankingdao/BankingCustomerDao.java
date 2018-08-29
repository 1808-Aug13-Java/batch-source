package com.revature.bankingdao;

import java.sql.Connection;
import java.util.List;

import com.revature.bankingmodel.BankCustomer;

public interface BankingCustomerDao {
	
	public List<BankCustomer> getBankCustomers();
	public BankCustomer getBankCustomerById(int id);
	public BankCustomer getBankCustomerById(int id, Connection c);
	public BankCustomer getBankCustomerByEmail(String email);
	public int createBankCustomer(BankCustomer bc);
	public int updateBankCustomer(BankCustomer bc);
	public int deleteBankCustomerById(int id);
	public void updateBalance(int id, float moneyAmount);
}
