package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
private static Connection connection;
	
	private ConnectionUtil() {
		
	}
	
	public static Connection getConnection() throws IOException, SQLException {
		Properties p = new Properties();
		InputStream input = new FileInputStream("connection.properties");
		p.load(input);
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String pass = p.getProperty("password");
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, pass);
		}
		return connection;
	}
}
