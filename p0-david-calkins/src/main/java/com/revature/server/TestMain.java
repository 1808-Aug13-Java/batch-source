package com.revature.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMain {
	
	
	public static void main(String[] args) throws IOException, SQLException {
		Connection con = DBConnectionUtil.getConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CLIENTS");
		
		while (rs.next()) {
			System.out.println(rs.getString("username"));
		}
	}

}
