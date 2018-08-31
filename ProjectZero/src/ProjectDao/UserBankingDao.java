package ProjectDao;
import java.util.List;
import ProjectModel.UserBanking;
public interface UserBankingDao {

/**
 * Interface for our Data Access Object to handle database queries related to UserBanking.
 */

	public List<UserBanking> getAllUserBanking();
	public List<UserBanking> getUsername(String username);
	public List<UserBanking> getUserBalance(double userbalance);
	public UserBanking getUserBankingByName(String name);
	
	public boolean addUserBanking(UserBanking userbanking);
	public boolean updateUserBanking(UserBanking userbanking);
	public boolean deleteUserBankingById(String id);
}
