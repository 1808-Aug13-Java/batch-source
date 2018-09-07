package com.revature.bank.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {

	private static Connection connection;
	private static Logger log = Logger.getRootLogger();

	private ConnectionUtil() {
		super();
	}
	
	public static Connection getHardCodedConnection() throws SQLException {
		
		Properties prop = new Properties();
		InputStream in;
		try {
			in = new FileInputStream("connections.properties");
			prop.load(in);
		} catch (IOException e) {
			log.error("Connection failed to be established. ", e);
		}
		String url = prop.getProperty("url");
		String userName = prop.getProperty("userName");
		String pswd = prop.getProperty("passwrd");
		
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, userName, pswd);
		}
		return connection;
	}
	
	public static Connection getConnection() throws IOException, SQLException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
	
}
