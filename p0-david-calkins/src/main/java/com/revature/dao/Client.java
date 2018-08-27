package com.revature.dao;

public class Client {
	/** The unique id for the banking client. */
	private long clientId;
	
	/** The unique email for this client. */
	private String email;
	
	/** The unique username for this user.  */
	private String username;
	
	/** The hashed password for this user.  */
	private String passPhrase;
	
	/** Empty Constructor for a client.  */
	public Client() {}
	
	public Client(long clientId, String email, String username, String passPhrase) {
		super();
		this.clientId = clientId;
		this.email = email;
		this.username = username;
		this.passPhrase = passPhrase;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassPhrase() {
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (clientId != other.clientId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", email=" + email + ", username=" + username + ", passPhrase="
				+ passPhrase + "]";
	}
}
