package com.revature.models;

public class Login {
	private int id;
	private String user;
	private String pwsd;
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(int id, String user, String pwsd) {
		super();
		this.id = id;
		this.user = user;
		this.pwsd = pwsd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwsd() {
		return pwsd;
	}
	public void setPwsd(String pwsd) {
		this.pwsd = pwsd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((pwsd == null) ? 0 : pwsd.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Login other = (Login) obj;
		if (id != other.id)
			return false;
		if (pwsd == null) {
			if (other.pwsd != null)
				return false;
		} else if (!pwsd.equals(other.pwsd))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Login [id=" + id + ", user=" + user + ", pwsd=" + pwsd + "]";
	}
	
}
