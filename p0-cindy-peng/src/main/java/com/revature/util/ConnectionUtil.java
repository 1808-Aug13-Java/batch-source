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
	
	private static Connection connection; //must import connection
	
	//testing it
//	public static Connection getHardCodedConnection() throws SQLException {
//		//jdbc:oracle:thin:@url:port:ORCL
//		String url = "jdbc:oracle:thin:@database2.c4tdrmkmnxdc.us-east-2.rds.amazonaws.com:1521:ORCL";
//		String username = "clpeng";
//		String password = "happiness4";
//		if(connection == null || connection.isClosed())
//			connection = DriverManager.getConnection(url, username, password);
//		return connection;	
//	}
	
	//gonna read from connectionproperties file to open connection
	public static Connection getConnection() throws SQLException, IOException 
	{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		if(connection == null || connection.isClosed())
			connection = DriverManager.getConnection(url, username, password);
		return connection;
		
	}
}
