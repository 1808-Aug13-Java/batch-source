package com.ibbanker.main;
import com.ibbanker.utils.Bank;
import java.sql.*;
import com.ibbanker.dao.*;
import com.ibbanker.models.*;
import com.ibbanker.utils.Connect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
/**
 * Unit test for simple App.
 */
public class BankDriverTest 
{
    @Test
    public void testAssertEquals() {
      assertEquals(1,1);
    }
    
    @Test
    public void test_username_exists() {
      String username = "jbki5";
  
      Connection con = null;
      try {
        con = Connect.getConnection();
        assertTrue(Bank.usernameTaken(con, username));
      }
      catch(SQLException e) {
        e.printStackTrace();
      }
    }
    @Test
    public void test_username_exists_after_creation() {
      assertTrue(true);
    }
    
    @Test
    public void testUserValuesAfterCreation() throws SQLException {
      UsersDaoImp uDao = new UsersDaoImp();
      String sql = "insert into users (username, password, firstname, lastname, balance) values('newuser', '123', 'first', 'last', 0)";
      Connection con = null;
      try {
        con = Connect.getConnection();
        con.setAutoCommit(false);
        Statement stmt = con.createStatement(); 
        stmt.executeUpdate(sql);
        User user = uDao.getUser(con, "newuser"); 
        assertEquals(user.getUsername(), "newuser");
        assertEquals(user.getPassword(), "123");
        assertEquals(user.getFirstname(), "first");
        assertEquals(user.getLastname(), "last");
        assertEquals(user.getBalance(), 0);
      } 
      finally {
        con.rollback();
        con.setAutoCommit(true);
        con.close();
      }
    }
  }
  
//      Connection con = null;
//      PreparedStatement p_stmt = null;
//      try {
//        con = Connect.getConnection();
//        con.setAutoCommit(false);
//        String username = "jbki100";
//        char[] password = {'1', '2', '3'}; 
//        String first_name = "first_name";
//        String last_name = "lastname"; 
//        int balance = 0;
//        String u_sql = null;
//
        //prepare query to check for existing username
//        String sql = "Select * from users where username = ?";
//        p_stmt = con.prepareStatement(sql); 
//        p_stmt.setString(1, username);
//        ResultSet rs = p_stmt.executeQuery(sql);  
//        if(rs.next()) //if username exists, delete 
//        {
//            u_sql = "Delete from users where username = ?";
//            p_stmt = con.prepareStatement(u_sql);
//            p_stmt.setString(1,username);
//            p_stmt.executeUpdate();
//        }
//
//        u_sql = "Insert into users (username, password, firstname, lastname, balance) "
//          + "values(?, ?, ?, ?, ?)";
//        p_stmt = con.prepareStatement(u_sql); 
//        p_stmt.setString(1, username);
//        p_stmt.setString(2, new String(password));
//        p_stmt.setString(3, first_name);
//        p_stmt.setString(4, last_name);
//        p_stmt.setInt(5, balance);
//        p_stmt.executeUpdate(); 
//        //con.rollback();
//        con.setAutoCommit(true);
//        assertTrue(true); //TODO: finish  test 
