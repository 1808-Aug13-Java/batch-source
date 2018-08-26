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
//here we will make a connection with out database
//make a private connection with a public method that will use the driver 
	//otherwise just use the connnection
	
	private static Connection connection; 
	public static Connection getHardCodedConnection() throws SQLException {
		String url="";//"jdbc:oracle:thin:@jndbinstance.ckctizdzrngl.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username="novoa_j";
		String info="C!ndy221";
		if(connection == null || connection.isClosed()) {
			connection=DriverManager.getConnection(url, username, info);
		}
		
		return connection;
	}
	
	
	public static Connection getConnection() throws IOException,SQLException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		String url=prop.getProperty("url");
		String username=prop.getProperty("username");
		String info=prop.getProperty("password");
		if(connection == null || connection.isClosed()) {
			connection=DriverManager.getConnection(url, username, info);
		}
		return connection;
	}
}
