package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao{
	
	private static Logger log = Logger.getRootLogger();
	//if log4j not recognized, delete log4j folder in .m2. THen Update Maven project
	//it will download our log4j for us :DD
	@Override
	public List<User> getUsers()
	{
		//make a list to be returned
		List<User> listUsers = new ArrayList<User> ();
		String sql = "SELECT * FROM USERS";
		try ( Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql); )
		{
			while(rs.next())
			{
				String username = rs.getString("USERNAME");
				String email = rs.getString("EMAIL");
				String password = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				//add to the list
				listUsers.add(new User(username, email, password, balance));
			}
			
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		}
		
		return listUsers;
	}
	//all our CRUD operations
	//this is like select, where. returns the user 
	@Override
	public User getUserByUsername(String username) 
	{
		//returning a result user
		User user = null;
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next())
			{
				String user_n = rs.getString("USERNAME");
				String email = rs.getString("EMAIL");
				String password = rs.getString("PASSWORD");
				float balance = rs.getFloat("BALANCE");
				user = new User(user_n, email, password, balance);
			}
			
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		}
		
		return user;
	}	
	@Override
	public int createUser(User user) 
	{
		int userCount = 0;
		String sql = "INSERT INTO USERS VALUES(?,?,?,?)";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{
			//must set the ? variables before executing
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3,  user.getPassword());
			ps.setFloat(4, user.getAccountBalance());
			//now the sql statement is finished, so execute UPDATE, not query.
			userCount = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		}
		return userCount;
	}
	@Override
	public int updateUser(User user) 
	{
		String sql = "UPDATE USERS "+
					"SET PASSWORD = ?, BALANCE = ? "+
					"WHERE USERNAME = ?";
		//no result set. not returinging a query at all
		int updateStatus = 0; //return status of update
		//build connection and preparedstatement
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, user.getPassword());
			ps.setFloat(2, user.getAccountBalance());
			ps.setString(3, user.getUsername());
			updateStatus = ps.executeUpdate();
		} catch (SQLException | IOException e) {
//			e.printStackTrace();
			log.info(e.getMessage());
		}
		return updateStatus;
	}
	@Override
	public int deleteUser(User user) 
	{
		String sql = "DELETE FROM USERS WHERE USERNAME = ?";
		int deleteStatus = 0;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, user.getUsername());
			deleteStatus = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		}
		return 0;
	}
	@Override
	public void makeWithdrawal(String username, float minus)
	{
		String sql = "{call DECREASE_BALANCE(?,?)}";
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql); )
		{
			cs.setString(1, username);
			cs.setFloat(2, minus);
			cs.execute();
			log.info("Balance has been changed.");
		} catch (SQLException | IOException e) {
			log.info(e.getMessage());
		}
	}
}
