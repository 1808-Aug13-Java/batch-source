package dao;

import java.sql.Connection;
import java.util.List;

import model.User;

public interface UserDao {
	public List<User> getUsers();
	public User getUserById(String id);
	public User getUserById(Connection con, String id);
	public int createUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
	int deleteUserById(String id);
}
