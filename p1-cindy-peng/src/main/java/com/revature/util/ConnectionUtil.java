package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	//singleton design pattern
	
	private static Connection connection;
	private ConnectionUtil() {}
	
	public static Connection getConnection() throws IOException, SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		//i think...i hope the line below is referrencing a connection.properties file
//		if(loader.getResourceAsStream("connection.properties") == null) {
//			System.out.println("noooo");
//			return null;
//		}
		//I had to keep connection.properties in the same dir as the ConnectionUtil 
		//package...so inside src/main/java
		//FIXME! Ask why
		prop.load(loader.getResourceAsStream("connection.properties"));
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
}
