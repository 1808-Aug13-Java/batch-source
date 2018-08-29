package backend;

public class BankingAccount 
{
	@Override
	public String toString() {
		String money = "$" + dollars +".";
		if(cents < 10)
			money += "0";
		money += cents;
		
		return "BankingAccount [name=" + name + ", isChecking=" + isChecking + ", mainUser=" + mainUser + " Balance: "+ money+"]";
	}

	private int dollars;
	private byte cents;
	private String name;
	private boolean isChecking;
	private int mainUser;
	
	public BankingAccount(int dollars, byte cents, String name, boolean isChecking, int user) 
	{
		super();
		this.dollars = dollars;
		this.cents = cents;
		this.name = name;
		this.isChecking = isChecking;
		mainUser = user;
	}
	
	protected void setMainUser(int i)
	{
		mainUser = i;
	}
	
	protected int getMainUser()
	{
		return mainUser;
	}
	
	protected float getMoney()
	{
		float ret = ((float)cents) / 100;
		return ret + (float)dollars;
	}
	
	protected boolean isAccountChecking()
	{
		return isChecking;
	}

	protected int getDollars() {
		return dollars;
	}

	protected void setDollars(int dollars) {
		this.dollars = dollars;
	}

	protected byte getCents() {
		return cents;
	}

	protected void setCents(byte cents) {
		this.cents = cents;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected boolean isChecking() {
		return isChecking;
	}

	protected void setChecking(boolean isChecking) {
		this.isChecking = isChecking;
	}
	
	
}
