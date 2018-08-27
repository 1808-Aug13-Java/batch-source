package com.revature.dao;

import java.util.List;

public interface ClientDao {
	
	public Client getClientById(long id);
	public Client getClientByEmail(String email);
	public Client getClientByUsername(String username);
	
	public List<Client> getClients();
	
	public int createClient(Client client);
	public int updateClient(Client client);
	public int deleteClient(long id);
	public int deleteClient(Client client);
}
