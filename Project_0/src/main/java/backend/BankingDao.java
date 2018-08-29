package backend;

import java.sql.Connection;
import java.util.List;


public interface BankingDao 
{
	BankingAccount getAccountByName(String un, Connection con);
	List<BankingAccount> getUserAccounts(Connection con);
	List<String> getUserAccountsByUser(Connection con, int id);
	
	public int createBankingAccount(BankingAccount ua, Connection con);
	public int updateBankingAccount(BankingAccount ua, Connection con);
	
	public int deleteBankingAccount(BankingAccount ua, Connection con);

}
