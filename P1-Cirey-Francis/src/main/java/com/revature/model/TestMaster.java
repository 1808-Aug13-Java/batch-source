package com.revature.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ProjectOneConnection;

public class TestMaster {

	public static void main(String[] args) {
		
		try {
			Connection con = ProjectOneConnection.getConnection();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No connection");
			e.printStackTrace();
		}

	}

}
