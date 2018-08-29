package com.ibbanker.main;
import com.ibbanker.models.User;
import com.ibbanker.utils.Connect;
import com.ibbanker.utils.Bank;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.Console;
import java.nio.*;
import java.sql.*;

public class BankDriver {
  private static final Logger log = LogManager.getLogger(com.ibbanker.main.BankDriver.class.getName());

  public static void main(String ... args) throws SQLException {
    boolean done = false;
    while(!done) {
      Console c = System.console();
      Bank.showSplashMenu(); //show prelogin menu
      String selection = c.readLine();
      switch (selection) {
        case "1": 
          Bank.createUserAccount(); 
          break;
        case "2":
        //do login stuff
          doActions(); 
          break;
        case "3": 
          log.info("bye");
          done = true; 
          break;
        default: log.info("I don't know what you want");
      } //end splash menu
    }
  }

  public static void doActions() {
    String username = Bank.readUsername(); 
    String password = Bank.readPassword(); 
    try (Connection con = Connect.getConnection()) {
      User user = Bank.login(con, username, password);
      if(user == null) {
        log.info(Bank.BAD_LOGIN);
      }
      
      while(user != null) {
        Bank.showLoggedinMenu();
        int selection = Bank.getSelection();
        switch (selection) {
          case 1: //deposit
            Bank.deposit(con, user);
            break;
          case 2: //withdraw
            Bank.withdraw(con, user);
            break;
          case 3: //view balance
            Bank.view(con, user);
            break;
          case 4: //logout
            user = null;
            break;
          default:
           log.info("don't know that option"); 
        }
      }
    } //end loggedinMenu
    catch(SQLException e) {
      log.info("Connection failed");
    }

  }
}
