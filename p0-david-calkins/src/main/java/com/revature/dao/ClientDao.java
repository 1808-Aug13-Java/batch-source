package com.revature.dao;

import java.util.List;

public interface ClientDao {
	
	public Client getClientById(long id);
	
	public List<Client> getClients();
	
	public int createClient(Client client);
	public int updateClient(Client client);
	public int deleteClient(long id);
	public int deleteClient(Client client);
}
