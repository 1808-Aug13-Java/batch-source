package com.revature.app.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.app.dao.AccountDaoImpl;
import com.revature.app.util.ConnectionUtil;

public class Driver {
	private static Logger log = Logger.getRootLogger();

	public static void main(String[] args) {
//		AccountDaoImpl running= new AccountDaoImpl();
//		running.startUp();
//		running.screen();
		
		try {

			Connection con = ConnectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException e) {

			log.error(e.getMessage());
		}
		catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
