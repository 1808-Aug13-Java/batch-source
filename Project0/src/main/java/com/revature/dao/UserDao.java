/**
 * 
 */
package com.revature.dao;
import java.sql.Connection;
import java.util.List;
import com.revature.model.User;

/**
 * @author nozuko
 * INTERFACE
 */
public interface UserDao {
	
	public List<User> getUser();
	public User getUserById(String id);
	public User getUserById(String id, Connection con);
	public int createUser(User user);
	public int updateUser(User user);
	public int deleteUserById(String id);
	public void makeDeposit(String userId, float depositAmount);
	public void makeWithdrawal(String userId, float withdrawAmount);

}