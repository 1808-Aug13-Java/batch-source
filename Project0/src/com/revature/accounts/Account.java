package com.revature.accounts;

public class Account {
	private Integer accountNo;
    private String password;
    private double balance;

    public Account(Integer accountNo, String password, double balance) {
        this.accountNo = accountNo;
        this.password = password;
        this.balance = balance;
    }

   
   
	public Account() {
		super();
		
	}


	public Integer getAccountNo() {
        return accountNo;
    }

    
    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    
    public String getPassword() {
        return password;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }

    
    public double getBalance() {
        return balance;
    }

    
    public void setBalance(double balance) {
        this.balance = balance;
    }   
}
