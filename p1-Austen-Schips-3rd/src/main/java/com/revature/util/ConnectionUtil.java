package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:oracle:thin:@demoneye.cijhximyui14.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "Krenjaw";
		String password = "Zorak77!";
		OracleDriver driver = new OracleDriver();
		DriverManager.registerDriver(driver);
		return DriverManager.getConnection(url, username, password);
	}
}