package com.revature.bankingutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankingConnection {
	
	private static Connection connection;
	
	public static Connection getConnection() throws IOException, SQLException
	{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("bankingcredentials.properties");
		prop.load(in);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		if(connection == null || connection.isClosed())
		{
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}

}
