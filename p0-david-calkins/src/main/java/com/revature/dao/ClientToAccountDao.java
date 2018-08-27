package com.revature.dao;

import java.util.List;

public interface ClientToAccountDao {
	
	public List<Account> getAccountsByClient(Client client);
	public List<Client> getClientsByAccount(Account account);
	
	
	public int createCLtoAC(Client client, Account account);
	public int deleteCLtoAC(long clientId, long accountId);
	public int deleteCLtoAC(Client client, Account account);
}
