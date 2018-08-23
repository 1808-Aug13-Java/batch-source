package com.revature.banking.data;

import java.io.IOException;
import java.math.BigDecimal;

public class RemoteDatabase implements DataInterface{
	
	
	
	
	
	@Override
	public boolean userNameExists(String userName) throws IOException {
		// TODO Auto-generated method stub
		return false;
	} // end of userNameExists
	
	@Override
	public BankUserData getUserByName(String userName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	} // end of getUserByName
	
	
	
	@Override
	public boolean userEmailExists(String email) throws IOException {
		// TODO Auto-generated method stub
		return false;
	} // end of userEmailExists
	
	@Override
	public BankUserData getUserByEmail(String email) throws IOException {
		// TODO Auto-generated method stub
		return null;
	} // end of getUserByEmail
	
	
	
	@Override
	public boolean addNewUser(BankUserData newUser) throws IOException {
		// TODO Auto-generated method stub
		return false;
	} // end of addNewUser
	
	@Override
	public boolean withdraw(BankUserData user, BigDecimal amount) throws IOException {
		// TODO Auto-generated method stub
		return false;
	} // end of withdraw
	
	
	@Override
	public void deposit(BankUserData user, BigDecimal amount) throws IOException {
		// TODO Auto-generated method stub
	} // end of deposit
	
}
