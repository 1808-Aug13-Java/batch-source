package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	// where we are going to make a connection with our database

	// want to create a private connection
	private static Connection connection; // must import Connection
/*
	// we do not want to do this hard coding in future, but to test connection
	public static Connection getHardCodedConnection() throws SQLException {
		// our string should be in format:
		// jdbc:oracle:thin:@url:1521:ORCL
		String url = "jdbc:oracle:thin:@[url]:1521:ORCL"; // this is endpoint from AWS
		String username = "Nozuko"; // this username and password are what I set up on AWS...
		String password = "harry3622"; // ...but I use in sqldeveloper
		if (connection == null || connection.isClosed()) {
			// if null or closed, create new connection
			connection = DriverManager.getConnection(url, username, password);
		}
		// otherwise connect
		return connection;
	}
*/

	//this is going to return to me a connection
	public static Connection getConnection() throws IOException, SQLException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
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