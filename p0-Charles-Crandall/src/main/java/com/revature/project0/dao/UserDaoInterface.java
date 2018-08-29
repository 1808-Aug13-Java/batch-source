package com.revature.project0.dao;

import com.revature.project0.abstraction.CurrentUser;

public interface UserDaoInterface {

	public CurrentUser getCustomer(int id);
	public CurrentUser getCustomer(String userName);
	public boolean checkPassword(String user, String pass);
	public boolean addCustomer(String user, String pass, String address);
	public boolean modifyAddress(int id, String newAddress);
	public boolean deleteCustomer(int id);
}