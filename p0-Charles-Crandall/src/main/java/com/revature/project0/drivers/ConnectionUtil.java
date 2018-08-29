package com.revature.project0.drivers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static Connection conn;

	public static Connection getConnection() throws IOException, SQLException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("pass");
		
		if (conn == null || conn.isClosed())
			conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}
}
