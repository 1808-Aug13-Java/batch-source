package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** 
 * This class provides static methods to get the parameters used to 
 * form connections in the database. 
 */
public class DBConnectionUtil {
	
	
	/** The URL to connect to the database with. */
	private static String url = null;
	
	/** The username to connect to the database with. */
	private static String username = null;
	
	/** The password to connect to the database with. */
	private static String passPhrase = null;
	
	/** Prevent instantiation.  */
	private DBConnectionUtil() {}
	
	
	/** This is provided as a convenience method. It creates new connections, 
	 * and returns them to threads that require them. It also initializes
	 * the properties, if not yet initialized, used to connect to the database. 
	 * @throws IOException - If there is a problem loading the properties.
	 * @throws SQLException - If there is a problem making a connection. */
	public static Connection getConnection() throws IOException, SQLException {
		// If the properties are not yet initialized, initialize them. 
		// Only need to check URL as if one is null, all are null. 
		if (url == null) {
			initializeProperties();
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Return a new connection instance
		return DriverManager.getConnection(url, username, passPhrase);
	} // end of getConnection
	
	
	/** This method initializes the properties that are used to connect to the 
	 * database with. This is thread safe, and it only initializes once. 
	 * @throws IOException - If there was a problem reading the properties. */
	private static synchronized void initializeProperties() throws IOException{
		// Do nothing if the variables have already been initialized. 
		if (url != null && username != null && passPhrase != null) {
			return;
		}
		
		// Make a new properties object and a new loader for the properties
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		// Load resource from the resources folder. 
		prop.load(loader.getResourceAsStream("connection.properties"));
		
		// Set the properties to the appropriate static variables
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		passPhrase = prop.getProperty("password");
	} // end of initializeProperties
	
	
}
