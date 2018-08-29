package backend;
import java.sql.Connection;
import java.util.List;


public interface UserDao 
{
	UserAccount getAccountByName(String un, String pw, Connection con);
	List<UserAccount> getUserAccounts(Connection con);
	
	public boolean doesUserExist(String un, Connection con);
	
	public int createUserAccount(UserAccount ua, String pw, Connection con);
	public int updateUserAccount(UserAccount ua, Connection con);
	
	public int deleteUserAccount(UserAccount ua, Connection con);
	
	public int getAvailableId(Connection con);
}
