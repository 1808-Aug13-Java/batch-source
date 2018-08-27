package com.revature.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** 
 * This class provides static methods to get the parameters used to 
 * form connections in the database. 
 */
public class DBConnectionUtil {
	
	/** The location of the properties file that contains the connection 
	 * information. */
	private static final String configFileLocation = 
			"C:\\Users\\David\\Desktop\\Dev Environments\\gitStuff\\Revature\\"
			+ "dbProperties0.0 - Do Not Upload.properties";
	
	/** The URL to connect to the database with. */
	private static String url = null;
	
	/** The username to connect to the database with. */
	private static String username = null;
	
	private static String passPhrase = null;
	
	/** Prevent instantiation.  */
	private DBConnectionUtil() {}
	
	
	/** Returns the URL to connect to the database with. Loads the URL 
	 * from the properties file if not yet initialized. 
	 * @throws IOException - If there is a problem loading the properties. */
	public static String getURL() throws IOException {
		// If the properties have not yet been initialized, load them. 
		if (url == null) {
			initializeProperties();
		}
		
		return url;
	} // end of getURL
	
	
	/** Returns the username to connect to the database with. Loads the username 
	 * from the properties file if not yet initialized. 
	 * @throws IOException - If there is a problem loading the properties. */
	public static String getUsername() throws IOException {
		// If the properties have not yet been initialized, load them. 
		if (username == null) {
			initializeProperties();
		}
		
		return username;
	} // end of getUsername
	
	
	
	/** Returns the pass phrase to connect to the database with. Loads the 
	 * pass phrase from the properties file if not yet initialized. 
	 * @throws IOException - If there is a problem loading the properties. */
//	public static String getPassPhrase() throws IOException {
//		// If the properties have not yet been initialized, load them. 
//		if (passPhrase == null) {
//			initializeProperties();
//		}
//		
//		return passPhrase;
//	} // end of getPassPhrase
	
	
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
			// TODO Auto-generated catch block
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
		
		// Used to read in the properties. 
		Properties prop = new Properties();
		InputStream in = new FileInputStream(configFileLocation);
		
		// Load the properties
		prop.load(in);
		
		// Set the properties to the appropriate static variables
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		passPhrase = prop.getProperty("password");
	} // end of initializeProperties
	
	
}
