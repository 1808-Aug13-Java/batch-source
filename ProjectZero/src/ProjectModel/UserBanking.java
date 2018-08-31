package ProjectModel;
	

public class UserBanking {

	public UserBanking() {}
	
	
	
	public UserBanking(int id) {
		super();
		this.id = id;
	}



	private int id;			// International Standard Book Number, unique
	private String username;
	private double userbalance;

	
	public int getBankingId() {
		return id;
	}

	public void setBankingId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public double getUserBalance() {
		return userbalance;
	}

	public void setUserBalance(double userbalance) {
		this.userbalance = userbalance;
}



	@Override
	public String toString() {
		return "UserBanking [id=" + id + ", username=" + username + ", userbalance=" + userbalance + "]";
	}
	
}
