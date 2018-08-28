package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	private static Connection connection;
	private ConnectionUtil() {}

	public static Connection getConnection() throws IOException, SQLException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		
		//the Properties object prop uses the InputStream in here
		prop.load(in);
		//the syntax for a property in the .properties file is
		//propertyname = property
		//no quotations.
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		// uses the previously defined strings to get a connection
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
}