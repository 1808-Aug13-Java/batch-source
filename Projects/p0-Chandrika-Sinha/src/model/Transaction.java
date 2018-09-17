package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
	private int id;
	private Timestamp time;
	private Account account;
	private User user;
	private String type;
	private BigDecimal balanceBefore;
	private BigDecimal balanceAfter;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int id, Timestamp time, Account account, User user, String type, BigDecimal balanceBefore,
			BigDecimal balanceAfter) {
		super();
		this.id = id;
		this.time = time;
		this.account = account;
		this.user = user;
		this.type = type;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getBalanceBefore() {
		return balanceBefore;
	}
	public void setBalanceBefore(BigDecimal balanceBefore) {
		this.balanceBefore = balanceBefore;
	}
	public BigDecimal getBalanceAfter() {
		return balanceAfter;
	}
	public void setBalanceAfter(BigDecimal balanceAfter) {
		this.balanceAfter = balanceAfter;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((balanceAfter == null) ? 0 : balanceAfter.hashCode());
		result = prime * result + ((balanceBefore == null) ? 0 : balanceBefore.hashCode());
		result = prime * result + id;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (balanceAfter == null) {
			if (other.balanceAfter != null)
				return false;
		} else if (!balanceAfter.equals(other.balanceAfter))
			return false;
		if (balanceBefore == null) {
			if (other.balanceBefore != null)
				return false;
		} else if (!balanceBefore.equals(other.balanceBefore))
			return false;
		if (id != other.id)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "Transaction [id=" + id + ", time=" + time + ", account=" + account + ", user=" + user + ", type=" + type
				+ ", balanceBefore=" + balanceBefore + ", balanceAfter=" + balanceAfter + "]";
	}
	
}
