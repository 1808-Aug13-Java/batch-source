package com.ibbanker.dao;
import com.ibbanker.models.User;
import java.sql.*;

public interface UsersDao {
  public User getUser(Connection con, String username) throws SQLException;
  public int insertUser(Connection con, User u) throws SQLException;
  public int updateUser(Connection con, User u) throws SQLException;
  public int deleteUser(Connection con, User u) throws SQLException;
  public int updateUser(Connection con, User u, float balance) throws SQLException;
}
