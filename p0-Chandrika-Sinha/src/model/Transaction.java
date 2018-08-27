package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
	private Timestamp id;
	private Account account;
	private User user;
	private String type;
	private BigDecimal balanceBefore;
	private BigDecimal balanceAfter;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(Timestamp id, Account account, User user, String type, BigDecimal balanceBefore,
			BigDecimal balanceAfter) {
		super();
		this.id = id;
		this.account = account;
		this.user = user;
		this.type = type;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
	}
	public Timestamp getId() {
		return id;
	}
	public void setId(Timestamp id) {
		this.id = id;
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
	public String toString() {
		return "Transaction [id=" + id + ", account=" + account + ", user=" + user + ", type=" + type
				+ ", balanceBefore=" + balanceBefore + ", balanceAfter=" + balanceAfter + "]";
	}
	
}
