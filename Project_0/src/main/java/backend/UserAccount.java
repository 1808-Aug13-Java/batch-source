package backend;

import java.util.ArrayList;

public class UserAccount 
{
	private String username, firstName, lastName;
	private BankingAccount attachedAccount;
	protected ArrayList<String> bankingAccounts = new ArrayList<String>();
	
	private int accountId;
	
	
	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", attachedAccount=" + ((attachedAccount == null)? "Not Avaiable":attachedAccount.toString()) + ", accountId=" + accountId
				+ "]";
	}

	protected String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected BankingAccount getAttachedAccount() {
		return attachedAccount;
	}

	protected void setAttachedAccount(BankingAccount attachedAccount) {
		this.attachedAccount = attachedAccount;
	}

	protected int getAccountId() {
		return accountId;
	}

	protected void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public UserAccount(String username, int accountId, String firstName, String lastName) {
		super();
		this.username = username;
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserAccount(String username, String firstName, String lastName) {
		super();
		this.username = username;
		this.accountId = -1;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	protected String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	protected String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}


	
	
}
