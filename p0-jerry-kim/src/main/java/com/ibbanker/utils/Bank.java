package com.ibbanker.utils;
import com.ibbanker.models.User;
import com.ibbanker.utils.Connect;
import com.ibbanker.dao.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.Console;
import java.util.*;
import java.sql.*;
import java.io.*;

//for completely static class, use private constructor 
public class Bank {
  public static final String CREATE_USR = "Username: ";
  public static final String CREATE_P = "Password: ";
  public static final String CREATE_FIRSTNAME = "First name: ";
  public static final String CREATE_LASTNAME = "Last name: ";
  public static final int STARTING_BALANCE = 0;
  public static final String USR_TAKEN = "username taken";
  public static final String READ_USERNAME = "Username: "; 
  public static final String READ_P = "Password: ";
  public static final String DEPOSIT_AMT = "enter deposit amount: ";
  public static final String WITHDRAW_AMT = "enter withdrawal amount: ";
  public static final String DELIMIT = " "; //delimiter used by scanner to parse user files
  public static final String BALANCE = "balance";
  public static final String BAD_LOGIN = "Wrong username or password";
  public static final String E_DEPOSIT = "Something went wrong";
  public static final String E_WITHDRAW = "Something went wrong";
  private static UsersDaoImp uDao = new UsersDaoImp();
  /*TABLE IDENTIFIERS*/
  private static Logger log = LogManager.getLogger(com.ibbanker.utils.Bank.class.getName());
  private Bank() {
    super();
  }
  public static void print(Object o) {
    log.info(o);
  }

  public static String createUsername(String msg) {
    Console c = System.console();
    return c.readLine(msg);
  }

  public static String createPassword(String msg) {
    Console c = System.console();
    log.info(msg);
    char[] pwd = c.readPassword();
    log.info(pwd);
    return new String(pwd);
  }
  public static User login(Connection con, String username, String password) throws SQLException {
    User user = uDao.getUser(con, username);
    if(user != null && (password.equals(user.getPassword()))) {
      return user; 
    }
    return null;
  } 

  public static boolean usernameTaken(Connection con, String username) throws SQLException {
    User user = uDao.getUser(con, username);
    if(user == null) {
      return false;
    }
    log.info(USR_TAKEN);
    return true;
  }

  public static User createUserAccount() {
    Console c = System.console();
    String username = null;
    char[] password = new char[50];
    String firstname = null;
    String lastname = null;
    UsersDaoImp uDao = new UsersDaoImp(); 
    User user = new User();
     
    try {
      Connection con = Connect.getConnection();
      do {
        username = c.readLine(CREATE_USR);
        password = c.readPassword(CREATE_P);
      }
      while (usernameTaken(con, username));

      firstname = c.readLine(CREATE_FIRSTNAME);
      lastname = c.readLine(CREATE_LASTNAME);
      user.setUsername(username);
      user.setPassword(new String(password));
      user.setFirstname(firstname);
      user.setLastname(lastname);
      user.setBalance(STARTING_BALANCE);
      uDao.insertUser(con, user);
      
    }
    catch(SQLException e) {
     log.error(e); 
    }
    return user;
  }
  //views balance of current user
  public static void view(Connection con, User user) {
    UsersDaoImp uDao = new UsersDaoImp(); 
    try {
      user = uDao.getUser(con, user.getUsername());
      log.info(String.format("Current Balance: %.2f", user.getBalance()));
    }
    catch(SQLException e) {
      log.error("some error", e);
    }

  }

  public static String readUsername() {
    Console c = System.console();
    return c.readLine(READ_USERNAME);
  }
  public static String readPassword() {
    Console c = System.console();
    log.info(READ_P);
    return new String(c.readPassword());
  }

  public static void showSplashMenu() {
    log.info("1. First time login");
    log.info("2. Login");
    log.info("3. exit");
  }
  public static void showLoggedinMenu() {
    log.info("1. deposit");
    log.info("2. withdraw");
    log.info("3. view");
    log.info("4. logout");
  }
  public static Scanner getScanLine(Scanner sc, String target) {
    while(sc.hasNext())
    {
      try {
        if(sc.next().contains(target)) 
        {
          return sc;
        }
      }
      catch(NoSuchElementException e) {
        log.error(e);
        break;
      }
    }
    return sc;
  }
  public static int getSelection() {
    Console c = System.console();
    String selection = c.readLine();
    Integer selectionInt = null; 
    try {
      selectionInt = Integer.parseInt(selection);  //getting input
    }
    catch(NumberFormatException e) {
      return 5;
    }
    return selectionInt;
  }

  public static void deposit(Connection con, User user) {
    UsersDaoImp uDao = new UsersDaoImp();
    Console c = System.console();
    String input = null;
    Float amt = null;
    do {
      try {
      
        input = c.readLine(WITHDRAW_AMT);
        if(input.equalsIgnoreCase("b")) {
          return; //go back to loggedin menu
        }
        amt = Float.parseFloat(input);
      }
      catch(NumberFormatException e) { //catch non-numbers
        amt = null; //don't really need this?
      }
    }
    while(!validAmount(amt));
    try {
      uDao.updateUser(con, user, amt);
    }
    catch(SQLException e) {
      log.info(E_DEPOSIT);
    }
  }

  public static void withdraw(Connection con, User user) {
    UsersDaoImp uDao = new UsersDaoImp();
    Console c = System.console();
    String input = null;
    Float amt = null; 
    do {
      try {
        input = c.readLine(WITHDRAW_AMT);
        if(input.equalsIgnoreCase("b")) {
          return; //go back to loggedin menu
        }
        amt = Float.parseFloat(input);
      }
      catch(NumberFormatException e) { //catch non-numbers
        amt = null; //don't really need this?
      }
    }
    while(!validAmount(amt));
    try {
      uDao.updateUser(con, user, (-1)*amt); 
    }
    catch(SQLException e) {
      log.info(E_WITHDRAW); //withdrawing too much
    }
  }

  public static boolean validAmount(Float amt) {
    if(amt == null) {
      log.info("Enter a number");
      return false;
    }
    else if(amt < 0) {
      log.info("no negative amount");
      return false;
    }
    return true;
  }
}
