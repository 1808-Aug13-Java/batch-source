package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author nozuk
 * This is where we are going to make a connection with our BANK database
 */
public class ConnectionUtil {
	
	
	// want to create a private connection
	private static Connection connection; // must import Connection
	
	/**
	 * This is going to return to me a connection
	 * throws IOException and SQLException
	 */
	public static Connection getConnection() throws IOException, SQLException {
		// new properties object 
		Properties prop = new Properties();// must import
		// create an input stream
		// connection properties file should be in base project file along with pom file NOT in resources folder
		InputStream in = new FileInputStream("connection.properties");
		
		//now we will load in our properties
		prop.load(in);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		if (connection == null || connection.isClosed()) {
			// if null or closed, create new connection
			connection = DriverManager.getConnection(url, username, password);
		}
		// otherwise connect. Now at this point, we can remove the url typed in the getHardCodedConnection()
		return connection;
	}
}