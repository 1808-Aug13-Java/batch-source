package com.ibbanker.dao;
import com.ibbanker.utils.*;
import com.ibbanker.models.User;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.io.*;

public class UsersDaoImp implements UsersDao {
  private static final String T_USERS = "users";
  private static final String USERNAME = "username";
  private static final String P_FIELD = "password";
  private static final String FIRSTNAME = "firstname"; 
  private static final String LASTNAME = "lastname"; 
  private static final String BALANCE = "balance";
  private static final String SQL_SELECT_USER = "Select * from users where username = ?";
  private static final String SQL_INSERT_USER = String.format("Insert into %s (%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)", T_USERS, USERNAME, P_FIELD, FIRSTNAME, LASTNAME, BALANCE);
  private static final String SQL_UPDATE_USER = String.format("Update %s set %s=?, %s=?, %s=?, %s=?, %s=? where %s=?", T_USERS, USERNAME, P_FIELD, FIRSTNAME, LASTNAME, BALANCE, USERNAME); 
  private static final String SQL_DEPOSIT_C = "{call add_balance(?,?)}";
  private static final String SQL_BALANCE_P = String.format("Select %s from %s where %s = ?", BALANCE, T_USERS, USERNAME);
 private static final String SQLSTATE_NO_RESULTS = "99999";
  private static final Logger log = LogManager.getLogger(com.ibbanker.main.BankDriver.class.getName());
  @Override 
  public User getUser(Connection con, String username) throws SQLException {
    User user = new User();
    try (PreparedStatement stmtP = con.prepareStatement(SQL_SELECT_USER)
      ) {
      stmtP.setString(1, username);
      try (ResultSet rs = stmtP.executeQuery()) {
        rs.next();
        user.setUsername(rs.getString(USERNAME)); 
        user.setPassword(rs.getString(P_FIELD)); 
        user.setFirstname(rs.getString(FIRSTNAME)); 
        user.setLastname(rs.getString(LASTNAME)); 
        user.setBalance(rs.getFloat(BALANCE)); 
      }
      catch(SQLException e) {
        String sqlstate = e.getSQLState();
        if(sqlstate.equalsIgnoreCase(SQLSTATE_NO_RESULTS)) { //sql state when last row reached
          return null;
        }
      }
    }

    return user; 
  }
  
  @Override
  public int insertUser(Connection con, User u) throws SQLException {
    try(PreparedStatement stmtP = con.prepareStatement(SQL_INSERT_USER)) {
      stmtP.setString(1, u.getUsername());
      stmtP.setString(2, u.getPassword());
      stmtP.setString(3, u.getFirstname());
      stmtP.setString(4, u.getLastname());
      stmtP.setFloat(5, u.getBalance());
      try { 
        stmtP.executeUpdate();
      }
      catch(SQLException e) { //necessary with try with resources?
        con.close();
      }
    }
    return 0; 
  }
  @Override
  public int updateUser(Connection con, User u) throws SQLException {
    try (PreparedStatement stmtP = con.prepareStatement(SQL_UPDATE_USER)) {
      stmtP.setString(1, u.getUsername());
      stmtP.setString(2, u.getPassword());
      stmtP.setString(3, u.getFirstname());
      stmtP.setString(4, u.getLastname());
      stmtP.setFloat(5, u.getBalance());
      stmtP.setString(6, u.getUsername());
      stmtP.executeUpdate();
    }
    return 0;
  } 
  @Override
  public int updateUser(Connection con, User user, float amt) throws SQLException {
    con.setAutoCommit(false);
    try (CallableStatement stmtC = con.prepareCall(SQL_DEPOSIT_C)) {
      stmtC.setString(1, user.getUsername());
      stmtC.setFloat(2, amt); 
      stmtC.executeUpdate();
      try (PreparedStatement stmtP = con.prepareStatement(SQL_BALANCE_P)) {
        stmtP.setString(1, user.getUsername());
        try(ResultSet rs = stmtP.executeQuery()) {
          //check for too much withdrawal
          if(rs.next()) {
            float newBalance = rs.getFloat(BALANCE);
            if(newBalance < 0) {
              log.info("Can't withdraw that much");
              try {
                con.rollback();
              }
              catch(SQLException e) {
                log.error(e);
              }
            }
            else {
              con.commit(); 
            }
          }
        }
      }
    }
    finally {
      con.setAutoCommit(true);
    }
    return 0;
  } 
  
  @Override
  public int deleteUser(Connection con, User u)  throws SQLException {
    //TODO
    return 0;
  } 

}
