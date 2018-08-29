package com.ibbanker.utils;
import java.sql.*;
import java.io.*;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Connect {
  private static Connection con;
  private Connect() {
    super();
  }
  private static final Logger log = LogManager.getLogger(com.ibbanker.main.BankDriver.class.getName());
  public static Connection getConnection() throws SQLException {
		try(InputStream in = new FileInputStream("connection.properties")) {
      Properties prop = new Properties();
      prop.load(in);
      String url = prop.getProperty("url");
      String username = prop.getProperty("username");
      String password = prop.getProperty("password");
      if(con == null || con.isClosed()) 
      {
        con = DriverManager.getConnection(url, username, password);
      } 
    }
    catch(IOException e) {
      log.error(e);
    }
    return con;
  }
}
